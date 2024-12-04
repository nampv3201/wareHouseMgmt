package com.datn.warehousemgmt.service;

import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface UserService{

    ServiceResponse create(UserDTO dto);

    ServiceResponse update(Long userId, UserDTO dto);

    ServiceResponse delete(Long uId);
}
