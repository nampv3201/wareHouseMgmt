package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {


}
