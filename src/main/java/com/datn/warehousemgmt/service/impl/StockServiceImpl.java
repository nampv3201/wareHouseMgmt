package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.entities.Stock;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.repository.StockRepository;
import com.datn.warehousemgmt.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Stock findBySkuCode(String skuCode) {
        try{
            return stockRepository.findBySkuCode(skuCode);
        }catch (Exception e){
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public Stock findBySkuCodeAndWareHouse(String skuCode, Long warehouseId) {
        try{
            return stockRepository.findBySkuCodeAndWarehouseId(skuCode, warehouseId);
        }catch (Exception e){
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public Stock save(Stock stock) {
        Stock mStock = new Stock();
        BeanUtils.copyProperties(stock, mStock);
        return stockRepository.save(mStock);
    }
}
