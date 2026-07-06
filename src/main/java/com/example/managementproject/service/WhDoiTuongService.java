package com.example.managementproject.service;

import com.example.managementproject.dto.WhDoiTuongBulkRequest;
import com.example.managementproject.dto.WhDoiTuongRequest;
import com.example.managementproject.dto.WhDoiTuongResponse;
import com.example.managementproject.entity.WhDoiTuong;
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
public class WhDoiTuongService {
    @Autowired
    private WhDoiTuongRepository whDoiTuongRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void addOrUpdate(WhDoiTuongBulkRequest bulkRequest) {
        if(bulkRequest.getData() == null || bulkRequest.getData().isEmpty()) return;

        List<Long> requestIds = bulkRequest.getData().stream()
                .map(WhDoiTuongRequest::getId)
                .collect(Collectors.toList());

        List<Long> existingIds = whDoiTuongRepository.findByExistingIds(requestIds);
        List<WhDoiTuongRequest> inserts = bulkRequest.getData().stream()
                .filter(dto -> !existingIds.contains(dto.getId()))
                .collect(Collectors.toList());

        List<WhDoiTuongRequest> updates = bulkRequest.getData().stream()
                .filter(dto -> existingIds.contains(dto.getId()))
                .collect(Collectors.toList());

        if(!inserts.isEmpty()) {
            String insertSql = "INSERT INTO wh_doi_tuong (id, ma_dinh_danh, cu_tru_id, ngay_sinh, gioi_tinh, dan_toc_id, ton_giao_id, nhom_mau_id, ngay_bat_dau_ql, dia_ban_quan_ly_code, so_ban_an_lenh_qd, so_ban_an_lenh_qd_hieu_luc, thao_tac_cuoi, sync_vnpt) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            jdbcTemplate.batchUpdate(insertSql, inserts, BATCH_SIZE, (ps, dto) ->{
               ps.setObject(1,dto.getId());
               ps.setObject(2,dto.getMaDinhDanh());
               ps.setObject(3, dto.getCuTruId());
               ps.setObject(4, dto.getNgaySinh());
               ps.setObject(5, dto.getGioiTinh());
               ps.setObject(6, dto.getDanTocId());
               ps.setObject(7, dto.getTonGiaoId());
               ps.setObject(8, dto.getNhomMauId());
               ps.setObject(9, dto.getNgayBatDauQl());
               ps.setObject(10, dto.getDiaBanQuanLyCode());
               ps.setInt(11, INITIAL_COUNT);
               ps.setInt(12, INITIAL_COUNT);
               ps.setInt(13, THAO_TAC_THEM);
               ps.setInt(14, SYNC_STATUS_PENDING);
            });
        }

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        if(!updates.isEmpty()) {
            String updateSql = "UPDATE wh_doi_tuong SET ma_dinh_danh = ?, cu_tru_id = ?, ngay_sinh = ?, gioi_tinh = ?, dan_toc_id = ?, ton_giao_id = ?, nhom_mau_id = ?, ngay_bat_dau_ql = ?, dia_ban_quan_ly_code = ?, thao_tac_cuoi = ?, ngay_sua_cuoi = ? WHERE id = ?";

            jdbcTemplate.batchUpdate(updateSql, updates, BATCH_SIZE, (ps, dto) -> {
                ps.setObject(1,dto.getMaDinhDanh());
                ps.setObject(2, dto.getCuTruId());
                ps.setObject(3, dto.getNgaySinh());
                ps.setObject(4, dto.getGioiTinh());
                ps.setObject(5, dto.getDanTocId());
                ps.setObject(6, dto.getTonGiaoId());
                ps.setObject(7, dto.getNhomMauId());
                ps.setObject(8, dto.getNgayBatDauQl());
                ps.setObject(9, dto.getDiaBanQuanLyCode());
                ps.setInt(10, THAO_TAC_SUA);
                ps.setTimestamp(11, currentTimestamp);
                ps.setObject(12, dto.getId());
            });
        }
    }

}
