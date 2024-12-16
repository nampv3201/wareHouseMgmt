package com.datn.warehousemgmt.processor;

import com.datn.warehousemgmt.dto.PacketDTO;
import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.dto.RfidDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.response.ImportProcessResponse;
import com.datn.warehousemgmt.entities.*;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.mapper.ProductMapper;
import com.datn.warehousemgmt.service.*;
import com.datn.warehousemgmt.utils.Constant;
import com.datn.warehousemgmt.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImportGoodsProcessor {
    private final BatchService batchService;
    private final PacketService packetService;
    private final ProductService productService;
    private final ProductLogService productLogService;
    private final WareHouseService wareHouseService;
    private final UserUtils utils;
    private final ProductMapper productMapper;

    @Transactional
    public ServiceResponse importGoods(RfidDTO request){
        Users myAccount = utils.getMyUser();
        ImportProcessResponse response = new ImportProcessResponse();
        verifyImportGoodRequest(request);
        Optional<BatchProduct> oBatch = batchService.getBatchById(request.getBatchId());
        Optional<ProductsLog> oProductsLog = productLogService.findByStatusAndBatchId(
                Constant.ProductLogStatus.PENDING.name(),
                request.getBatchId()
        );

        // Create or Import Batch
        BatchProduct batchProduct;
        ProductsLog productsLog = new ProductsLog();
        ProductLogDTO productLogDTO = new ProductLogDTO();
        if(!oBatch.isPresent()){
            batchProduct = new BatchProduct();
            batchProduct.setId(request.getBatchId());
            batchProduct.setProductSkuCode(request.getSkuCode());
            batchProduct.setManufacturerDate(request.getManufacturerDate());
            batchProduct.setExpirationDate(request.getExpirationDate());
            batchProduct.setWarehouseId(utils.getMyUser().getWarehouseId());
            batchProduct.setQuantity(1);
        }else {
            batchProduct = oBatch.get();
            if (!batchProduct.getManufacturerDate().equals(request.getManufacturerDate()) ||
                    !batchProduct.getExpirationDate().equals(request.getExpirationDate()) ||
                    !Objects.equals(batchProduct.getProductSkuCode(), request.getSkuCode())) {
                throw new AppException(ErrorCode.BATCH_INFORMATION_NOT_MATCH);
            }
            batchProduct.setQuantity(batchProduct.getQuantity() + 1);
        }
        batchProduct = batchService.save(batchProduct);

        if(!oProductsLog.isPresent()){
            productLogDTO.setBatchProduct(batchProduct);
            productLogDTO.setAction(Constant.ProductLogAction.IMPORT.name());
            productLogDTO.setStatus(Constant.ProductLogStatus.PENDING.name());
            productsLog = productLogService.createLog(productLogDTO);
        }else{
            productsLog = oProductsLog.get();
        }
        productLogDTO = productMapper.fromEntityToLogDTO(productsLog);
        // Import Packet
        PacketDTO packetDTO = new PacketDTO();
        packetDTO.setRfidTag(request.getRfidCode());
        packetDTO.setBatchId(batchProduct.getId());
        packetDTO.setQuantity(request.getQuantity());
        packetDTO.setStatus(Constant.PacketStatus.IN_STOCK.getStatus());
        Packet packet = (Packet) packetService.importPacket(packetDTO).getData();

        // Write Log
        productLogDTO.getPackets().add(packet);
        productLogDTO.setQuantity(productLogDTO.getQuantity() + 1);
        productsLog = productLogService.updateLog(productLogDTO);

        response.setBatchId(batchProduct.getId());
        response.setImportDate(LocalDate.from(productsLog.getCreatedDate()));
        response.setSkuCode(request.getSkuCode());
        response.setManufacturerDate(batchProduct.getManufacturerDate());
        response.setExpirationDate(batchProduct.getExpirationDate());
        response.setQuantity(batchProduct.getQuantity());
        response.setWarehouseName(wareHouseService.getById(myAccount.getWarehouseId()).getName());

        return new ServiceResponse(response,"Nhập kho thành công", 200);
    }

    private void verifyImportGoodRequest(RfidDTO request){
        if(request == null || request.getSkuCode() == null || request.getBatchId() == null){
            throw new AppException(ErrorCode.INVALID_RFID_TAG);
        }

        if(!productService.existProductBySKUCode(request.getSkuCode())){
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        if(packetService.checkPacket(request.getRfidCode())){
            throw new AppException(ErrorCode.PACKET_ALREADY_IMPORTED);
        }

    }
}
