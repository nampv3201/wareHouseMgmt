package com.datn.warehousemgmt.mapper;

import com.datn.warehousemgmt.dto.ProductDTO;
import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.entities.Category;
import com.datn.warehousemgmt.entities.Product;
import com.datn.warehousemgmt.entities.ProductsLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

//    @Mapping(source = "categories", target = "categoryList",
//            qualifiedByName = "mapCategoryNames")
    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);

    @Named("mapCategoryNames")
    default List<String> mapCategoryNames(List<Category> categories) {
        if (categories == null) {
            return new ArrayList<>();
        }
        return categories.stream()
                .map(Category::getName)
                .toList();
    }

    default ProductLogDTO fromEntityToLogDTO(ProductsLog productsLog){
        ProductLogDTO productLogDTO = new ProductLogDTO();
        productLogDTO.setId(productsLog.getId());
        productLogDTO.setBatchProduct(productsLog.getBatchProduct());
        productLogDTO.setQuantity(productsLog.getQuantity());
        productLogDTO.setAction(productsLog.getAction());
        productLogDTO.setStatus(productsLog.getStatus());
        productLogDTO.setPackets(new ArrayList<>(productsLog.getPackets()));
        return productLogDTO;
    };
}
