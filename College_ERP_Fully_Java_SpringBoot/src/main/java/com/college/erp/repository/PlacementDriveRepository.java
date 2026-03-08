package com.college.erp.repository;

import com.college.erp.model.PlacementDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlacementDriveRepository extends JpaRepository<PlacementDrive, Long> {
    List<PlacementDrive> findByStatus(String status);
    List<PlacementDrive> findByJobType(String jobType);
}