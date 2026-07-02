package com.example.managementproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WhBanAnLenhQdToiPhamRequest {

    @NotNull(message = "ID tội phạm không được để trống")
    private Long toiPhamId;

    @NotBlank(message = "Mã địa bàn quản lý không được để trống")
    private String diaBanQuanLyCode;

    @NotNull(message = "ID bản án không được để trống")
    private Long banAnLenhQdId;
}
