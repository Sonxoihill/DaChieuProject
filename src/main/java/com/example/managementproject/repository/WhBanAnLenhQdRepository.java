package com.example.managementproject.repository;

import com.example.managementproject.entity.WhBanAnLenhQd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WhBanAnLenhQdRepository extends JpaRepository<WhBanAnLenhQd, Long> {
    Optional<WhBanAnLenhQd> findByDoiTuongIdAndDienId(Long doiTuongId, Long dienId);

    @Query("SELECT b.id FROM WhBanAnLenhQd b WHERE b.id IN :ids")
    List<Long> findExistingIds(@Param("ids") List<Long> ids);
}
