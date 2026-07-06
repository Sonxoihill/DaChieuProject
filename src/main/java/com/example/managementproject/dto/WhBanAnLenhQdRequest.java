package com.example.managementproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WhBanAnLenhQdRequest {
    @NotNull(message = "ID bản án không được để trống")
    private Long id;

    @NotNull(message = "ID đối tượng không được để trống")
    private Long doiTuongId;

    @NotNull(message = "Trường có bản án hình sự không được để trống")
    private Integer coBanAnHinhSu;

    @NotNull(message = "Mã diện quản lý không được để trống")
    private Long dienId;

    @NotNull(message = "Ngày vào diện không được để trống")
    private Integer ngayVaoDien;

    private Integer ngayKetThucDien;

    @NotNull(message = "Tình trạng quản lý không được để trống")
    private Integer tinhTrangQuanLy;

    @NotBlank(message = "Mã địa bàn quản lý không được để trống")
    private String diaBanQuanLyCode;
}
