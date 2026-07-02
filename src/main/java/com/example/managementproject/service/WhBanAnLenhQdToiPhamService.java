package com.example.managementproject.service;

import com.example.managementproject.dto.WhBanAnLenhQdResponse;
import com.example.managementproject.dto.WhBanAnLenhQdToiPhamBulkRequest;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.managementproject.constant.AppConstants.*;

@Service
public class WhBanAnLenhQdToiPhamService {
    @Autowired
    WhBanAnLenhQdToiPhamRepository whBanAnLenhQdToiPhamRepository;

    @Autowired
    WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<WhBanAnLenhQdToiPhamResponse> addOrUpdate(WhBanAnLenhQdToiPhamBulkRequest bulkRequest) {
        List<WhBanAnLenhQdToiPham> entitiesToSave = new ArrayList<>();

        for(WhBanAnLenhQdToiPhamRequest dto : bulkRequest.getData()){
            Optional<WhBanAnLenhQdToiPham> existingEntity = whBanAnLenhQdToiPhamRepository.findByBanAnLenhQdIdAndToiPhamId(dto.getBanAnLenhQdId(), dto.getToiPhamId());

            WhBanAnLenhQdToiPham toiPham;
            Date now = new Date();
            if(existingEntity.isPresent()){
                toiPham = existingEntity.get();
                modelMapper.map(dto, toiPham);
                toiPham.setThaoTacCuoi(THAO_TAC_SUA);
                toiPham.setNgaySuaCuoi(now);
            } else {
                WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(dto.getBanAnLenhQdId())
                        .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));
                toiPham = modelMapper.map(dto, WhBanAnLenhQdToiPham.class);
                toiPham.setBanAnLenhQd(banAn);
                toiPham.setThaoTacCuoi(THAO_TAC_THEM);
            }

            toiPham.setSyncVnpt(SYNC_STATUS_PENDING);
            toiPham.setTimeSyncVnpt(now);
            entitiesToSave.add(toiPham);
        }

        List<WhBanAnLenhQdToiPham> savedEntities = whBanAnLenhQdToiPhamRepository.saveAll(entitiesToSave);
        return savedEntities.stream()
                .map(entity -> {
                    WhBanAnLenhQdToiPhamResponse res = modelMapper.map(entity, WhBanAnLenhQdToiPhamResponse.class);
                    if(entity.getBanAnLenhQd() != null) res.setBanAnLenhQdId(entity.getBanAnLenhQd().getId());
                    return res;
                }).collect(Collectors.toList());
    }

}
