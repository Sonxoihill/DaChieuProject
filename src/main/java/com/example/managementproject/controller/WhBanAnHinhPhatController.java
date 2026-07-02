package com.example.managementproject.controller;

import com.example.managementproject.dto.WhBanAnHinhPhatBulkRequest;
import com.example.managementproject.dto.WhBanAnHinhPhatRequest;
import com.example.managementproject.dto.WhBanAnHinhPhatResponse;
import com.example.managementproject.entity.WhBanAnHinhPhat;
import com.example.managementproject.service.WhBanAnHinhPhatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hinh-phat")
public class WhBanAnHinhPhatController {
    @Autowired
    WhBanAnHinhPhatService whBanAnHinhPhatService;

    @PostMapping("/sync")
    public ResponseEntity<List<WhBanAnHinhPhatResponse>> addOrUpdate(@Valid @RequestBody WhBanAnHinhPhatBulkRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(whBanAnHinhPhatService.addOrUpdate(request));
    }
}
