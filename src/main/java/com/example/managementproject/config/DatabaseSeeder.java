package com.example.managementproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM dm_toi_pham", Integer.class);
            if (count == null || count == 0) {
                System.out.println("Dang nap du lieu cho bang dm_toipham...");

                List<Long> ids = jdbcTemplate.queryForList(
                        "SELECT DISTINCT toi_pham_id FROM wh_ban_an_lenh_qd_toi_pham", 
                        Long.class
                );
                
                if (!ids.isEmpty()) {
                    String insertSql = "INSERT INTO dm_toi_pham (ID, TINH_CHAT) VALUES (?, ?)";
                    jdbcTemplate.batchUpdate(insertSql, ids, 100, (ps, id) -> {
                        ps.setLong(1, id);
                        int tinhChat = (id % 2 == 0) ? 1 : 2;
                        ps.setInt(2, tinhChat);
                    });
                    System.out.println("Da tao " + ids.size() + " du lieu mau cho dm_toi_pham");
                } else {
                    System.out.println("Khong toi pham nao duoc tim thay trong wh_ban_an_lenh_qd_toi_pham");
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to seed dm_toi_pham table: " + e.getMessage());
        }
    }
}
