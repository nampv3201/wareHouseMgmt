package com.datn.warehousemgmt.utils;

import com.datn.warehousemgmt.dto.ImportDetail;
import com.datn.warehousemgmt.dto.ProductDTO;
import com.datn.warehousemgmt.dto.request.ProductRequest;
import com.datn.warehousemgmt.entities.Product;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.mapper.ProductMapper;
import com.datn.warehousemgmt.repository.CategoryRepository;
import com.datn.warehousemgmt.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.print.DocFlavor;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ImportProduct {

    @Value("${upload.folder}")
    private String uploadFolder;

    @Value("${upload.result.folder}")
    private String uploadResultFolder;

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    String[] HEADERS = {"SKU_Code", "Name", "Description", "Category"};
    public ImportDetail readFileImportUserToCourse(String fileName){
        String path = uploadFolder + fileName;
        ImportDetail importDetail = new ImportDetail();
        List<ProductRequest> validProducts = new ArrayList<>();
        List<List<String>> errors = new ArrayList<>();

        try (Reader in = new FileReader(path)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .builder()
                    .setSkipHeaderRecord(false)
                    .build()
                    .parse(in);

            CSVRecord headerRecord = records.iterator().next();
            List<String> actualHeaders = new ArrayList<>();
            headerRecord.iterator().forEachRemaining(actualHeaders::add);

            if (!actualHeaders.equals(List.of(HEADERS))) {
                throw new AppException(ErrorCode.FILE_FALSE_FORMAT);
            }

            for (CSVRecord record : records) {
                String skuCode = record.get("SKU_Code");
                String name = record.get("Name");
                String description = record.get("Description");
                List<Long> categories;

                // Parse categories safely
                try {
                    categories = Arrays.stream(record.get("Categories").split(","))
                            .map(Long::parseLong)
                            .collect(Collectors.toList());
                } catch (NumberFormatException e) {
                    categories = new ArrayList<>();
                }

                // Validate data
                StringBuilder errorMessages = new StringBuilder();
                List<String> errorRecord = new ArrayList<>();
                errorRecord.add(skuCode);
                errorRecord.add(name);

                boolean isValid = true;
                if (skuCode == null || skuCode.isEmpty()) {
                    errorMessages.append("Mã sản phẩm không được để trống. ");
                    isValid = false;
                }
                if (productRepository.existsBySkuCode(skuCode)) {
                    errorMessages.append("Mã sản phẩm đã tồn tại trong hệ thống. ");
                    isValid = false;
                }
                if (name == null || name.isEmpty()) {
                    errorMessages.append("Tên sản phẩm không được để trống. ");
                    isValid = false;
                }

                if (isValid) {
                    validProducts.add(new ProductRequest(skuCode, name, description, categories, null, null));
                } else {
                    errorRecord.add(errorMessages.toString());
                    errors.add(errorRecord);
                }
            }

            importDetail.setProducts(validProducts);
            importDetail.setError(errors);

        } catch (FileNotFoundException e) {
            throw new AppException(ErrorCode.FILE_NOT_FOUND);
        } catch (IOException e) {
            throw new AppException(ErrorCode.IO_EXCEPTION);
        }

        return importDetail;
    }

    public void importProduct(ImportDetail importDetail){
        List<Product> productList = new ArrayList<>();
        try {
            importDetail.getProducts().forEach(dto -> {
                Product product = new Product();
                product.setSkuCode(dto.getSkuCode());
                product.setName(dto.getName());
                product.setDescription(dto.getDescription());
                product.getCategories().addAll(categoryRepository.findAllByIdIn(dto.getCategoryIds()));
                productList.add(product);
            });
            productRepository.saveAll(productList);
        }catch (Exception e){
            throw new AppException(ErrorCode.IMPORT_FAILED);
        }
    }

    public String fileErrorName(ImportDetail importDetail, String fileName){
        List<String> fileSplit = List.of(fileName.split("\\."));
        String outputPath = uploadResultFolder + fileSplit.get(0) + UUID.randomUUID() + "." + fileSplit.get(1);
        try (Writer writer = new FileWriter(outputPath);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(HEADERS))) {
            for(int i = 0; i< importDetail.getError().size(); i++) {
                csvPrinter.printRecord(importDetail.getError().get(i));
            }
        }catch (FileNotFoundException e) {
            throw new AppException(ErrorCode.FILE_NOT_FOUND);
        }catch (IOException e) {
            throw new AppException(ErrorCode.IO_EXCEPTION);
        }
        return outputPath;
    }
}
