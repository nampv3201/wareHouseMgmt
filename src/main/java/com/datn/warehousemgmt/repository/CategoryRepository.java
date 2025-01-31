package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT cat FROM Category cat " +
            "WHERE cat.id IN ?1")
    List<Category> findAllByIdIn(List<Long> ids);

    @Query(value = "SELECT cat FROM Category cat " +
            "WHERE ?1 IS NULL OR ?1 = '' " +
            "OR lower(cat.name) like concat('%', coalesce(?1, ''), '%')")
    List<Category> getCategory(String search);
}
