package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.InvalidateToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidateTokenRepository extends JpaRepository<InvalidateToken, String> {

    Boolean existsInvalidateTokenById(String id);
}
