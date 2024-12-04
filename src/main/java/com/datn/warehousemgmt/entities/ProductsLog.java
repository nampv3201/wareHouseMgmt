package com.datn.warehousemgmt.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "product_log")
public class ProductsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "action")
    String action;

    @CreatedDate
    LocalDateTime createdDate;

    @CreatedBy
    Long createdBy;

    @OneToMany(mappedBy = "productsLog", cascade = CascadeType.ALL)
    private List<Log> logs = new ArrayList<>();

}