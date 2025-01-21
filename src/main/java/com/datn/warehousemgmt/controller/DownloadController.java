package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.utils.DownloadUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/download")
@RequiredArgsConstructor
@Tag(name = "API download file")
public class DownloadController {

    private final DownloadUtils downloadUtils;

    @Operation(summary = "Tải file")
    @PostMapping("/template")
    public void download(HttpServletResponse response,
                         @RequestParam("name") String nameFile){
        downloadUtils.downloadImportProduct(response, nameFile);
    }

    @Operation(summary = "Tải file kết quả import")
    @PostMapping("/import-result")
    public void downloadErrorImport(HttpServletResponse response,
                         @RequestParam("name") String nameFile){
        downloadUtils.downloadImportProductError(response, nameFile);
    }
}
