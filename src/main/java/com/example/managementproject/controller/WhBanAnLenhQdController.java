package com.example.managementproject.controller;

import com.example.managementproject.dto.WhBanAnLenhQdBulkRequest;
import com.example.managementproject.dto.WhBanAnLenhQdRequest;
import com.example.managementproject.dto.WhBanAnLenhQdResponse;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.service.WhBanAnLenhQdService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ban-an")
public class WhBanAnLenhQdController {
    @Autowired
    private WhBanAnLenhQdService whBanAnLenhQdService;

    @PostMapping("/sync")
    public ResponseEntity<List<WhBanAnLenhQdResponse>> addOrUpdate(@Valid @RequestBody WhBanAnLenhQdBulkRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(whBanAnLenhQdService.addOrUpdate(request));
    }

}
