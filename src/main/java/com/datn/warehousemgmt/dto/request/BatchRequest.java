package com.datn.warehousemgmt.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BatchRequest {
    Long id;
    String productSkuCode;
    Integer quantity;
    LocalDate manufacturerDate;
    LocalDate expirationDate;
    Long warehouseId;
}
