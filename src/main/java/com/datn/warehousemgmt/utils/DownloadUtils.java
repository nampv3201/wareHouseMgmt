package com.datn.warehousemgmt.utils;

import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.exception.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.*;

@Component
@RequiredArgsConstructor
public class DownloadUtils {

    @Value("${excel.template.folder}")
    private String templateFolder;

    @Value("${upload.result.folder}")
    private String importErrorFolder;

    public void download(HttpServletResponse response, File file) throws Exception {
        if (!file.exists()){
            throw new Exception();
        }
       InputStream inputStream = null;
        try {
            String nameFile = file.getName().replaceAll("_[a-f0-9\\-]{36}(?=\\.xlsx$)", "");
            byte[] data = FileUtils.readFileToByteArray(file);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition","attachment;filename="+nameFile);
            response.setContentLength(data.length);
            inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            FileCopyUtils.copy(inputStream,response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            try {
                response.sendRedirect("/admin");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void downloadImportProduct(HttpServletResponse response, String filename){
        File file = new File(templateFolder + filename);
        try {
            download(response, file);
        }catch (Exception e) {
            throw new AppException(ErrorCode.IO_EXCEPTION);
        }
    }

    public void downloadImportProductError(HttpServletResponse response, String filename){
        File file = new File(importErrorFolder + filename);
        try {
            download(response, file);
        }catch (Exception e) {
            throw new AppException(ErrorCode.IO_EXCEPTION);
        }
    }
}
