package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.ProductsLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductLogRepository extends JpaRepository<ProductsLog, Long> {

    ProductsLog findByStatus(String status);

    Optional<ProductsLog> findByStatusAndBatchProductId(String status, Long batchId);
}
