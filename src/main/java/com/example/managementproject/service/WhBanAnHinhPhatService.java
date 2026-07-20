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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
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

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public void addOrUpdate(WhBanAnHinhPhatBulkRequest bulkRequest) {
       if(bulkRequest.getData() == null || bulkRequest.getData().isEmpty()) return;

       List<Long> requestIds = bulkRequest.getData().stream()
               .map(WhBanAnHinhPhatRequest::getId)
               .collect(Collectors.toList());

       Set<Long> existingIds = whBanAnHinhPhatRepository.findExistingIds(requestIds);
       List<WhBanAnHinhPhatRequest> inserts = bulkRequest.getData().stream()
               .filter(dto -> !existingIds.contains(dto.getId()))
               .collect(Collectors.toList());

       List<WhBanAnHinhPhatRequest> updates = bulkRequest.getData().stream()
               .filter(dto -> existingIds.contains(dto.getId()))
               .collect(Collectors.toList());

       if(!inserts.isEmpty()) {
           String insertSql = "INSERT INTO wh_ban_an_hinh_phat (id, ban_an_lenh_qd_id, hanh_vi_xu_phat_id, dia_ban_quan_ly_code, thao_tac_cuoi, sync_vnpt) VALUES (?, ?, ?, ?, ?, ?)";

           jdbcTemplate.batchUpdate(insertSql, inserts, BATCH_SIZE, (ps, dto) -> {
              ps.setObject(1, dto.getId());
              ps.setObject(2, dto.getBanAnLenhQdId());
              ps.setObject(3, dto.getHanhViXuPhatId());
              ps.setObject(4, dto.getDiaBanQuanLyCode());
              ps.setInt(5, THAO_TAC_THEM);
              ps.setInt(6, SYNC_STATUS_PENDING);
           });
       }

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

       if(!updates.isEmpty()) {
           String updateSql = "UPDATE wh_ban_an_hinh_phat SET ban_an_lenh_qd_id = ?, hanh_vi_xu_phat_id = ?, dia_ban_quan_ly_code = ?, thao_tac_cuoi = ?, ngay_sua_cuoi = ? WHERE id = ?";

           jdbcTemplate.batchUpdate(updateSql, updates, BATCH_SIZE, (ps, dto) -> {
               ps.setObject(1, dto.getBanAnLenhQdId());
               ps.setObject(2, dto.getHanhViXuPhatId());
               ps.setObject(3, dto.getDiaBanQuanLyCode());
               ps.setObject(4, THAO_TAC_SUA);
               ps.setTimestamp(5, currentTimestamp);
               ps.setObject(6, dto.getId());
           });
       }
    }
}
