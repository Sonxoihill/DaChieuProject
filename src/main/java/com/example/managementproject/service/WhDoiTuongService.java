package com.example.managementproject.service;

import com.example.managementproject.dto.WhDoiTuongBulkRequest;
import com.example.managementproject.dto.WhDoiTuongRequest;
import com.example.managementproject.dto.WhDoiTuongResponse;
import com.example.managementproject.entity.WhDoiTuong;
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
public class WhDoiTuongService {
    @Autowired
    private WhDoiTuongRepository whDoiTuongRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public List<WhDoiTuongResponse> addOrUpdate(WhDoiTuongBulkRequest bulkRequest) {
        List<WhDoiTuong> entitiestoSave = new ArrayList<>();
        for(WhDoiTuongRequest dto : bulkRequest.getData()) {
            Optional<WhDoiTuong> existingEntity = whDoiTuongRepository.findByMaDinhDanh(dto.getMaDinhDanh());

            WhDoiTuong doiTuong;
            Date current = new Date();
            if(existingEntity.isPresent()) {
                doiTuong = existingEntity.get();
                modelMapper.map(dto, doiTuong);
                doiTuong.setNgaySuaCuoi(current);
                doiTuong.setThaoTacCuoi(THAO_TAC_SUA);
            }else {
                doiTuong = modelMapper.map(dto, WhDoiTuong.class);
                doiTuong.setSoBanAnLenhQd(INITIAL_COUNT);
                doiTuong.setThaoTacCuoi(THAO_TAC_THEM);
            }

            doiTuong.setSyncVnpt(SYNC_STATUS_PENDING);
            doiTuong.setTimeSyncVnpt(current);
            entitiestoSave.add(doiTuong);
        }

        List<WhDoiTuong> savedEntities = whDoiTuongRepository.saveAll(entitiestoSave);
        return savedEntities.stream()
                .map(entity -> modelMapper.map(entity, WhDoiTuongResponse.class))
                .collect(Collectors.toList());
    }

}
