package com.example.managementproject.service;

import com.example.managementproject.dto.WhBanAnHinhPhatBulkRequest;
import com.example.managementproject.dto.WhBanAnHinhPhatRequest;
import com.example.managementproject.dto.WhBanAnHinhPhatResponse;
import com.example.managementproject.entity.WhBanAnHinhPhat;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.repository.WhBanAnHinhPhatRepository;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
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
public class WhBanAnHinhPhatService {
    @Autowired
    WhBanAnLenhQdRepository whBanAnLenhQdRepository;

    @Autowired
    WhBanAnHinhPhatRepository whBanAnHinhPhatRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    public List<WhBanAnHinhPhatResponse> addOrUpdate(WhBanAnHinhPhatBulkRequest bulkRequest) {
       List<WhBanAnHinhPhat> entitiesToSave = new ArrayList<>();
       for(WhBanAnHinhPhatRequest dto : bulkRequest.getData()){
           Optional<WhBanAnHinhPhat> existingEntity = whBanAnHinhPhatRepository.findByBanAnLenhQdIdAndHanhViXuPhatId(dto.getBanAnLenhQdId(),  dto.getHanhViXuPhatId());

           WhBanAnHinhPhat hp;
           Date now = new Date();
           if(existingEntity.isPresent()){
               hp = existingEntity.get();
               modelMapper.map(dto,hp);
               hp.setThaoTacCuoi(THAO_TAC_SUA);
               hp.setNgaySuaCuoi(now);
           } else {
               WhBanAnLenhQd banAn = whBanAnLenhQdRepository.findById(dto.getBanAnLenhQdId())
                       .orElseThrow(() -> new RuntimeException("Khong tim thay ban an"));
               hp = modelMapper.map(dto, WhBanAnHinhPhat.class);
               hp.setBanAnLenhQd(banAn);
               hp.setThaoTacCuoi(THAO_TAC_THEM);
           }

           hp.setSyncVnpt(SYNC_STATUS_PENDING);
           hp.setTimeSyncVnpt(now);
           entitiesToSave.add(hp);
       }

       List<WhBanAnHinhPhat> savedEntities = whBanAnHinhPhatRepository.saveAll(entitiesToSave);
       return savedEntities.stream()
               .map(entity -> {
                   WhBanAnHinhPhatResponse res = modelMapper.map(entity, WhBanAnHinhPhatResponse.class);
                   if(entity.getBanAnLenhQd() != null) res.setBanAnLenhQdId(entity.getBanAnLenhQd().getId());
                   return res;
               }).collect(Collectors.toList());
    }


}
