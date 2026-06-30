package com.example.managementproject.service;

import com.example.managementproject.dto.WhBanAnLenhQdRequest;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import com.example.managementproject.repository.WhDoiTuongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhBanAnLenhQdService {
    @Autowired
    private WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    private WhDoiTuongRepository whDoiTuongRepository;

    @Transactional
    public WhBanAnLenhQd create(WhBanAnLenhQdRequest request){
        WhDoiTuong whDoiTuong = whDoiTuongRepository.findById(request.getDoiTuongId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong"));

        WhBanAnLenhQd banAn = new WhBanAnLenhQd();
        banAn.setDoiTuong(whDoiTuong);
        banAn.setCoBanAnHinhSu(request.getCoBanAnHinhSu());
        banAn.setDienId(request.getDienId());
        banAn.setNgayVaoDien(request.getNgayVaoDien());
        banAn.setNgayKetThucDien(request.getNgayKetThucDien());
        banAn.setTinhTrangQuanLy(request.getTinhTrangQuanLy());
        banAn.setDiaBanQuanLyCode(request.getDiaBanQuanLyCode());

        banAn.setThaoTacCuoi(1);
        banAn.setSyncVnpt(0);
        banAn.setTimeSyncVnpt(new Date());

        return whBanAnLenhQdRepository.save(banAn);
    }

    @Transactional
    public WhBanAnLenhQd update(Long id, WhBanAnLenhQdRequest request){
        WhBanAnLenhQd whBanAn = whBanAnLenhQdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an qd"));

        whBanAn.setCoBanAnHinhSu(request.getCoBanAnHinhSu());
        whBanAn.setDienId(request.getDienId());
        whBanAn.setNgayVaoDien(request.getNgayVaoDien());
        whBanAn.setNgayKetThucDien(request.getNgayKetThucDien());
        whBanAn.setTinhTrangQuanLy(request.getTinhTrangQuanLy());
        whBanAn.setDiaBanQuanLyCode(request.getDiaBanQuanLyCode());

        if(request.getDoiTuongId() != null){
            WhDoiTuong dt = whDoiTuongRepository.findById(request.getDoiTuongId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong co ID: " + request.getDoiTuongId()));
            whBanAn.setDoiTuong(dt);
        }
        whBanAn.setNgaySuaCuoi(new Date());
        whBanAn.setThaoTacCuoi(2);
        whBanAn.setSyncVnpt(0);
        whBanAn.setTimeSyncVnpt(new Date());

        return whBanAnLenhQdRepository.save(whBanAn);
    }

    @Transactional
    public void delete(Long id){
        if(!whBanAnLenhQdRepository.existsById(id)){
            throw new RuntimeException("Khong tim thay ban an qd");
        }
        whBanAnLenhQdRepository.deleteById(id);
    }
}
