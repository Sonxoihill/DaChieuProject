package com.example.managementproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "wh_doi_tuong")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhDoiTuong {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "MA_DINH_DANH", length = 12, nullable = false)
    private String maDinhDanh;

    @Column(name = "CU_TRU_ID", length = 100)
    private String cuTruId;

    @Column(name = "NGAY_SINH", nullable = false)
    private Integer ngaySinh;

    @Column(name = "GIOI_TINH")
    private Integer gioiTinh;

    @Column(name = "DAN_TOC_ID")
    private Long danTocId;

    @Column(name = "TON_GIAO_ID")
    private Long tonGiaoId;

    @Column(name = "NHOM_MAU_ID")
    private Long nhomMauId;

    @Column(name = "NGAY_BAT_DAU_QL", nullable = false)
    private Integer ngayBatDauQl;

    @Column(name = "NGAY_KET_THUC_QL")
    private Integer ngayKetThucQl;

    @Column(name = "SO_BAN_AN_LENH_QD")
    private Integer soBanAnLenhQd;

    @Column(name = "SO_BAN_AN_LENH_QD_HIEU_LUC")
    private Integer soBanAnLenhQdHieuLuc = 0;

    @Column(name = "DS_ID_DC", length = 2000)
    private String dsIdDc;

    @Column(name = "SYNC_XOA_AN_TICH")
    private Integer syncXoaAnTich;

    @Column(name = "SO_AN_TICH_TIEN_AN")
    private Integer soAnTichTienAn;

    @Column(name = "XOA_AN_TICH")
    private Integer xoaAnTich;

    @Column(name = "SO_AN_TICH_TIEN_SU")
    private Integer soAnTichTienSu;

    @Column(name = "NGAY_SUA_CUOI")
    @Temporal(TemporalType.DATE)
    private Date ngaySuaCuoi;

    @Column(name = "THAO_TAC_CUOI", nullable = false)
    private Integer thaoTacCuoi = 1;

    @Column(name = "DIA_BAN_QUAN_LY_CODE", length = 20, nullable = false)
    private String diaBanQuanLyCode;

    @Column(name = "SYNC_VNPT", nullable = false)
    private Integer syncVnpt = 0;

    @Column(name = "TIME_SYNC_VNPT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSyncVnpt = new Date();
}
