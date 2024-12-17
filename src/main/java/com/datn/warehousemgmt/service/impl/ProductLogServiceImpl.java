package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.request.ProductLogSearchRequest;
import com.datn.warehousemgmt.entities.Permission;
import com.datn.warehousemgmt.entities.ProductsLog;
import com.datn.warehousemgmt.entities.Users;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.mapper.ProductMapper;
import com.datn.warehousemgmt.repository.ProductLogRepository;
import com.datn.warehousemgmt.service.ProductLogService;
import com.datn.warehousemgmt.utils.Constant;
import com.datn.warehousemgmt.utils.PageUtils;
import com.datn.warehousemgmt.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductLogServiceImpl implements ProductLogService {

    private final ProductLogRepository productLogRepository;

    private final UserUtils utils;
    private final ProductMapper productMapper;

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

    @Override
    public ServiceResponse findLog(ProductLogSearchRequest request) {
        try {
            Users u = utils.getMyUser();
            Pageable pageable = PageUtils.customPage(request.getPageDTO());
            if(u.getPermissions().stream().map(Permission::getName)
                    .toList().contains(Constant.ADMIN_ROLE)){
                u.setWarehouseId(null);
            }
            Page<ProductsLog> page = productLogRepository.findLog(request.getSearch(), request.getStartDateTime(), request.getEndDateTime(),
                    request.getAction(), request.getStatus(), u.getWarehouseId(), pageable);
            return new ServiceResponse(page.getContent(), "Lấy danh sách thành công", 200,
                    page.getTotalPages(), page.getTotalElements(), page.getNumber() + 1);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ServiceResponse getLog(Long id){
        try{
            ProductsLog log = productLogRepository.findById(id)
                   .orElseThrow(() -> new AppException(ErrorCode.IMPORT_TICKET_NOT_FOUND));
            ProductLogDTO dto = productMapper.fromEntityToLogDTO(log);
            return new ServiceResponse(dto, "Lấy thông tin thành công", 200);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
