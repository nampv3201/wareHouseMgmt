package com.datn.warehousemgmt.utils;

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

    public enum PacketStatus{
        IN_STOCK("Đã nhập kho"),
        EXPORTED("Đã xuất kho");
        private String status;

        PacketStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
}
