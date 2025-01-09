package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping()
    public ResponseEntity<?> getPermissionById() {
        return ResponseEntity.ok(permissionService.getListPermissions());
    }
}
