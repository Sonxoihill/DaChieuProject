package com.example.managementproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WhBanAnHinhPhatRequest {
    @NotNull(message = "ID hình phạt không được để trống")
    private Long hanhViXuPhatId;

    @NotBlank(message = "Mã địa bàn quản lý không được để trống")
    private String diaBanQuanLyCode;

    @NotNull(message = "ID bản án không được để trống")
    private Long banAnLenhQdId;
}
