package com.example.managementproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dm_toi_pham")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DmToiPham {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "TINH_CHAT")
    private Integer tinhChat;
}
