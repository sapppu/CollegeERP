package com.college.erp.service;

import com.college.erp.model.Hostel;
import com.college.erp.repository.HostelRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HostelService {

    private final HostelRepository repo;

    public HostelService(HostelRepository repo) {
        this.repo = repo;
    }

    public void save(Hostel hostel) {
        repo.save(hostel);
    }

    public List<Hostel> getAll() {
        return repo.findAll();
    }

    public Hostel getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void markFeePaid(Long id) {
        Hostel h = repo.findById(id).get();
        h.setFeeStatus("Paid");
        repo.save(h);
    }

    public void markFeeUnpaid(Long id) {
        Hostel h = repo.findById(id).get();
        h.setFeeStatus("Unpaid");
        repo.save(h);
    }

    public void vacate(Long id) {
        Hostel h = repo.findById(id).get();
        h.setStatus("Vacated");
        repo.save(h);
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