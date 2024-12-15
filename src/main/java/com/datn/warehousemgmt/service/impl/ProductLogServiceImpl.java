package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.entities.ProductsLog;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.repository.ProductLogRepository;
import com.datn.warehousemgmt.service.ProductLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductLogServiceImpl implements ProductLogService {

    private final ProductLogRepository productLogRepository;
    @Override
    public Optional<ProductsLog> findByStatusAndBatchId(String status, Long batchId) {
        return productLogRepository.findByStatusAndBatchProductId(status, batchId);
    }
    @Override
    @Transactional
    public ProductsLog createLog(ProductLogDTO request) {
        ProductsLog productsLog = new ProductsLog();
        productsLog.setBatchProduct(request.getBatchProduct());
        productsLog.setAction(request.getAction());
        productsLog.setStatus(request.getStatus());
        productsLog.setQuantity(0);
        return productLogRepository.save(productsLog);
    }

    @Override
    @Transactional
    public ProductsLog updateLog(ProductLogDTO request) {
        ProductsLog productsLog = productLogRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.IMPORT_TICKET_NOT_FOUND));
        productsLog.setQuantity(request.getQuantity());
        productsLog.setStatus(request.getStatus());
        productsLog.getPackets().clear();
        productsLog.getPackets().addAll(request.getPackets());
        return productLogRepository.save(productsLog);
    }
}
