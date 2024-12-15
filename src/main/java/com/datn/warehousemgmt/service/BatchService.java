package com.datn.warehousemgmt.service;

import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.entities.BatchProduct;

import java.util.Optional;

public interface BatchService {
    ServiceResponse createBatch();
    ServiceResponse updateBatch();
    Optional<BatchProduct> getBatchById(Long id);

    BatchProduct save(BatchProduct batchProduct);
}
