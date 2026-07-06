package com.example.managementproject.repository;

import com.example.managementproject.entity.WhBanAnLenhQdToiPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WhBanAnLenhQdToiPhamRepository extends JpaRepository<WhBanAnLenhQdToiPham, Long> {
    Optional<WhBanAnLenhQdToiPham> findByBanAnLenhQdIdAndToiPhamId(Long banAnLenhQdToiPhamId, Long toiPhamId);

    @Query("SELECT t.id FROM WhBanAnLenhQdToiPham t WHERE t.id IN :ids")
    List<Long> findExistingIds(@Param("ids") List<Long> ids);
}
