package com.datn.warehousemgmt.dto;

import com.datn.warehousemgmt.utils.PageDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    String name;
    String email;
    LocalDate birthday;
    String username;
    String password;
    String phoneNumber;
    Integer gender;
}
