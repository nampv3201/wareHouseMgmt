package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.Packet;
import com.datn.warehousemgmt.entities.ProductsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ProductLogRepository extends JpaRepository<ProductsLog, Long> {

    ProductsLog findByStatus(String status);

    Optional<ProductsLog> findByStatusAndBatchProductId(String status, Long batchId);

    @Query(value = "SELECT DISTINCT pl.id as id, " +
            "b.product_sku_code as skuCode, " +
            "p.name as name, " +
            "b.id as batchId, " +
            "pl.created_by as worker, " +
            "pl.created_date as createdDate, " +
            "pl.quantity as quantity, " +
            "pl.action as action, " +
            "pl.status as status " +
            "FROM product_log pl " +
            "JOIN batch b on pl.batch_product_id = b.id " +
            "JOIN product p on p.sku_code = b.product_sku_code " +
            "WHERE (?1 IS NULL OR ?1 = '' OR pl.batch_product_id = ?1 OR pl.created_by = ?1 " +
            "OR p.sku_code = ?1 OR p.name = ?1) " +
            "AND (?2 IS NULL OR pl.created_date >= ?2) " +
            "AND (?3 IS NULL OR pl.created_date <= ?3) " +
            "AND (?4 IS NULL OR ?4 = '' OR pl.action = ?4) " +
            "AND (?5 IS NULL OR ?5 = '' OR pl.status = ?5) " +
            "AND (?6 IS NULL OR ?6 = '' OR b.ware_house_id = ?6)", nativeQuery = true)
    Page<Map<String, Object>> findLog(String search, LocalDateTime startDate, LocalDateTime endDate,
                                         String action, String status, Long warehouseId, Pageable pageable);
}
