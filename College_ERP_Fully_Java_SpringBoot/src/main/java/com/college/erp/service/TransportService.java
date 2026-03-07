package com.college.erp.service;

import com.college.erp.model.Transport;
import com.college.erp.repository.TransportRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransportService {

    private final TransportRepository repo;

    public TransportService(TransportRepository repo) {
        this.repo = repo;
    }

    public void save(Transport transport) {
        repo.save(transport);
    }

    public List<Transport> getAll() {
        return repo.findAll();
    }

    public Transport getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void markFeePaid(Long id) {
        Transport t = repo.findById(id).get();
        t.setFeeStatus("Paid");
        repo.save(t);
    }

    public void markFeeUnpaid(Long id) {
        Transport t = repo.findById(id).get();
        t.setFeeStatus("Unpaid");
        repo.save(t);
    }

    public long countTotal() {
        return repo.count();
    }

    public long countActive() {
        return repo.countActive();
    }

    public long countFeeUnpaid() {
        return repo.countFeeUnpaid();
    }

    public Double getTotalFeeCollected() {
        Double v = repo.getTotalFeeCollected();
        return v != null ? v : 0.0;
    }
}