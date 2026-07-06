package com.example.managementproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class DashboardToiDanhResponse {
    private List<ChartDataDTO> chart1ToiDanh;
    private List<ChartDataDTO> chartADiaBanGioiTinh;
    private List<ChartDataDTO> chartBDanToc;
    private List<ChartDataDTO> chartCTonGiao;
    private List<ChartDataDTO> chartDDoTuoi;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChartDataDTO{
        private String label;
        private String subLabel;
        private Long value;
    }
}
