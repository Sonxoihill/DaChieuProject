package com.example.managementproject.service;

import com.example.managementproject.dto.WhBanAnHinhPhatResponse;
import com.example.managementproject.dto.WhBanAnLenhQdBulkRequest;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.managementproject.constant.AppConstants.*;

@Service
public class WhBanAnLenhQdService {
    @Autowired
    private WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    private WhDoiTuongRepository whDoiTuongRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<WhBanAnLenhQdResponse> addOrUpdate(WhBanAnLenhQdBulkRequest bulkRequest){
        List<WhBanAnLenhQd> entitiesToSave = new ArrayList<>();
        for(WhBanAnLenhQdRequest dto : bulkRequest.getData()){
            Optional<WhBanAnLenhQd> existingEntity = whBanAnLenhQdRepository.findByDoiTuongIdAndDienId(dto.getDoiTuongId(), dto.getDienId());

            WhBanAnLenhQd banAn;
            Date now = new Date();
            if(existingEntity.isPresent()){
                banAn = existingEntity.get();
                banAn.setNgaySuaCuoi(now);
                banAn.setThaoTacCuoi(THAO_TAC_SUA);
            } else {
                WhDoiTuong doiTuong = whDoiTuongRepository.findById(dto.getDoiTuongId())
                        .orElseThrow(() -> new RuntimeException("Khong tim thay doi tuong"));
                banAn = modelMapper.map(dto, WhBanAnLenhQd.class);
                banAn.setDoiTuong(doiTuong);
                banAn.setThaoTacCuoi(THAO_TAC_THEM);
            }

            banAn.setSyncVnpt(SYNC_STATUS_PENDING);
            banAn.setTimeSyncVnpt(now);
            entitiesToSave.add(banAn);
        }

        List<WhBanAnLenhQd>  savedEntities = whBanAnLenhQdRepository.saveAll(entitiesToSave);

        return savedEntities.stream()
                .map(entity -> {
                    WhBanAnLenhQdResponse res = modelMapper.map(entity, WhBanAnLenhQdResponse.class);
                    if(entity.getDoiTuong() != null) res.setDoiTuongId(entity.getDoiTuong().getId());
                    return res;
                }).collect(Collectors.toList());
    }

}
