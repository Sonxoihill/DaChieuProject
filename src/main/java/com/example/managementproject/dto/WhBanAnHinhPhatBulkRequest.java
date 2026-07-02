package com.example.managementproject.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class WhBanAnHinhPhatBulkRequest {
    @NotEmpty(message = "Danh sách hình phạt không được để trống")
    @Size(max = 1000, message = "Mỗi lần đồng bộ tối đa không quá 1000 bản ghi")
    private List<@Valid WhBanAnHinhPhatRequest> data;
}
