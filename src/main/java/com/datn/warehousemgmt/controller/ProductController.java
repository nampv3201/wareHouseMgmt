package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.ProductDTO;
import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.request.ImportRequest;
import com.datn.warehousemgmt.dto.request.ProductRequest;
import com.datn.warehousemgmt.exception.AppException;
import com.datn.warehousemgmt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/genId")
    public ResponseEntity<?> generateId(){
        return new ResponseEntity<>(productService.generateSKUCode(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") String skuCode){
        return new ResponseEntity<>(productService.getOne(skuCode), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest dto){
        return new ResponseEntity<>(productService.createProduct(dto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest dto){
        return new ResponseEntity<>(productService.updateProduct(dto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestBody ProductRequest dto){
        return new ResponseEntity<>(productService.deleteProduct(dto), HttpStatus.OK);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importProduct(@RequestBody ImportRequest request){
        return new ResponseEntity<>(productService.importProduct(request.getFilename()),
                HttpStatus.OK);
    }

    @PostMapping("/get")
    public ResponseEntity<?> getProduct(@RequestBody ProductRequest request){
        return new ResponseEntity<>(productService.getProduct(request),
                HttpStatus.OK);
    }
}
