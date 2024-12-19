package com.datn.warehousemgmt.service;

import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.request.ProductLogSearchRequest;
import com.datn.warehousemgmt.entities.ProductsLog;

import java.util.Optional;

public interface ProductLogService {
    ProductsLog createLog(ProductLogDTO request);

    ProductsLog updateLog(ProductLogDTO request);

    ServiceResponse changeStatus(ProductLogDTO request);

    Optional<ProductsLog> findByStatusAndBatchId(String status, Long batchId);

    ServiceResponse findLog(ProductLogSearchRequest request);

    ServiceResponse getLogDetail(Long id);
}