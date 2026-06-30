package com.example.managementproject.dto;

import lombok.Data;

@Data
public class WhDoiTuongRequest {
    private String maDinhDanh;
    private String cuTruId;
    private Integer ngaySinh;
    private Integer gioiTinh;
    private Long danTocId;
    private Long tonGiaoId;
    private Long nhomMauId;
    private Integer ngayBatDauQl;
    private Integer ngayKetThucQl;
    private String diaBanQuanLyCode;
}
