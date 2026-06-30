package com.example.managementproject.service;

import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import com.example.managementproject.repository.WhBanAnLenhQdToiPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhBanAnLenhQdToiPhamService {
    @Autowired
    WhBanAnLenhQdToiPhamRepository whBanAnLenhQdToiPhamRepository;

    @Autowired
    WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    public WhBanAnLenhQdToiPham create(WhBanAnLenhQdToiPham wd, Long banAnLenhQdId) {
        WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(banAnLenhQdId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));

        wd.setBanAnLenhQd(banAn);
        wd.setThaoTacCuoi(1);
        wd.setSyncVnpt(0);
        wd.setTimeSyncVnpt(new Date());

        return whBanAnLenhQdToiPhamRepository.save(wd);
    }

    public WhBanAnLenhQdToiPham update(Long id, WhBanAnLenhQdToiPham wd, Long banAnLenhQdId) {
        WhBanAnLenhQdToiPham tp = whBanAnLenhQdToiPhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));

        tp.setToiPhamId(wd.getToiPhamId());
        tp.setDiaBanQuanLyCode(wd.getDiaBanQuanLyCode());

        if(banAnLenhQdId!=null){
            WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(banAnLenhQdId)
                    .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));
            tp.setBanAnLenhQd(banAn);
        }

        tp.setNgaySuaCuoi(new Date());
        tp.setThaoTacCuoi(2);
        tp.setSyncVnpt(0);
        tp.setTimeSyncVnpt(new Date());

        return whBanAnLenhQdToiPhamRepository.save(tp);
    }

    public void delete(Long id) {
        WhBanAnLenhQdToiPham toiPham = whBanAnLenhQdToiPhamRepository.getById(id);
        whBanAnLenhQdToiPhamRepository.delete(toiPham);
    }
}
