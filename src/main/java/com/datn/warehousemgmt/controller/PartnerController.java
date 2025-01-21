package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.request.PartnerSearchRequest;
import com.datn.warehousemgmt.service.PartnerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
@Tag(name = "API Quản lý đối tác")
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping()
    public ResponseEntity<?> getPartner(@RequestParam(value = "name", required = false) String name){
        return ResponseEntity.ok(partnerService.getPartnerList(name));
    }

    @PostMapping()
    public ResponseEntity<?> findPartner(@RequestBody PartnerSearchRequest request){
        return ResponseEntity.ok(partnerService.findPartnerList(request));
    }
}
