package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.ProductLogDTO;
import com.datn.warehousemgmt.dto.RfidDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.processor.ImportGoodsProcessor;
import com.datn.warehousemgmt.service.PacketService;
import com.datn.warehousemgmt.service.ProductLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor
@Tag(name = "API quản lý kho")
public class WarehouseController {
    private final ImportGoodsProcessor importGoodsProcessor;
    private final PacketService packetService;
    private final SimpMessagingTemplate messagingTemplate;

    @Operation(summary = "Nhập kho")
    @PostMapping("/import-goods")
    public ResponseEntity<?> importGoods(@RequestBody RfidDTO request){
        return new ResponseEntity<>(importGoodsProcessor.importGoods(request), HttpStatus.OK);
    }

    @Operation(summary = "Xuất kho")
    @PostMapping("/export-goods")
    public ResponseEntity<?> exportGoods(@RequestBody RfidDTO request){
        return new ResponseEntity<>(importGoodsProcessor.exportGoods(request), HttpStatus.OK);
    }

    @Operation(summary = "Nhập/xuất kho")
    @PostMapping("/do-task")
    public ResponseEntity<?> doTask(@RequestBody RfidDTO request){
        ServiceResponse res = importGoodsProcessor.executeTask(request);

        messagingTemplate.convertAndSend("/topic/product/log", res);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Đổi trạng thái kiện hàng")
    @PutMapping("/change-packet-status")
    public ResponseEntity<?> changeStatus(@RequestBody RfidDTO request){
        ServiceResponse res = packetService.updatePacket(request.getRfidCode());
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
