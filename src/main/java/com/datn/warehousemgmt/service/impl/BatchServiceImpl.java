package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.entities.BatchProduct;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.repository.BatchProductRepository;
import com.datn.warehousemgmt.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {
    private final BatchProductRepository batchProductRepository;
    @Override
    public ServiceResponse createBatch() {
        return null;
    }

    @Override
    public ServiceResponse updateBatch() {
        return null;
    }

    @Override
    public Optional<BatchProduct> getBatchById(Long id) {
        return batchProductRepository.findById(id);
    }

    @Override
    @Transactional
    public BatchProduct save(BatchProduct batchProduct) {
        return batchProductRepository.save(batchProduct);
    }
}
