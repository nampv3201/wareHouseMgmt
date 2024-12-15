package com.datn.warehousemgmt.utils;

import com.datn.warehousemgmt.entities.Users;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import com.datn.warehousemgmt.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class UserUtils {

    private final UsersRepository usersRepository;

    public Users getMyUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = jwt.getClaim("id");

        return usersRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}
