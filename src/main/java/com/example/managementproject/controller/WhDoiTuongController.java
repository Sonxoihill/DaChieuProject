package com.example.managementproject.controller;

import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.service.WhDoiTuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doi-tuong")
public class WhDoiTuongController {
    @Autowired
    private WhDoiTuongService whDoiTuongService;

    @PostMapping
    public WhDoiTuong create(@RequestBody WhDoiTuong whDoiTuong){
        return whDoiTuongService.create(whDoiTuong);
    }

    @PutMapping("/{id}")
    public WhDoiTuong update(@PathVariable Long id ,@RequestBody WhDoiTuong whDoiTuong){
        return whDoiTuongService.update(id, whDoiTuong);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        whDoiTuongService.delete(id);
    }
}
