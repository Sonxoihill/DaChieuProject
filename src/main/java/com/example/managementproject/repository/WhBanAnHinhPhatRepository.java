package com.example.managementproject.repository;

import com.example.managementproject.entity.WhBanAnHinhPhat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WhBanAnHinhPhatRepository extends JpaRepository<WhBanAnHinhPhat, Long> {
    Optional<WhBanAnHinhPhat> findByBanAnLenhQdIdAndHanhViXuPhatId(Long banAnLenhQdId, Long hanhViXuPhatId);
}
