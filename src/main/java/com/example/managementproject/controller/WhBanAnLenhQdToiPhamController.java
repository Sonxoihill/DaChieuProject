package com.example.managementproject.controller;

import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import com.example.managementproject.service.WhBanAnLenhQdToiPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ban-an-tp")
public class WhBanAnLenhQdToiPhamController {
    @Autowired
    WhBanAnLenhQdToiPhamService whBanAnLenhQdToiPhamService;

    @PostMapping
    public WhBanAnLenhQdToiPham create(@RequestBody WhBanAnLenhQdToiPham wd, @RequestParam Long banAnLenhQdId){
        return whBanAnLenhQdToiPhamService.create(wd,banAnLenhQdId);
    }

    @PutMapping("/{id}")
    public WhBanAnLenhQdToiPham update(@PathVariable Long id, @RequestBody WhBanAnLenhQdToiPham wd,  @RequestParam Long banAnLenhQdId){
        return whBanAnLenhQdToiPhamService.update(id,wd,banAnLenhQdId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        whBanAnLenhQdToiPhamService.delete(id);
    }
}
