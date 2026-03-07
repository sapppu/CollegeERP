package com.college.erp.service;

import com.college.erp.model.SystemLog;
import com.college.erp.repository.SystemLogRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SystemLogService {

    private final SystemLogRepository repo;

    public SystemLogService(SystemLogRepository repo) {
        this.repo = repo;
    }

    public void log(String action, String module, String performedBy, String description, String logLevel) {
        repo.save(new SystemLog(action, module, performedBy, description, logLevel));
    }

    public List<SystemLog> getAllLogs() {
        return repo.findAllOrderByIdDesc();
    }

    public void deleteLog(Long id) {
        repo.deleteById(id);
    }

    public void clearAllLogs() {
        repo.clearAllLogs();
    }

    public long countTotal() { return repo.count(); }

    public long countByLevel(String level) {
        return repo.findByLogLevel(level).size();
    }
}