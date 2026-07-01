package com.example.managementproject.service;

import com.example.managementproject.dto.WhDoiTuongRequest;
import com.example.managementproject.dto.WhDoiTuongResponse;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.repository.WhDoiTuongRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhDoiTuongService {
    @Autowired
    private WhDoiTuongRepository whDoiTuongRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public WhDoiTuongResponse create(WhDoiTuongRequest request) {
        WhDoiTuong doiTuong = modelMapper.map(request, WhDoiTuong.class);

        doiTuong.setSoBanAnLenhQd(0);
        doiTuong.setSoBanAnLenhQdHieuLuc(0);
        doiTuong.setThaoTacCuoi(1);
        doiTuong.setSyncVnpt(0);
        doiTuong.setTimeSyncVnpt(new Date());

        WhDoiTuong saved = whDoiTuongRepository.save(doiTuong);
        return modelMapper.map(saved, WhDoiTuongResponse.class);
    }

    @Transactional
    public WhDoiTuongResponse update(Long id, WhDoiTuongRequest request) {
        WhDoiTuong dt = whDoiTuongRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong"));
        modelMapper.map(request, dt);

        dt.setNgaySuaCuoi(new Date());
        dt.setThaoTacCuoi(2);
        dt.setSyncVnpt(0);
        dt.setTimeSyncVnpt(new Date());
        WhDoiTuong updated = whDoiTuongRepository.save(dt);

        return modelMapper.map(updated, WhDoiTuongResponse.class);
    }

    @Transactional
    public void delete(Long id){
       if(!whDoiTuongRepository.existsById(id)){
           throw new RuntimeException("Khong tim thay doi tuong");
       }
        whDoiTuongRepository.deleteById(id);
    }
}
