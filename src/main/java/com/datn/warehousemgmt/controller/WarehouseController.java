package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.RfidDTO;
import com.datn.warehousemgmt.processor.ImportGoodsProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final ImportGoodsProcessor importGoodsProcessor;

    @PostMapping("/import-goods")
    public ResponseEntity<?> importGoods(@RequestBody RfidDTO request){
        return new ResponseEntity<>(importGoodsProcessor.importGoods(request), HttpStatus.OK);
    }
}
