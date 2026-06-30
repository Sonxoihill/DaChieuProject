package com.example.managementproject.service;

import com.example.managementproject.dto.WhBanAnLenhQdToiPhamRequest;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import com.example.managementproject.repository.WhBanAnLenhQdToiPhamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhBanAnLenhQdToiPhamService {
    @Autowired
    WhBanAnLenhQdToiPhamRepository whBanAnLenhQdToiPhamRepository;

    @Autowired
    WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Transactional
    public WhBanAnLenhQdToiPham create(WhBanAnLenhQdToiPhamRequest request) {
        WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(request.getBanAnLenhQdId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));

        WhBanAnLenhQdToiPham wd = new WhBanAnLenhQdToiPham();
        wd.setBanAnLenhQd(banAn);
        wd.setToiPhamId(request.getToiPhamId());
        wd.setDiaBanQuanLyCode(request.getDiaBanQuanLyCode());

        wd.setThaoTacCuoi(1);
        wd.setSyncVnpt(0);
        wd.setTimeSyncVnpt(new Date());

        return whBanAnLenhQdToiPhamRepository.save(wd);
    }

    @Transactional
    public WhBanAnLenhQdToiPham update(Long id, WhBanAnLenhQdToiPhamRequest request) {
        WhBanAnLenhQdToiPham tp = whBanAnLenhQdToiPhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));

        tp.setToiPhamId(request.getToiPhamId());
        tp.setDiaBanQuanLyCode(request.getDiaBanQuanLyCode());

        if(request.getBanAnLenhQdId() != null){
            WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(request.getBanAnLenhQdId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));
            tp.setBanAnLenhQd(banAn);
        }

        tp.setNgaySuaCuoi(new Date());
        tp.setThaoTacCuoi(2);
        tp.setSyncVnpt(0);
        tp.setTimeSyncVnpt(new Date());

        return whBanAnLenhQdToiPhamRepository.save(tp);
    }

    @Transactional
    public void delete(Long id) {
        if(!whBanAnLenhQdToiPhamRepository.existsById(id)){
            throw new RuntimeException("Khong tim thay ban an toi pham");
        }
        whBanAnLenhQdToiPhamRepository.deleteById(id);
    }
}
