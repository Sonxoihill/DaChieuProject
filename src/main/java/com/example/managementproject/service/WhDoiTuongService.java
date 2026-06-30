package com.example.managementproject.service;

import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.repository.WhDoiTuongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhDoiTuongService {
    @Autowired
    private WhDoiTuongRepository whDoiTuongRepository;

    public WhDoiTuong create(WhDoiTuong wd) {
        wd.setThaoTacCuoi(1);
        wd.setSyncVnpt(0);
        wd.setTimeSyncVnpt(new Date());
        return whDoiTuongRepository.save(wd);
    }

    public WhDoiTuong update(Long id, WhDoiTuong wd) {
        WhDoiTuong dt = whDoiTuongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong"));
        dt.setMaDinhDanh(wd.getMaDinhDanh());
        dt.setCuTruId(wd.getCuTruId());
        dt.setGioiTinh(wd.getGioiTinh());
        dt.setDanTocId(wd.getDanTocId());
        dt.setTonGiaoId(wd.getTonGiaoId());
        dt.setNhomMauId(wd.getNhomMauId());
        dt.setNgayKetThucQl(wd.getNgayKetThucQl());
        dt.setNgayBatDauQl(wd.getNgayBatDauQl());
        dt.setDsIdDc(wd.getDsIdDc());
        dt.setXoaAnTich(wd.getXoaAnTich());
        dt.setNgaySinh(wd.getNgaySinh());

        dt.setNgaySuaCuoi(new Date());
        dt.setThaoTacCuoi(2);
        dt.setSyncVnpt(0);
        dt.setTimeSyncVnpt(new Date());

        return  whDoiTuongRepository.save(dt);
    }

    public void delete(Long id){
        WhDoiTuong whDoiTuong = whDoiTuongRepository.getById(id);
        whDoiTuongRepository.delete(whDoiTuong);
    }
}
