package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.BatchProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BatchProductRepository extends JpaRepository<BatchProduct, Long> {

    @Query("SELECT bp FROM BatchProduct bp " +
            "WHERE (?1 Is null or ?1 = '' or bp.productSkuCode = ?1) " +
            "and (?2 is null or ?2 = '' or bp.supplierId = ?2) " +
            "and (?3 is null or ?3 = '')")
    Page<BatchProduct> getAllBySkuCode(String skuCode, Long supplierId,
                                       String status, Pageable pageable);
}
