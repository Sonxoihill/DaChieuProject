package com.example.managementproject.repository;

import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhBanAnLenhQdToiPhamRepository extends JpaRepository<WhBanAnLenhQdToiPham, Long> {
}
