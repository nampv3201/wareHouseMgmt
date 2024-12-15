package com.datn.warehousemgmt.dto;

import com.datn.warehousemgmt.entities.Packet;
import com.datn.warehousemgmt.entities.ProductsLog;
import lombok.Data;

import java.math.BigInteger;

@Data
public class LogDetailDTO {
    private Long id;
    private ProductsLog productsLog;
    private Packet packet;
}
