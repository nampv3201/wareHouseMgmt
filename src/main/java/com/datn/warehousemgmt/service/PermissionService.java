package com.datn.warehousemgmt.service;

import com.datn.warehousemgmt.dto.PermissionDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;

import java.util.List;

public interface PermissionService {

    ServiceResponse getListPermissions();

    ServiceResponse createPermission(PermissionDTO dto);

    ServiceResponse deletePermission(Long permissionId);

    ServiceResponse addPermissionForUser(Long permissionId, List<Long> userIds);
}

