package com.example.managementproject.controller;

import com.example.managementproject.dto.WhBanAnLenhQdToiPhamBulkRequest;
import com.example.managementproject.dto.WhBanAnLenhQdToiPhamRequest;
import com.example.managementproject.dto.WhBanAnLenhQdToiPhamResponse;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import com.example.managementproject.service.WhBanAnLenhQdToiPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ban-an-tp")
public class WhBanAnLenhQdToiPhamController {
    @Autowired
    WhBanAnLenhQdToiPhamService whBanAnLenhQdToiPhamService;

    @PostMapping("/sync")
    public ResponseEntity<String> addOrUpdate(@Valid @RequestBody WhBanAnLenhQdToiPhamBulkRequest request){
        whBanAnLenhQdToiPhamService.addOrUpdate(request);
        return ResponseEntity.ok("Dong bo danh sach ban an toi pham thanh cong");
    }

}
