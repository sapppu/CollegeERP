package com.college.erp.repository;

import com.college.erp.model.InventoryAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface InventoryAssetRepository extends JpaRepository<InventoryAsset, Long> {
    List<InventoryAsset> findByStatus(String status);
    List<InventoryAsset> findByCategory(String category);
    List<InventoryAsset> findByDepartment(String department);

    @Query("SELECT COUNT(a) FROM InventoryAsset a WHERE a.status = 'Active'")
    long countActive();

    @Query("SELECT COUNT(a) FROM InventoryAsset a WHERE a.condition = 'Under Repair'")
    long countUnderRepair();

    @Query("SELECT COALESCE(SUM(a.purchasePrice * a.quantity), 0) FROM InventoryAsset a WHERE a.status = 'Active'")
    Double getTotalAssetValue();
}