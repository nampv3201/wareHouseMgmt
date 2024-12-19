package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.request.ProductLogSearchRequest;
import com.datn.warehousemgmt.service.ProductLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productLog")
@RequiredArgsConstructor
public class ProductLogController {
    private final ProductLogService productLogService;

    @PutMapping("/change-status")
    public ResponseEntity<?> changeLogStatus(@RequestBody ProductLogDTO dto){
        ServiceResponse result = productLogService.changeStatus(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/find-log")
    public ResponseEntity<?> findLog(@RequestBody ProductLogSearchRequest request){
        return new ResponseEntity<>(productLogService.findLog(request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLog(@PathVariable("id") Long id){
        return new ResponseEntity<>(productLogService.getLogDetail(id), HttpStatus.OK);
    }
}
