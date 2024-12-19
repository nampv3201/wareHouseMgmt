package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.request.BatchRequest;
import com.datn.warehousemgmt.entities.BatchProduct;
import com.datn.warehousemgmt.repository.BatchProductRepository;
import com.datn.warehousemgmt.service.BatchService;
import com.datn.warehousemgmt.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public ServiceResponse getBatchBySku(BatchRequest request) {
        Pageable pageable = PageUtils.customPage(request.getPageDTO());
        try {
            Page<BatchProduct> page = batchProductRepository.getAllBySkuCode(request.getProductSkuCode(), request.getSupplierId(),
                    request.getStatus(), pageable);
            return new ServiceResponse(page.getContent(), "Lấy thông tin lô thành công", 200,
                    page.getTotalPages(), page.getTotalElements(), page.getNumber() + 1);
        }catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
