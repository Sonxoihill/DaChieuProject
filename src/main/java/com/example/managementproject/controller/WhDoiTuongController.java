package com.example.managementproject.controller;

import com.example.managementproject.dto.WhDoiTuongBulkRequest;
import com.example.managementproject.dto.WhDoiTuongRequest;
import com.example.managementproject.dto.WhDoiTuongResponse;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.service.WhDoiTuongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doi-tuong")
public class WhDoiTuongController {
    @Autowired
    private WhDoiTuongService whDoiTuongService;

    @PostMapping("/sync")
    public ResponseEntity<List<WhDoiTuongResponse>> addOrUpdate(@Valid @RequestBody WhDoiTuongBulkRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(whDoiTuongService.addOrUpdate(request));
    }


}
