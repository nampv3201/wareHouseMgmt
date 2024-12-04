package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.utils.UploadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {

    private final UploadUtils uploadUtils;

    @PostMapping("/excel-file")
    public ResponseEntity<?> uploadExcel(MultipartFile file){
        try {
            return new ResponseEntity<>(uploadUtils.saveExcelFile(file), HttpStatus.OK);
        }catch (AppException e){
            return new ResponseEntity<>(e.getMessage(), e.getErrorCode().getStatusCode());
        }
    }

}
