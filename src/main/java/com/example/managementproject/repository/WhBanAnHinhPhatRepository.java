package com.example.managementproject.repository;

import com.example.managementproject.entity.WhBanAnHinhPhat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WhBanAnHinhPhatRepository extends JpaRepository<WhBanAnHinhPhat, Long> {
    Optional<WhBanAnHinhPhat> findByBanAnLenhQdIdAndHanhViXuPhatId(Long banAnLenhQdId, Long hanhViXuPhatId);

    @Query("SELECT h.id FROM WhBanAnHinhPhat h WHERE h.id IN :ids ")
    List<Long> findExistingIds(@Param("ids") List<Long> ids);
}
