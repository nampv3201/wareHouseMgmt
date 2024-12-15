package com.datn.warehousemgmt.dto;

import com.datn.warehousemgmt.entities.BatchProduct;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductLogDTO {
    Long id;
    BatchProduct batchProduct;
    String action;
    String status;
}
