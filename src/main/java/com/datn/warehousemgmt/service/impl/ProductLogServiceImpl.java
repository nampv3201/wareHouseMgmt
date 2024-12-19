package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.request.ProductLogSearchRequest;
import com.datn.warehousemgmt.dto.response.ProductLogListResponse;
import com.datn.warehousemgmt.entities.*;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.mapper.ProductMapper;
import com.datn.warehousemgmt.repository.BatchProductRepository;
import com.datn.warehousemgmt.repository.PacketRepository;
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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductLogServiceImpl implements ProductLogService {

    private final ProductLogRepository productLogRepository;
    private final BatchProductRepository batchProductRepository;
    private final PacketRepository packetRepository;

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
        BatchProduct batchProduct = batchProductRepository.findById(request.getBatchProductId())
                        .orElseThrow(() -> new AppException(ErrorCode.BATCH_NOT_FOUND));
        productsLog.setBatchProduct(batchProduct);
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
    public ServiceResponse changeStatus(ProductLogDTO request) {
        try {
            ProductsLog productsLog = productLogRepository.findById(request.getId())
                    .orElseThrow(() -> new AppException(ErrorCode.IMPORT_TICKET_NOT_FOUND));
            if (Objects.equals(productsLog.getStatus(), Constant.ProductLogStatus.PENDING.name())) {
                productsLog.setStatus(Constant.ProductLogStatus.CLOSED.name());
            } else {
                productsLog.setStatus(Constant.ProductLogStatus.PENDING.name());
            }
            productsLog = productLogRepository.save(productsLog);
            return new ServiceResponse("Cập nhật trạng thái thành công: " + productsLog.getStatus(), 200);
        }catch (Exception e){
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
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
            Page<Map<String, Object> > page = productLogRepository.findLog(request.getSearch(),
                    request.getStartDateTime(),
                    request.getEndDateTime(),
                    request.getAction(),
                    request.getStatus(),
                    u.getWarehouseId(),
                    pageable);
            List<ProductLogListResponse> result = page.stream().map(res -> {
                List<Packet> packets = packetRepository.getPacketList((Long) res.get("id"));
                return productMapper.toSearchLog(res, packets);
            }).collect(Collectors.toList());

            return new ServiceResponse(result, "Lấy danh sách thành công", 200,
                    page.getTotalPages(), page.getTotalElements(), page.getNumber() + 1);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ServiceResponse getLogDetail(Long id){
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
