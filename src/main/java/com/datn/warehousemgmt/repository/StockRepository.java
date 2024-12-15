package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findBySkuCode(String skuCode);
    Stock findBySkuCodeAndWarehouseId(String skuCode, Long warehouseId);
}
