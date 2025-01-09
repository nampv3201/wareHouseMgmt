package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.ServiceResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/esp")
@Slf4j
@Tag(name = "API giao tiếp với phần cứng")
public class EspController {

    @Value("${esp32.base.url}")
    private String espBaseUrl;

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

    @Operation(summary = "Gửi hành động muốn thực hiện tới module mạch")
    @PostMapping("/send-action")
    public ResponseEntity<?> sendAction(@RequestBody Map<String, String> payload) {
        try {
            String token = ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTokenValue();
            HttpClient httpClient = HttpClient.newHttpClient();
            payload.put("token", token);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://192.168.1.134:81/action"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(payload)))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return ResponseEntity.ok(new ServiceResponse("Task sent to ESP32: " + response.body(), 200));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error communicating with ESP32: " + e.getMessage());
        }
    }

    private boolean checkCardID(String cardID) {
        // Kiểm tra cardID trong database
        return true; // Giả sử thẻ hợp lệ
    }
}
