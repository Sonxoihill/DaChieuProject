package com.example.managementproject.controller;

import com.example.managementproject.dto.WhBanAnHinhPhatRequest;
import com.example.managementproject.entity.WhBanAnHinhPhat;
import com.example.managementproject.service.WhBanAnHinhPhatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hinh-phat")
public class WhBanAnHinhPhatController {
    @Autowired
    WhBanAnHinhPhatService whBanAnHinhPhatService;

    @PostMapping
    public ResponseEntity<WhBanAnHinhPhat> create(@RequestBody WhBanAnHinhPhatRequest request){
        return ResponseEntity.ok(whBanAnHinhPhatService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WhBanAnHinhPhat> update(@PathVariable Long id, @RequestBody WhBanAnHinhPhatRequest request){
        return ResponseEntity.ok(whBanAnHinhPhatService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        whBanAnHinhPhatService.delete(id);
        return ResponseEntity.ok().build();
    }
}
