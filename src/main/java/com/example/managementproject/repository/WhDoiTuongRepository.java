package com.example.managementproject.repository;

import com.example.managementproject.entity.WhDoiTuong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WhDoiTuongRepository extends JpaRepository<WhDoiTuong, Long> {
    Optional<WhDoiTuong> findByMaDinhDanh(String maDinhDanh);

    @Query("select d.id from WhDoiTuong d where d.id in :ids")
    List<Long> findByExistingIds(@Param("ids") List<Long> ids);
}
