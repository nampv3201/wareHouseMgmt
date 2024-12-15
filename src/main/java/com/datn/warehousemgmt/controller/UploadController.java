package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.service.CloudinaryService;
import com.datn.warehousemgmt.utils.UploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadUtils uploadUtils;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/sku-image")
    public ResponseEntity<?> uploadAvt(@RequestParam("file") MultipartFile file) {
        try {
            String url = (String) cloudinaryService.uploadFile(file).getData();
            return ResponseEntity.ok().body("File uploaded successfully: " + url);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @PostMapping("/excel-file")
    public ResponseEntity<?> uploadExcel(MultipartFile file){
        try {
            return new ResponseEntity<>(uploadUtils.saveExcelFile(file), HttpStatus.OK);
        }catch (AppException e){
            return new ResponseEntity<>(e.getMessage(), e.getErrorCode().getStatusCode());
        }
    }

}
