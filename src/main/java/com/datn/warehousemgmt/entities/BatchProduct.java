package com.datn.warehousemgmt.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "batch")
public class BatchProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "rfid_tag")
    String rfidTag;

    @Column(name = "product_sku_code")
    String productSkuCode;

    @Column(name = "supplier_id")
    Long supplierId;

    @Column(name = "ware_house_id")
    Long warehouseId;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "manufacture_date")
    LocalDate manufacturerDate;

    @Column(name = "expiry_date")
    LocalDate expiryDate;

    @Column(name = "in_price")
    BigInteger inPrice;

    @Column(name = "out_price")
    BigInteger outPrice;

    @OneToMany(mappedBy = "batchProduct", cascade = CascadeType.ALL)
    private List<Log> logs;
}
