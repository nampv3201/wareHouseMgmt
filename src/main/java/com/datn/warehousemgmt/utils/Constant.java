package com.datn.warehousemgmt.utils;

import lombok.Getter;

public class Constant {
    public static final String ADMIN_ROLE = "ADMIN";

    public enum ProductLogStatus {
        PENDING,
        CLOSED;
    }

    public enum ProductLogAction {
        IMPORT,
        EXPORT;
    }

    @Getter
    public enum PacketStatus{
        IN_STOCK("Đã nhập kho"),
        EXPORTED("Đã xuất kho");

        private final String status;

        PacketStatus(String status) {
            this.status = status;
        }

    }

    public enum MerchantType {
        SUPPLIER,
        BUYER;
    }
}
