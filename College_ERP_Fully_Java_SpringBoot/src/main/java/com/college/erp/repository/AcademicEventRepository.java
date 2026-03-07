package com.college.erp.repository;

import com.college.erp.model.AcademicEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AcademicEventRepository extends JpaRepository<AcademicEvent, Long> {
    List<AcademicEvent> findByEventType(String eventType);
    List<AcademicEvent> findByStatus(String status);
}