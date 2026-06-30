package com.example.managementproject.repository;

import com.example.managementproject.entity.WhBanAnLenhQd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhBanAnLenhQdRepository extends JpaRepository<WhBanAnLenhQd, Long> {
}
