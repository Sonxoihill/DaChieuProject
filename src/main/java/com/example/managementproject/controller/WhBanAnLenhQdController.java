package com.example.managementproject.controller;

import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.service.WhBanAnLenhQdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ban-an")
public class WhBanAnLenhQdController {
    @Autowired
    private WhBanAnLenhQdService whBanAnLenhQdService;

    @PostMapping
    public WhBanAnLenhQd  create(@RequestBody WhBanAnLenhQd wd, @RequestParam Long id){
        return whBanAnLenhQdService.create(wd,id);
    }

    @PutMapping("/{id}")
    public  WhBanAnLenhQd update(@PathVariable Long id,@RequestBody WhBanAnLenhQd wd, @RequestParam Long doituongId){
        return whBanAnLenhQdService.update(id,wd,doituongId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        whBanAnLenhQdService.delete(id);
    }
}
