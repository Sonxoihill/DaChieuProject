package com.example.managementproject.service;

import com.example.managementproject.dto.WhDoiTuongRequest;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.repository.WhDoiTuongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhDoiTuongService {
    @Autowired
    private WhDoiTuongRepository whDoiTuongRepository;

    @Transactional
    public WhDoiTuong create(WhDoiTuongRequest request) {
        WhDoiTuong doiTuong = new WhDoiTuong();
        doiTuong.setMaDinhDanh(request.getMaDinhDanh());
        doiTuong.setCuTruId(request.getCuTruId());
        doiTuong.setNgaySinh(request.getNgaySinh());
        doiTuong.setGioiTinh(request.getGioiTinh());
        doiTuong.setDanTocId(request.getDanTocId());
        doiTuong.setTonGiaoId(request.getTonGiaoId());
        doiTuong.setNhomMauId(request.getNhomMauId());
        doiTuong.setNgayBatDauQl(request.getNgayBatDauQl());
        doiTuong.setNgayKetThucQl(request.getNgayKetThucQl());
        doiTuong.setDiaBanQuanLyCode(request.getDiaBanQuanLyCode());

        doiTuong.setSoBanAnLenhQd(0);
        doiTuong.setSoBanAnLenhQdHieuLuc(0);
        doiTuong.setThaoTacCuoi(1);
        doiTuong.setSyncVnpt(0);
        doiTuong.setTimeSyncVnpt(new Date());
        return whDoiTuongRepository.save(doiTuong);
    }

    @Transactional
    public WhDoiTuong update(Long id, WhDoiTuongRequest request) {
        WhDoiTuong dt = whDoiTuongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong"));
        dt.setMaDinhDanh(request.getMaDinhDanh());
        dt.setCuTruId(request.getCuTruId());
        dt.setGioiTinh(request.getGioiTinh());
        dt.setDanTocId(request.getDanTocId());
        dt.setTonGiaoId(request.getTonGiaoId());
        dt.setNhomMauId(request.getNhomMauId());
        dt.setNgayKetThucQl(request.getNgayKetThucQl());
        dt.setNgayBatDauQl(request.getNgayBatDauQl());
        dt.setNgaySinh(request.getNgaySinh());

        dt.setNgaySuaCuoi(new Date());
        dt.setThaoTacCuoi(2);
        dt.setSyncVnpt(0);
        dt.setTimeSyncVnpt(new Date());

        return  whDoiTuongRepository.save(dt);
    }

    @Transactional
    public void delete(Long id){
       if(!whDoiTuongRepository.existsById(id)){
           throw new RuntimeException("Khong tim thay doi tuong");
       }
        whDoiTuongRepository.deleteById(id);
    }
}
