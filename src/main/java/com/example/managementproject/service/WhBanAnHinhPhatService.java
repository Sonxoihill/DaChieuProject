package com.example.managementproject.service;

import com.example.managementproject.dto.WhBanAnHinhPhatRequest;
import com.example.managementproject.entity.WhBanAnHinhPhat;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.repository.WhBanAnHinhPhatRepository;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhBanAnHinhPhatService {
    @Autowired
    WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    WhBanAnHinhPhatRepository whBanAnHinhPhatRepository;

    @Transactional
    public WhBanAnHinhPhat create(WhBanAnHinhPhatRequest request) {
        WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(request.getBanAnLenhQdId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an hinh phat"));

        WhBanAnHinhPhat wd = new WhBanAnHinhPhat();
        wd.setBanAnLenhQd(banAn);
        wd.setHanhViXuPhatId(request.getHanhViXuPhatId());
        wd.setDiaBanQuanLyCode(request.getDiaBanQuanLyCode());

        wd.setThaoTacCuoi(1);
        wd.setSyncVnpt(0);
        wd.setTimeSyncVnpt(new Date());

        return whBanAnHinhPhatRepository.save(wd);
    }

    @Transactional
    public WhBanAnHinhPhat update(Long id, WhBanAnHinhPhatRequest request){
        WhBanAnHinhPhat banAn = whBanAnHinhPhatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));

        banAn.setHanhViXuPhatId(request.getHanhViXuPhatId());
        banAn.setDiaBanQuanLyCode(request.getDiaBanQuanLyCode());

        if(request.getBanAnLenhQdId() != null){
            WhBanAnLenhQd banAnQd = whBanAnLenhQdRepository.findById(request.getBanAnLenhQdId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));
            banAn.setBanAnLenhQd(banAnQd);
        }

        banAn.setNgaySuaCuoi(new Date());
        banAn.setThaoTacCuoi(2);
        banAn.setSyncVnpt(0);
        banAn.setTimeSyncVnpt(new Date());

        return whBanAnHinhPhatRepository.save(banAn);
    }

    @Transactional
    public void delete(Long id){
        if(!whBanAnHinhPhatRepository.existsById(id)){
            throw new RuntimeException("Khong tim thay ban an ");
        }
        whBanAnHinhPhatRepository.deleteById(id);
    }
}
