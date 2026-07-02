package com.example.managementproject.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class WhBanAnLenhQdBulkRequest {
    @NotEmpty(message = "Danh sách bản án không được để trống")
    @Size(max = 1000, message = "Mỗi lần đồng bộ tối đa không quá 1000 bản ghi")
    private List<@Valid WhBanAnLenhQdRequest> data;
}
