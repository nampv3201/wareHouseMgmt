package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.dto.RfidDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.processor.ImportGoodsProcessor;
import com.datn.warehousemgmt.service.ProductLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
public class WarehouseController {
    private final ImportGoodsProcessor importGoodsProcessor;
    private final ProductLogService productLogService;

    @PostMapping("/import-goods")
    public ResponseEntity<?> importGoods(@RequestBody RfidDTO request){
        return new ResponseEntity<>(importGoodsProcessor.importGoods(request), HttpStatus.OK);
    }
}
