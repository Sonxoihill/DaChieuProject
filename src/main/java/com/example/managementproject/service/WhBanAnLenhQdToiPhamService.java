package com.example.managementproject.service;

import com.example.managementproject.dto.*;
import com.example.managementproject.entity.WhBanAnLenhQd;
import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import com.example.managementproject.repository.WhBanAnLenhQdRepository;
import com.example.managementproject.repository.WhBanAnLenhQdToiPhamRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void addOrUpdate(WhBanAnLenhQdToiPhamBulkRequest bulkRequest) {
        if(bulkRequest.getData() == null || bulkRequest.getData().isEmpty()) return;

        List<Long> requestIds = bulkRequest.getData().stream()
                .map(WhBanAnLenhQdToiPhamRequest::getId)
                .collect(Collectors.toList());

        List<Long> existingIds = whBanAnLenhQdToiPhamRepository.findExistingIds(requestIds);
        List<WhBanAnLenhQdToiPhamRequest> inserts = bulkRequest.getData().stream()
                .filter(dto -> !existingIds.contains(dto.getId()))
                .collect(Collectors.toList());

        List<WhBanAnLenhQdToiPhamRequest> updates = bulkRequest.getData().stream()
                .filter(dto -> existingIds.contains(dto.getId()))
                .collect(Collectors.toList());

        if(!inserts.isEmpty()) {
            String insertSql = "INSERT INTO wh_ban_an_lenh_qd_toi_pham (id, ban_an_lenh_qd_id, toi_pham_id, dia_ban_quan_ly_code, thao_tac_cuoi, sync_vnpt) VALUES (?, ?, ?, ?, ?, ?)";

            jdbcTemplate.batchUpdate(insertSql, inserts, BATCH_SIZE, (ps, dto) -> {
               ps.setObject(1, dto.getId());
               ps.setObject(2,  dto.getBanAnLenhQdId());
               ps.setObject(3, dto.getToiPhamId());
               ps.setObject(4, dto.getDiaBanQuanLyCode());
               ps.setObject(5, THAO_TAC_THEM);
               ps.setObject(6, SYNC_STATUS_PENDING);
            });
        }

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        if(!updates.isEmpty()) {
            String updateSql = "UPDATE wh_ban_an_lenh_qd_toi_pham SET ban_an_lenh_qd_id = ?, toi_pham_id = ?, dia_ban_quan_ly_code = ?, thao_tac_cuoi = ?, ngay_sua_cuoi = ? WHERE id = ?";

            jdbcTemplate.batchUpdate(updateSql, updates, BATCH_SIZE, (ps, dto) -> {
                ps.setObject(1, dto.getBanAnLenhQdId());
                ps.setObject(2, dto.getToiPhamId());
                ps.setObject(3, dto.getDiaBanQuanLyCode());
                ps.setObject(4, THAO_TAC_SUA);
                ps.setTimestamp(5, currentTimestamp);
                ps.setObject(6, dto.getId());
            });
        }
    }
}
