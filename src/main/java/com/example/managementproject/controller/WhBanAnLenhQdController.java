package com.example.managementproject.controller;

import com.example.managementproject.dto.WhBanAnLenhQdRequest;
import com.example.managementproject.dto.WhBanAnLenhQdResponse;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.service.WhBanAnLenhQdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ban-an")
public class WhBanAnLenhQdController {
    @Autowired
    private WhBanAnLenhQdService whBanAnLenhQdService;

    @PostMapping
    public ResponseEntity<WhBanAnLenhQdResponse> create(@RequestBody WhBanAnLenhQdRequest request){
        return ResponseEntity.ok(whBanAnLenhQdService.create(request));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<WhBanAnLenhQdResponse> update(@PathVariable Long id,@RequestBody WhBanAnLenhQdRequest request){
        return ResponseEntity.ok(whBanAnLenhQdService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        whBanAnLenhQdService.delete(id);
        return ResponseEntity.ok().build();
    }
}
