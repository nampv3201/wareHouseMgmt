package com.datn.warehousemgmt.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    String name;
    String email;
    Date birthday;
    String username;
    String password;
    String phoneNumber;
    Integer gender;
}
