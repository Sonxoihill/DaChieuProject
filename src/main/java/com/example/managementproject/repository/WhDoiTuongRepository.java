package com.example.managementproject.repository;

import com.example.managementproject.entity.WhDoiTuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WhDoiTuongRepository extends JpaRepository<WhDoiTuong, Long> {
    Optional<WhDoiTuong> findByMaDinhDanh(String maDinhDanh);
}
