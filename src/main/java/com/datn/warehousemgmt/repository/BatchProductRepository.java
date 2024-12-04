package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.BatchProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BatchProductRepository extends JpaRepository<BatchProduct, Long> {

    @Query("SELECT bp FROM BatchProduct bp " +
            "WHERE bp.productSkuCode = ?1")
    List<BatchProduct> getAllByProductId(Long productId);
}
