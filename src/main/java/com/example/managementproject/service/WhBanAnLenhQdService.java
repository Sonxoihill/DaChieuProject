package com.example.managementproject.service;

import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import com.example.managementproject.repository.WhDoiTuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhBanAnLenhQdService {
    @Autowired
    private WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    private WhDoiTuongRepository whDoiTuongRepository;

    public WhBanAnLenhQd create(WhBanAnLenhQd banAn, Long doituongId){
        WhDoiTuong whDoiTuong = whDoiTuongRepository.findById(doituongId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong"));

        banAn.setDoiTuong(whDoiTuong);
        banAn.setThaoTacCuoi(1);
        banAn.setSyncVnpt(0);
        banAn.setTimeSyncVnpt(new Date());

        return whBanAnLenhQdRepository.save(banAn);
    }

    public WhBanAnLenhQd update(Long id, WhBanAnLenhQd banAn, Long doituongId){
        WhBanAnLenhQd whBanAn = whBanAnLenhQdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an qd"));

        whBanAn.setCoBanAnHinhSu(banAn.getCoBanAnHinhSu());
        whBanAn.setDienId(banAn.getDienId());
        whBanAn.setNgayVaoDien(banAn.getNgayVaoDien());
        whBanAn.setNgayKetThucDien(banAn.getNgayKetThucDien());
        whBanAn.setTinhTrangQuanLy(banAn.getTinhTrangQuanLy());
        whBanAn.setDiaBanQuanLyCode(banAn.getDiaBanQuanLyCode());

        if(doituongId != null){
            WhDoiTuong dt = whDoiTuongRepository.findById(doituongId)
                    .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong co ID: " + doituongId));
            whBanAn.setDoiTuong(dt);
        }
        whBanAn.setNgaySuaCuoi(new Date());
        whBanAn.setThaoTacCuoi(2);
        whBanAn.setSyncVnpt(0);
        whBanAn.setTimeSyncVnpt(new Date());

        return whBanAnLenhQdRepository.save(whBanAn);
    }

    public void delete(Long id){
        WhBanAnLenhQd whBanAn = whBanAnLenhQdRepository.getById(id);
        whBanAnLenhQdRepository.delete(whBanAn);
    }
}
