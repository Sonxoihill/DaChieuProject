package com.example.managementproject.dto;

import lombok.Data;

@Data
public class WhBanAnLenhQdResponse {
    private Long id;
    private Long doiTuongId;
    private Integer coBanAnHinhSu;
    private Long dienId;
    private Integer ngayVaoDien;
    private Integer ngayKetThucDien;
    private Integer tinhTrangQuanLy;
    private String diaBanQuanLyCode;
}
