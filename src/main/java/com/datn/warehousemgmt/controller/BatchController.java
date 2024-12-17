package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.request.BatchRequest;
import com.datn.warehousemgmt.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batch")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    @PostMapping("/findBatch")
    public ResponseEntity<?> findBatch(@RequestBody BatchRequest request){
        return new ResponseEntity<>(batchService.getBatchBySku(request), HttpStatus.OK);
    }
}
