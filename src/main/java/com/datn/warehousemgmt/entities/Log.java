package com.datn.warehousemgmt.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "log_batch")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_log_id")
    private ProductsLog productsLog;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private BatchProduct batchProduct;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "totalCost")
    BigInteger totalCost;
}
