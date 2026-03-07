package com.college.erp.repository;

import com.college.erp.model.EventSeminar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventSeminarRepository extends JpaRepository<EventSeminar, Long> {
    List<EventSeminar> findByStatus(String status);
    List<EventSeminar> findByEventType(String eventType);
    List<EventSeminar> findByDepartment(String department);
}