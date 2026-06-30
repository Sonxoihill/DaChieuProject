package com.example.managementproject.service;

import com.example.managementproject.entity.WhBanAnHinhPhat;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.repository.WhBanAnHinhPhatRepository;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhBanAnHinhPhatService {
    @Autowired
    WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    WhBanAnHinhPhatRepository whBanAnHinhPhatRepository;

    public WhBanAnHinhPhat create(WhBanAnHinhPhat wd, Long banAnLenhQdId){
        WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(banAnLenhQdId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));
        wd.setBanAnLenhQd(banAn);
        wd.setThaoTacCuoi(1);
        wd.setSyncVnpt(0);
        wd.setTimeSyncVnpt(new Date());

        return whBanAnHinhPhatRepository.save(wd);
    }

    public WhBanAnHinhPhat update(Long id, WhBanAnHinhPhat wd, Long banAnLenhQdId){
        WhBanAnHinhPhat banAn = whBanAnHinhPhatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));

        banAn.setHanhViXuPhatId(wd.getHanhViXuPhatId());
        banAn.setDiaBanQuanLyCode(wd.getDiaBanQuanLyCode());

        if(banAnLenhQdId != null){
            WhBanAnLenhQd banAnQd = whBanAnLenhQdRepository.findById(banAnLenhQdId)
                    .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));
            banAn.setBanAnLenhQd(banAnQd);
        }

        banAn.setNgaySuaCuoi(new Date());
        banAn.setThaoTacCuoi(1);
        banAn.setSyncVnpt(0);
        banAn.setTimeSyncVnpt(new Date());

        return whBanAnHinhPhatRepository.save(banAn);
    }

    public void delete(Long id){
        WhBanAnHinhPhat banAnHinhPhat = whBanAnHinhPhatRepository.getById(id);
        whBanAnHinhPhatRepository.delete(banAnHinhPhat);
    }
}
