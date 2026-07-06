package com.example.managementproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class ManagementProjectApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        try {
            System.out.println("=== JOIN DIAGNOSTIC ===");
            
            Integer step1 = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM wh_ban_an_lenh_qd_toi_pham a", Integer.class
            );
            System.out.println("1. wh_ban_an_lenh_qd_toi_pham only: " + step1);

            Integer step2 = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM wh_ban_an_lenh_qd_toi_pham a JOIN wh_ban_an_lenh_qd b ON a.BAN_AN_LENH_QD_ID = b.ID", Integer.class
            );
            System.out.println("2. Join wh_ban_an_lenh_qd: " + step2);

            Integer step3 = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM wh_ban_an_lenh_qd_toi_pham a JOIN wh_ban_an_lenh_qd b ON a.BAN_AN_LENH_QD_ID = b.ID JOIN wh_doi_tuong c ON c.ID = b.DOI_TUONG_ID", Integer.class
            );
            System.out.println("3. Join wh_doi_tuong: " + step3);

            Integer step4 = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM wh_ban_an_lenh_qd_toi_pham a JOIN wh_ban_an_lenh_qd b ON a.BAN_AN_LENH_QD_ID = b.ID JOIN wh_doi_tuong c ON c.ID = b.DOI_TUONG_ID JOIN dm_toi_pham d ON d.ID = a.TOI_PHAM_ID", Integer.class
            );
            System.out.println("4. Join dm_toi_pham: " + step4);
            
            System.out.println("=======================");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
