package com.example.managementproject.dto;

import lombok.Data;

@Data
public class DashboardToiDanhRequest {
    private String maTinhCity;
    private String maHuyenDistrict;
    private String maXaCommune;
    private Integer gioiTinh;
    private Long danTocId;
    private Long tonGiaoId;
    private String doTuoi;

    private Integer tinhChatToiDanhSelected;
}
