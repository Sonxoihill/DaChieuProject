package com.example.managementproject.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class WhDoiTuongRequest {
    @NotBlank(message = "Mã định danh (CCCD) không được để trống")
    @Size(min = 12, max = 12, message = "Mã định danh phải chính xác 12 số")
    @Pattern(regexp = "^[0-9]+$", message = "Mã định danh chỉ được chứa các ký tự số")
    private String maDinhDanh;

    @NotBlank(message = "Mã cư trú không được để trống")
    private String cuTruId;

    @NotNull(message = "Ngày sinh không được để trống")
    @Min(value = 19000101, message = "Ngày sinh không hợp lệ")
    @Max(value = 20991231, message = "Ngày sinh không được vượt quá năm 2099")
    private Integer ngaySinh;

    @NotNull(message = "Giới tính không được để trống")
    @Min(value = 1, message = "Giới tính không hợp lệ (1: Nam, 2: Nữ)")
    @Max(value = 2, message = "Giới tính không hợp lệ (1: Nam, 2: Nữ)")
    private Integer gioiTinh;

    @NotNull(message = "Mã dân tộc không được để trống")
    private Long danTocId;

    @NotNull(message = "Mã tôn giáo không được để trống")
    private Long tonGiaoId;

    @NotNull(message = "Mã nhóm máu không được để trống")
    private Long nhomMauId;

    @NotNull(message = "Ngày bắt đầu quản lý không được để trống")
    private Integer ngayBatDauQl;

    private Integer ngayKetThucQl;

    @NotBlank(message = "Mã địa bàn quản lý không được để trống")
    private String diaBanQuanLyCode;
}
    
