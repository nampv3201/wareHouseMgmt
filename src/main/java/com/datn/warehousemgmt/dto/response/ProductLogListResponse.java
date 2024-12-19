package com.datn.warehousemgmt.dto.response;

import com.datn.warehousemgmt.entities.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLogListResponse {
    Long id;
    String skuCode;
    String productName;
    Long batchProductId;
    Integer quantity;
    String action;
    String status;
}
