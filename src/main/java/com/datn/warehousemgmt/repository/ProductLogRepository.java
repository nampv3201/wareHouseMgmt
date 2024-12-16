package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.ProductsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProductLogRepository extends JpaRepository<ProductsLog, Long> {

    ProductsLog findByStatus(String status);

    Optional<ProductsLog> findByStatusAndBatchProductId(String status, Long batchId);

    @Query(value = "SELECT pl FROM ProductsLog pl " +
            "JOIN BatchProduct b ON pl.batchProduct = b " +
            "WHERE (?1 IS NULL OR ?1 = '' OR pl.batchProduct.id = ?1 OR pl.createdBy = ?1) " +
            "AND (?2 IS NULL OR pl.createdDate >= ?2) " +
            "AND (?3 IS NULL OR pl.createdDate <= ?3) " +
            "AND (?4 IS NULL OR ?4 = '' OR pl.action = ?4) " +
            "AND (?5 IS NULL OR ?5 = '' OR pl.status = ?5) " +
            "AND (?6 IS NULL OR ?6 = '' OR b.warehouseId = ?6)")
    Page<ProductsLog> findLog(String search, LocalDateTime startDate, LocalDateTime endDate,
                              String action, String status, Long warehouseId, Pageable pageable);
}
