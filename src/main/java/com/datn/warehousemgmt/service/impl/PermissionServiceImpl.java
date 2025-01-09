package com.datn.warehousemgmt.service.impl;

import com.datn.warehousemgmt.dto.PermissionDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.entities.Permission;
import com.datn.warehousemgmt.entities.Users;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.repository.PermissionRepository;
import com.datn.warehousemgmt.repository.UsersRepository;
import com.datn.warehousemgmt.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final UsersRepository usersRepository;

    @Override
    public ServiceResponse getListPermissions() {
        try{
            return new ServiceResponse(permissionRepository.findAll(),
                    "Lấy danh sách permission thành công", 200);
        }catch(Exception e){
            throw new RuntimeException("Lấy danh sách permission thất bại: " + e.getMessage());
        }
    }

    @Override
    public ServiceResponse createPermission(PermissionDTO dto) {
        try{
            Permission p = new Permission();
            p.setName(dto.getName());
            return new ServiceResponse(permissionRepository.save(p),
                    "Tạo permission thành công", 200);
        } catch (Exception e) {
            throw new AppException(ErrorCode.CREATION_FAILED);
        }
    }

    @Override
    public ServiceResponse deletePermission(Long permissionId) {
        try{
            Permission p = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_FOUND));
            permissionRepository.delete(p);
            return new ServiceResponse("Xóa permission thành công", 200);
        } catch (Exception e) {
            throw new AppException(ErrorCode.DELETE_FAILED);
        }
    }

    @Override
    public ServiceResponse addPermissionForUser(Long permissionId, List<Long> userIds) {
        try{
            Permission p = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_FOUND));
            List<Users> users = usersRepository.findAllById(userIds);
            users.forEach(u -> {
                if (!p.getUsers().contains(u)) {
                    p.getUsers().add(u);
                }
            });
            return new ServiceResponse(permissionRepository.save(p),
                    "Thêm permission cho user thành công", 200);
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
}
