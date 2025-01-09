package com.datn.warehousemgmt.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Entity
@Table(name = "invalidate_token")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidateToken {
    @Id
    @Column(name = "id")
    String id;
    @Column(name = "expiryTime")
    Date expiryTime;
}