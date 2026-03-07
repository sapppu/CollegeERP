package com.college.erp.repository;

import com.college.erp.model.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface SystemLogRepository extends JpaRepository<SystemLog, Long> {
    List<SystemLog> findByLogLevel(String logLevel);
    List<SystemLog> findByModule(String module);
    List<SystemLog> findByPerformedBy(String performedBy);

    @Query("SELECT s FROM SystemLog s ORDER BY s.id DESC")
    List<SystemLog> findAllOrderByIdDesc();

    @Modifying
    @Transactional
    @Query("DELETE FROM SystemLog s")
    void clearAllLogs();
}