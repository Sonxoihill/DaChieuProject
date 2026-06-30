package com.example.managementproject.controller;

import com.example.managementproject.dto.WhBanAnLenhQdToiPhamRequest;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import com.example.managementproject.service.WhBanAnLenhQdToiPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ban-an-tp")
public class WhBanAnLenhQdToiPhamController {
    @Autowired
    WhBanAnLenhQdToiPhamService whBanAnLenhQdToiPhamService;

    @PostMapping
    public ResponseEntity<WhBanAnLenhQdToiPham> create(@RequestBody WhBanAnLenhQdToiPhamRequest request){
        return ResponseEntity.ok(whBanAnLenhQdToiPhamService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WhBanAnLenhQdToiPham> update(@PathVariable Long id, @RequestBody WhBanAnLenhQdToiPhamRequest request){
        return ResponseEntity.ok(whBanAnLenhQdToiPhamService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        whBanAnLenhQdToiPhamService.delete(id);
        return ResponseEntity.ok().build();
    }
}
