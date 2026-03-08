package com.college.erp.service;

import com.college.erp.model.PlacementDrive;
import com.college.erp.repository.PlacementDriveRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlacementDriveService {

    private final PlacementDriveRepository repo;

    public PlacementDriveService(PlacementDriveRepository repo) {
        this.repo = repo;
    }

    public void save(PlacementDrive drive) { repo.save(drive); }

    public List<PlacementDrive> getAll() { return repo.findAll(); }

    public PlacementDrive getById(Long id) { return repo.findById(id).get(); }

    public void delete(Long id) { repo.deleteById(id); }

    public long countUpcoming() { return repo.findByStatus("Upcoming").size(); }

    public long countOnCampus() { return repo.findByJobType("On-Campus").size(); }

    public long countOffCampus() { return repo.findByJobType("Off-Campus").size(); }
}