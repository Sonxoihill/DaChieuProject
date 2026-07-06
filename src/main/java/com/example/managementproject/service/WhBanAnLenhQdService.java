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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public void addOrUpdate(WhBanAnLenhQdBulkRequest bulkRequest){
        if(bulkRequest.getData() == null || bulkRequest.getData().isEmpty()) return;

        List<Long> requestIds = bulkRequest.getData().stream()
                .map(WhBanAnLenhQdRequest::getId)
                .collect(Collectors.toList());
        List<Long> existingIds = whBanAnLenhQdRepository.findExistingIds(requestIds);

        List<WhBanAnLenhQdRequest> inserts = bulkRequest.getData().stream()
                .filter(dto -> !existingIds.contains(dto.getId()))
                .collect(Collectors.toList());

        List<WhBanAnLenhQdRequest> updates = bulkRequest.getData().stream()
                .filter(dto -> existingIds.contains(dto.getId()))
                .collect(Collectors.toList());

        if(!inserts.isEmpty()){
            String insertSql = "INSERT INTO wh_ban_an_lenh_qd (id, doi_tuong_id, co_ban_an_hinh_su, dien_id, ngay_vao_dien, ngay_ket_thuc_dien, tinh_trang_quan_ly, dia_ban_quan_ly_code, thao_tac_cuoi, sync_vnpt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.batchUpdate(insertSql, inserts, BATCH_SIZE, (ps, dto) -> {
                ps.setObject(1, dto.getId());
                ps.setObject(2, dto.getDoiTuongId());
                ps.setObject(3, dto.getCoBanAnHinhSu());
                ps.setObject(4, dto.getDienId());
                ps.setObject(5, dto.getNgayVaoDien());
                ps.setObject(6, dto.getNgayKetThucDien());
                ps.setObject(7, dto.getTinhTrangQuanLy());
                ps.setString(8, dto.getDiaBanQuanLyCode());
                ps.setInt(9, THAO_TAC_THEM);
                ps.setInt(10, SYNC_STATUS_PENDING);
            });
        }

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        if(!updates.isEmpty()){
            String updateSql = "UPDATE wh_ban_an_lenh_qd SET doi_tuong_id = ?, co_ban_an_hinh_su = ?, dien_id = ?, ngay_vao_dien = ?, ngay_ket_thuc_dien = ?, tinh_trang_quan_ly = ?, dia_ban_quan_ly_code = ?, thao_tac_cuoi = ?, ngay_sua_cuoi = ? WHERE id = ?";

            jdbcTemplate.batchUpdate(updateSql, updates, BATCH_SIZE, (ps, dto) -> {
                ps.setObject(1, dto.getDoiTuongId());
                ps.setObject(2, dto.getCoBanAnHinhSu());
                ps.setObject(3, dto.getDienId());
                ps.setObject(4, dto.getNgayVaoDien());
                ps.setObject(5, dto.getNgayKetThucDien());
                ps.setObject(6, dto.getTinhTrangQuanLy());
                ps.setString(7, dto.getDiaBanQuanLyCode());
                ps.setInt(8, THAO_TAC_SUA);
                ps.setTimestamp(9, currentTimestamp);
                ps.setObject(10, dto.getId());
            });
        }
    }

}
