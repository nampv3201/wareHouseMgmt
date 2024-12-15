package com.datn.warehousemgmt.service;

import com.datn.warehousemgmt.entities.Stock;

public interface StockService {
    Stock findBySkuCode(String skuCode);

    Stock findBySkuCodeAndWareHouse(String skuCode, Long warehouseId);

    Stock save(Stock stock);
}
