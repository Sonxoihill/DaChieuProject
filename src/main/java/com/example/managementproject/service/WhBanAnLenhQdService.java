package com.example.managementproject.service;

import com.example.managementproject.dto.WhBanAnLenhQdRequest;
import com.example.managementproject.dto.WhBanAnLenhQdResponse;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhDoiTuong;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import com.example.managementproject.repository.WhDoiTuongRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WhBanAnLenhQdService {
    @Autowired
    private WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    private WhDoiTuongRepository whDoiTuongRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public WhBanAnLenhQdResponse create(WhBanAnLenhQdRequest request){
        WhDoiTuong whDoiTuong = whDoiTuongRepository.findById(request.getDoiTuongId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong"));

        WhBanAnLenhQd banAn = modelMapper.map(request, WhBanAnLenhQd.class);

        banAn.setDoiTuong(whDoiTuong);
        banAn.setThaoTacCuoi(1);
        banAn.setSyncVnpt(0);
        banAn.setTimeSyncVnpt(new Date());

        WhBanAnLenhQd saved = whBanAnLenhQdRepository.save(banAn);
        return convertToResponse(saved);
    }

    @Transactional
    public WhBanAnLenhQdResponse update(Long id, WhBanAnLenhQdRequest request){
        WhBanAnLenhQd whBanAn = whBanAnLenhQdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an qd"));

        modelMapper.map(request, whBanAn);

        if(request.getDoiTuongId() != null){
            WhDoiTuong dt = whDoiTuongRepository.findById(request.getDoiTuongId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong co ID: " + request.getDoiTuongId()));
            whBanAn.setDoiTuong(dt);
        }
        whBanAn.setNgaySuaCuoi(new Date());
        whBanAn.setThaoTacCuoi(2);
        whBanAn.setSyncVnpt(0);
        whBanAn.setTimeSyncVnpt(new Date());
        WhBanAnLenhQd updated = whBanAnLenhQdRepository.save(whBanAn);

        return convertToResponse(updated);
    }

    private WhBanAnLenhQdResponse convertToResponse(WhBanAnLenhQd whBanAn){
        WhBanAnLenhQdResponse res = modelMapper.map(whBanAn, WhBanAnLenhQdResponse.class);
        if(whBanAn.getDoiTuong() != null){
            res.setDoiTuongId(whBanAn.getDoiTuong().getId());
        }
        return res;
    }

    @Transactional
    public void delete(Long id){
        if(!whBanAnLenhQdRepository.existsById(id)){
            throw new RuntimeException("Khong tim thay ban an qd");
        }
        whBanAnLenhQdRepository.deleteById(id);
    }
}
