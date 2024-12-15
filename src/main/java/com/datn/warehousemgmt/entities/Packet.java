package com.datn.warehousemgmt.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "packet")
public class Packet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "rfid_tag")
    String rfidTag;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "batch_id")
    Long batchId;

    @Column(name = "status")
    String status;

    @OneToMany(mappedBy = "packet", cascade = CascadeType.ALL)
    private List<LogDetail> logDetails;
}
