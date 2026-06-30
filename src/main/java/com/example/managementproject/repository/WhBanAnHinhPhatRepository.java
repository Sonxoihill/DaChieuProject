package com.example.managementproject.repository;

import com.example.managementproject.entity.WhBanAnHinhPhat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhBanAnHinhPhatRepository extends JpaRepository<WhBanAnHinhPhat, Long> {
}
