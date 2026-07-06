package com.example.managementproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "wh_ban_an_hinh_phat")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhBanAnHinhPhat {
    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BAN_AN_LENH_QD_ID", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private WhBanAnLenhQd banAnLenhQd;

    @Column(name = "HANH_VI_XU_PHAT_ID", nullable = false)
    private Long hanhViXuPhatId;

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
