package com.example.managementproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "wh_ban_an_lenh_qd")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhBanAnLenhQd {
    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOI_TUONG_ID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private WhDoiTuong doiTuong;

    @Column(name = "CO_BAN_AN_HINH_SU")
    private Integer coBanAnHinhSu = 0;

    @Column(name = "DIEN_ID", nullable = false)
    private Long dienId;

    @Column(name = "NGAY_VAO_DIEN", nullable = false)
    private Integer ngayVaoDien;

    @Column(name = "NGAY_KET_THUC_DIEN", nullable = false)
    private Integer ngayKetThucDien;

    @Column(name = "TINH_TRANG_QUAN_LY")
    private Integer tinhTrangQuanLy = 1;

    @Column(name = "NGAY_SUA_CUOI")
    @Temporal(TemporalType.DATE)
    private Date ngaySuaCuoi;

    @Column(name = "THAO_TAC_CUOI", nullable = false)
    private Integer thaoTacCuoi = 1;

    @Column(name = "DIA_BAN_QUAN_LY_CODE", length = 20, nullable = false)
    private String diaBanQuanLyCode;

    @Column(name = "SYNC_VNPT", nullable = false)
    private Integer syncVnpt = 0;

    @Column(name = "TIME_SYNC_VNPT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeSyncVnpt = new Date();
}
