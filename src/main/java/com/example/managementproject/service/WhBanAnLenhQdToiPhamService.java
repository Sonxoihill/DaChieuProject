package com.example.managementproject.service;

import com.example.managementproject.dto.WhBanAnLenhQdResponse;
import com.example.managementproject.dto.WhBanAnLenhQdToiPhamRequest;
import com.example.managementproject.dto.WhBanAnLenhQdToiPhamResponse;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import com.example.managementproject.repository.WhBanAnLenhQdToiPhamRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class WhBanAnLenhQdToiPhamService {
    @Autowired
    WhBanAnLenhQdToiPhamRepository whBanAnLenhQdToiPhamRepository;

    @Autowired
    WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public WhBanAnLenhQdToiPhamResponse create(WhBanAnLenhQdToiPhamRequest request) {
        WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(request.getBanAnLenhQdId())
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));

        WhBanAnLenhQdToiPham wd = modelMapper.map(request,WhBanAnLenhQdToiPham.class);

        wd.setBanAnLenhQd(banAn);
        wd.setThaoTacCuoi(1);
        wd.setSyncVnpt(0);
        wd.setTimeSyncVnpt(new Date());

        WhBanAnLenhQdToiPham saved = whBanAnLenhQdToiPhamRepository.save(wd);
        return convertToResponse(saved);
    }

    @Transactional
    public WhBanAnLenhQdToiPhamResponse update(Long id, WhBanAnLenhQdToiPhamRequest request) {
        WhBanAnLenhQdToiPham tp = whBanAnLenhQdToiPhamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));

        modelMapper.map(request,tp);

        if(request.getBanAnLenhQdId() != null){
            WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(request.getBanAnLenhQdId())
                    .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));
            tp.setBanAnLenhQd(banAn);
        }

        tp.setNgaySuaCuoi(new Date());
        tp.setThaoTacCuoi(2);
        tp.setSyncVnpt(0);
        tp.setTimeSyncVnpt(new Date());

        return convertToResponse(whBanAnLenhQdToiPhamRepository.save(tp));
    }

    public WhBanAnLenhQdToiPhamResponse convertToResponse(@RequestBody WhBanAnLenhQdToiPham tp){
        WhBanAnLenhQdToiPhamResponse res = modelMapper.map(tp, WhBanAnLenhQdToiPhamResponse.class);
        if(tp.getBanAnLenhQd() != null){
            res.setBanAnLenhQdId(tp.getBanAnLenhQd().getId());
        }
        return res;
    }

    @Transactional
    public void delete(Long id) {
        if(!whBanAnLenhQdToiPhamRepository.existsById(id)){
            throw new RuntimeException("Khong tim thay ban an toi pham");
        }
        whBanAnLenhQdToiPhamRepository.deleteById(id);
    }
}
