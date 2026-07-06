package com.example.managementproject.service;

import com.example.managementproject.dto.DashboardToiDanhRequest;
import com.example.managementproject.dto.DashboardToiDanhResponse;
import com.example.managementproject.dto.RawToiDanhMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardToiDanhService {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final Map<Integer, String> TINH_CHAT_MAP = Map.ofEntries(
            Map.entry(1, "Xâm hại tình dục"),
            Map.entry(2, "Trộm cắp tài sản"),
            Map.entry(3, "Tội phạm về ma túy"),
            Map.entry(4, "Vi phạm qui định quản lý hành chính"),
            Map.entry(5, "Gây rối trật tự công cộng"),
            Map.entry(6, "Mua bán người"),
            Map.entry(7, "Cướp tài sản"),
            Map.entry(8, "Lừa đảo chiếm đoạt tài sản"),
            Map.entry(9, "Xâm phạm an ninh quốc gia"),
            Map.entry(10, "Cưỡng đoạt tài sản"),
            Map.entry(11, "Tội phạm về môi trường"),
            Map.entry(12, "Đánh bạc và tổ chức đánh bạc"),
            Map.entry(13, "Cố ý gây thương tích"),
            Map.entry(14, "Giết người"),
            Map.entry(15, "Mại dâm"),
            Map.entry(16, "Chống người thi hành công vụ"),
            Map.entry(17, "Tội phạm về vũ khí, vật liệu nổ"),
            Map.entry(18, "Cướp giật tài sản")
    );
    private static final Map<Long, String> DAN_TOC_MAP = Map.ofEntries(
            Map.entry(1L, "Kinh"),
            Map.entry(2L, "Mường"),
            Map.entry(3L, "Tày"),
            Map.entry(4L, "Khơ me"),
            Map.entry(5L, "Thái"),
            Map.entry(6L, "Nùng")
    );
    private static final Map<Long, String> TON_GIAO_MAP = Map.ofEntries(
            Map.entry(1L, "Phật giáo"),
            Map.entry(2L, "Công giáo"),
            Map.entry(3L, "Cao Đài"),
            Map.entry(4L, "Hòa Hảo"),
            Map.entry(5L, "Không tôn giáo")
    );

    public DashboardToiDanhResponse getToiDanhDashboard(DashboardToiDanhRequest request) {
        String baseSql = """
                SELECT
                        b.DIA_BAN_QUAN_LY_CODE                                                          AS MA_XA,
                        d.TINH_CHAT                                                                     AS ID_TINH_CHAT_TOI_DANH,
                        c.GIOI_TINH                                                                     AS ID_GIOI_TINH,
                        c.DAN_TOC_ID                                                                    AS ID_DAN_TOC,
                        c.TON_GIAO_ID                                                                   AS ID_TON_GIAO,
                        COUNT(DISTINCT c.CU_TRU_ID)                                                     AS TONG_SO_DT,

                        COUNT(DISTINCT CASE WHEN CAST(c.NGAY_SINH AS UNSIGNED) > CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 16 YEAR), '%Y%m%d') AS UNSIGNED) THEN c.CU_TRU_ID END) AS duoi16,
                        COUNT(DISTINCT CASE WHEN CAST(c.NGAY_SINH AS UNSIGNED) <= CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 16 YEAR), '%Y%m%d') AS UNSIGNED) AND CAST(c.NGAY_SINH AS UNSIGNED) > CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 18 YEAR), '%Y%m%d') AS UNSIGNED) THEN c.CU_TRU_ID END) AS tuoi1618,
                        COUNT(DISTINCT CASE WHEN CAST(c.NGAY_SINH AS UNSIGNED) <= CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 18 YEAR), '%Y%m%d') AS UNSIGNED) AND CAST(c.NGAY_SINH AS UNSIGNED) > CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 30 YEAR), '%Y%m%d') AS UNSIGNED) THEN c.CU_TRU_ID END) AS tuoi1830,
                        COUNT(DISTINCT CASE WHEN CAST(c.NGAY_SINH AS UNSIGNED) <= CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 30 YEAR), '%Y%m%d') AS UNSIGNED) AND CAST(c.NGAY_SINH AS UNSIGNED) > CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 45 YEAR), '%Y%m%d') AS UNSIGNED) THEN c.CU_TRU_ID END) AS tuoi3045,
                        COUNT(DISTINCT CASE WHEN CAST(c.NGAY_SINH AS UNSIGNED) <= CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 45 YEAR), '%Y%m%d') AS UNSIGNED) AND CAST(c.NGAY_SINH AS UNSIGNED) > CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 60 YEAR), '%Y%m%d') AS UNSIGNED) THEN c.CU_TRU_ID END) AS tuoi4560,
                        COUNT(DISTINCT CASE WHEN CAST(c.NGAY_SINH AS UNSIGNED) <= CAST(DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 60 YEAR), '%Y%m%d') AS UNSIGNED) THEN c.CU_TRU_ID END) AS tren60
                    FROM WH_BAN_AN_LENH_QD_TOI_PHAM a
                    JOIN WH_BAN_AN_LENH_QD b ON a.BAN_AN_LENH_QD_ID = b.ID
                    JOIN WH_DOI_TUONG c ON c.ID = b.DOI_TUONG_ID
                    JOIN DM_TOI_PHAM d ON d.ID = a.TOI_PHAM_ID
                    WHERE 1=1
                """;

        StringBuilder sqlBuilder = new StringBuilder(baseSql);
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (request.getMaXaCommune() != null && !"ALL".equals(request.getMaXaCommune()) && !request.getMaXaCommune().isEmpty()) {
            sqlBuilder.append(" AND b.DIA_BAN_QUAN_LY_CODE = :maXa ");
            params.addValue("maXa", request.getMaXaCommune());
        }
        if (request.getGioiTinh() != null) {
            sqlBuilder.append(" AND c.GIOI_TINH = :gioiTinh ");
            params.addValue("gioiTinh", request.getGioiTinh());
        }
        if (request.getDanTocId() != null) {
            sqlBuilder.append(" AND c.DAN_TOC_ID = :danTocId ");
            params.addValue("danTocId", request.getDanTocId());
        }
        if (request.getTonGiaoId() != null) {
            sqlBuilder.append(" AND c.TON_GIAO_ID = :tonGiaoId ");
            params.addValue("tonGiaoId", request.getTonGiaoId());
        }

        sqlBuilder.append(" GROUP BY b.DIA_BAN_QUAN_LY_CODE, d.TINH_CHAT, c.GIOI_TINH, c.DAN_TOC_ID, c.TON_GIAO_ID ");

        List<RawToiDanhMatrix> matrixList = namedParameterJdbcTemplate.query(
                sqlBuilder.toString(),
                params,
                new BeanPropertyRowMapper<>(RawToiDanhMatrix.class)
        );

        DashboardToiDanhResponse response = new DashboardToiDanhResponse();

        List<DashboardToiDanhResponse.ChartDataDTO> chart1 = matrixList.stream()
                .collect(Collectors.groupingBy(m -> m.getIdTinhChatToiDanh() == null ? 0 : m.getIdTinhChatToiDanh(), Collectors.summingLong(m -> m.getTongSoDt() == null ? 0L : m.getTongSoDt())))
                .entrySet().stream()
                .map(e -> new DashboardToiDanhResponse.ChartDataDTO(getTenTinhChatToiDanh(e.getKey()), String.valueOf(e.getKey()), e.getValue()))
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .collect(Collectors.toList());
        response.setChart1ToiDanh(chart1);


        List<RawToiDanhMatrix> filteredMatrix = matrixList;
        if (request.getTinhChatToiDanhSelected() != null) {
            filteredMatrix = matrixList.stream()
                    .filter(m -> request.getTinhChatToiDanhSelected().equals(m.getIdTinhChatToiDanh()))
                    .collect(Collectors.toList());
        }

        Map<String, Long> namCountMap = filteredMatrix.stream()
                .filter(m -> m.getIdGioiTinh() != null && m.getIdGioiTinh() == 1)
                .collect(Collectors.groupingBy(m -> m.getMaXa() == null ? "Unknown" : m.getMaXa(), Collectors.summingLong(m -> m.getTongSoDt() == null ? 0L : m.getTongSoDt())));

        List<DashboardToiDanhResponse.ChartDataDTO> chartA = filteredMatrix.stream()
                .collect(Collectors.groupingBy(
                        m -> (m.getMaXa() == null ? "Unknown" : m.getMaXa()) + "_" + (m.getIdGioiTinh() != null && m.getIdGioiTinh() == 1 ? "NAM" : "NU"),
                        Collectors.summingLong(m -> m.getTongSoDt() == null ? 0L : m.getTongSoDt())
                ))
                .entrySet().stream()
                .map(e -> {
                    String[] parts = e.getKey().split("_");
                    DashboardToiDanhResponse.ChartDataDTO dto = new DashboardToiDanhResponse.ChartDataDTO();
                    dto.setLabel(parts[0]);
                    dto.setSubLabel(parts[1]);
                    dto.setValue(e.getValue());
                    return dto;
                })
                .sorted((o1, o2) -> {
                    Long nam1 = namCountMap.getOrDefault(o1.getLabel(), 0L);
                    Long nam2 = namCountMap.getOrDefault(o2.getLabel(), 0L);
                    return nam2.compareTo(nam1);
                })
                .collect(Collectors.toList());
        response.setChartADiaBanGioiTinh(chartA);

        if (request.getDanTocId() == null) {
            List<DashboardToiDanhResponse.ChartDataDTO> chartB = filteredMatrix.stream()
                    .collect(Collectors.groupingBy(m -> m.getIdDanToc() == null ? 0L : m.getIdDanToc(), Collectors.summingLong(m -> m.getTongSoDt() == null ? 0L : m.getTongSoDt())))
                    .entrySet().stream()
                    .map(e -> new DashboardToiDanhResponse.ChartDataDTO(getTenDanToc(e.getKey()), null, e.getValue()))
                    .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                    .collect(Collectors.toList());
            response.setChartBDanToc(chartB);
        }

        if (request.getTonGiaoId() == null) {
            List<DashboardToiDanhResponse.ChartDataDTO> chartC = filteredMatrix.stream()
                    .collect(Collectors.groupingBy(m -> m.getIdTonGiao() == null ? 0L : m.getIdTonGiao(), Collectors.summingLong(m -> m.getTongSoDt() == null ? 0L : m.getTongSoDt())))
                    .entrySet().stream()
                    .map(e -> new DashboardToiDanhResponse.ChartDataDTO(getTenTonGiao(e.getKey()), null, e.getValue()))
                    .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                    .collect(Collectors.toList());
            response.setChartCTonGiao(chartC);
        }

        if (request.getDoTuoi() == null || request.getDoTuoi().isEmpty()) {
            long duoi16   = filteredMatrix.stream().mapToLong(m -> m.getDuoi16() == null ? 0L : m.getDuoi16()).sum();
            long tuoi1618 = filteredMatrix.stream().mapToLong(m -> m.getTuoi1618() == null ? 0L : m.getTuoi1618()).sum();
            long tuoi1830 = filteredMatrix.stream().mapToLong(m -> m.getTuoi1830() == null ? 0L : m.getTuoi1830()).sum();
            long tuoi3045 = filteredMatrix.stream().mapToLong(m -> m.getTuoi3045() == null ? 0L : m.getTuoi3045()).sum();
            long tuoi4560 = filteredMatrix.stream().mapToLong(m -> m.getTuoi4560() == null ? 0L : m.getTuoi4560()).sum();
            long tren60   = filteredMatrix.stream().mapToLong(m -> m.getTren60() == null ? 0L : m.getTren60()).sum();

            List<DashboardToiDanhResponse.ChartDataDTO> chartD = new ArrayList<>();
            chartD.add(new DashboardToiDanhResponse.ChartDataDTO("Dưới 16", null, duoi16));
            chartD.add(new DashboardToiDanhResponse.ChartDataDTO("Từ 16 đến 18", null, tuoi1618));
            chartD.add(new DashboardToiDanhResponse.ChartDataDTO("Từ 18 đến 30", null, tuoi1830));
            chartD.add(new DashboardToiDanhResponse.ChartDataDTO("Từ 30 đến 45", null, tuoi3045));
            chartD.add(new DashboardToiDanhResponse.ChartDataDTO("Từ 45 đến 60", null, tuoi4560));
            chartD.add(new DashboardToiDanhResponse.ChartDataDTO("Trên 60", null, tren60));

            response.setChartDDoTuoi(chartD);
        }


        return response;
    }

    private String getTenTinhChatToiDanh(Integer id) {
        return TINH_CHAT_MAP.getOrDefault(id, "Tội danh khác");
    }

    private String getTenDanToc(Long id) {
        return (id == null || id == 0L) ? "Dân tộc khác" : DAN_TOC_MAP.getOrDefault(id, "Dân tộc khác");
    }

    private String getTenTonGiao(Long id) {
        return (id == null || id == 0L) ? "Không tôn giáo" : TON_GIAO_MAP.getOrDefault(id, "Tôn giáo khác");
    }

    private String getTenGioiTinh(Integer id) {
        if (id == null) return "Không xác định";
        if (id == 1) return "Nam";
        if (id == 2) return "Nữ";
        return "Khác";
    }
}