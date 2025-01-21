package com.datn.warehousemgmt.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PartnerDTO {
    Long id;
    String name;
    String address;
    String phone;
    String type;
}
