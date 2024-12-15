package com.datn.warehousemgmt.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "log_detail")
public class LogDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_log_id")
    private ProductsLog productsLog;

    @ManyToOne
    @JoinColumn(name = "packet_id")
    private Packet packet;
}
