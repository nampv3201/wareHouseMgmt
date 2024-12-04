package com.datn.warehousemgmt.controller;

import com.datn.warehousemgmt.dto.ServiceResponse;
import com.datn.warehousemgmt.dto.UserDTO;
import com.datn.warehousemgmt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<ServiceResponse> createUser(@RequestBody UserDTO dto){
        return ResponseEntity.ok(userService.create(dto));
    }

    @PutMapping("/{uId}")
    public ResponseEntity<ServiceResponse> updateUser(@PathVariable Long uId, @RequestBody UserDTO dto){
        return ResponseEntity.ok(userService.update(uId, dto));
    }

    @DeleteMapping("/{uId}")
    public ResponseEntity<ServiceResponse> deleteUser(@PathVariable Long uId){
        userService.delete(uId);
        return ResponseEntity.ok(new ServiceResponse("Xóa user thành công", 200));
    }

    @GetMapping("/get")
    public String getUsers(){
        return "Lấy thông tin thành công";
    }

    @GetMapping("/get-more")
    public ResponseEntity<ServiceResponse> getAllUsers(){
        return ResponseEntity.ok(new ServiceResponse("Lấy tất cả thông tin thành công", 200));
    }
}
