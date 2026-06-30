package com.example.managementproject.controller;

import com.example.managementproject.entity.WhBanAnHinhPhat;
import com.example.managementproject.service.WhBanAnHinhPhatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hinh-phat")
public class WhBanAnHinhPhatController {
    @Autowired
    WhBanAnHinhPhatService whBanAnHinhPhatService;

    @PostMapping
    public WhBanAnHinhPhat create(@RequestBody WhBanAnHinhPhat wd, @RequestParam Long banAnLenhQdId){
        return whBanAnHinhPhatService.create(wd, banAnLenhQdId);
    }

    @PutMapping("/{id}")
    public WhBanAnHinhPhat update(@PathVariable Long id, @RequestBody WhBanAnHinhPhat wd, @RequestParam Long banAnLenhQdId){
        return whBanAnHinhPhatService.update(id, wd, banAnLenhQdId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        whBanAnHinhPhatService.delete(id);
    }
}
