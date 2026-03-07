package com.college.erp.repository;

import com.college.erp.model.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface HostelRepository extends JpaRepository<Hostel, Long> {
    List<Hostel> findByStatus(String status);
    List<Hostel> findByFeeStatus(String feeStatus);
    List<Hostel> findByHostelBlock(String hostelBlock);

    @Query("SELECT COUNT(h) FROM Hostel h WHERE h.status = 'Active'")
    long countActive();

    @Query("SELECT COUNT(h) FROM Hostel h WHERE h.feeStatus = 'Unpaid'")
    long countFeeUnpaid();

    @Query("SELECT COALESCE(SUM(h.feeAmount), 0) FROM Hostel h WHERE h.feeStatus = 'Paid'")
    Double getTotalFeeCollected();
}