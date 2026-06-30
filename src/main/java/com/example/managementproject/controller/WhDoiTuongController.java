package com.example.managementproject.controller;

import com.example.managementproject.dto.WhDoiTuongRequest;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.service.WhDoiTuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doi-tuong")
public class WhDoiTuongController {
    @Autowired
    private WhDoiTuongService whDoiTuongService;

    @PostMapping
    public ResponseEntity<WhDoiTuong> create(@RequestBody WhDoiTuongRequest request){
        return ResponseEntity.ok(whDoiTuongService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WhDoiTuong> update(@PathVariable Long id , @RequestBody WhDoiTuongRequest request){
        return ResponseEntity.ok(whDoiTuongService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        whDoiTuongService.delete(id);
        return ResponseEntity.ok().build();
    }
}
