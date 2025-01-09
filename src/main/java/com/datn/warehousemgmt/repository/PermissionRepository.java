package com.datn.warehousemgmt.repository;

import com.datn.warehousemgmt.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByName(String name);

    @Query(value = "SELECT * FROM permission p " +
            "JOIN user_permission up ON up.permission_id = p.id " +
            "WHERE up.user_id = ?1", nativeQuery = true)
    List<Permission> getPermissionOfUser(Long userId);
}
