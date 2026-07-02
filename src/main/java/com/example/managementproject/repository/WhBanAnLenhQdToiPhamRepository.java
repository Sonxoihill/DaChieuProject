package com.example.managementproject.repository;

import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WhBanAnLenhQdToiPhamRepository extends JpaRepository<WhBanAnLenhQdToiPham, Long> {
    Optional<WhBanAnLenhQdToiPham> findByBanAnLenhQdIdAndToiPhamId(Long banAnLenhQdToiPhamId, Long toiPhamId);
}
