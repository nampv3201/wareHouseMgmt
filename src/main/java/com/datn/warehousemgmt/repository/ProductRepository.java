package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "Select p from Product p " +
            "where (:search IS NULL " +
            "OR :search = '' " +
            "OR LOWER(p.name) LIKE concat('%', coalesce(:search, ''), '%') " +
            "OR LOWER(p.skuCode) LIKE concat('%', coalesce(:search, ''), '%'))")
    Page<Product> getProduct(@Param("search") String search, Pageable pageable);

    @Query(value = "Select p from Product p where p.skuCode =  ?1")
    Optional<Product> findProductBySkuCode(String skuCode);

    Boolean existsBySkuCode(String skuCode);
}
