package com.college.erp.repository;

import com.college.erp.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface TransportRepository extends JpaRepository<Transport, Long> {
    List<Transport> findByStatus(String status);
    List<Transport> findByFeeStatus(String feeStatus);
    List<Transport> findByRouteNumber(String routeNumber);

    @Query("SELECT COUNT(t) FROM Transport t WHERE t.status = 'Active'")
    long countActive();

    @Query("SELECT COUNT(t) FROM Transport t WHERE t.feeStatus = 'Unpaid'")
    long countFeeUnpaid();

    @Query("SELECT COALESCE(SUM(t.feeAmount), 0) FROM Transport t WHERE t.feeStatus = 'Paid'")
    Double getTotalFeeCollected();
}