package com.datn.warehousemgmt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/esp")
@Slf4j
@Tag(name = "API giao tiếp với phần cứng")
public class EspController {

    @Operation(summary = "Đọc thông tin thẻ RFID")
    @PostMapping("/rfid")
    public ResponseEntity<String> processRFID(@RequestBody Map<String, String> payload) {
        String cardID = payload.get("cardID");
        System.out.println("Received RFID Card ID: " + cardID);

        // Xử lý thông tin thẻ
        boolean isAuthorized = checkCardID(cardID);

        if (isAuthorized) {
            log.info("Card id: {}", cardID);
            return ResponseEntity.ok("Access granted");
        } else {
            log.info("Card id failed");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }

    private boolean checkCardID(String cardID) {
        // Kiểm tra cardID trong database
        return true; // Giả sử thẻ hợp lệ
    }
}
