package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.service.ProductLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-log")
@RequiredArgsConstructor
public class ProductLogController {
    private final ProductLogService productLogService;

    @PutMapping("/change-log-status")
    public ResponseEntity<?> changeLogStatus(@RequestBody ProductLogDTO dto){
        ServiceResponse result = new ServiceResponse(productLogService.updateLog(dto), "Đổi trạng thái thành công", 200);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
