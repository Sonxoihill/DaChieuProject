package com.example.managementproject.dto;

import lombok.Data;

@Data
public class WhBanAnLenhQdRequest {
    private Integer coBanAnHinhSu;
    private Long dienId;
    private Integer ngayVaoDien;
    private Integer ngayKetThucDien;
    private Integer tinhTrangQuanLy;
    private String diaBanQuanLyCode;
    private Long doiTuongId;
}
