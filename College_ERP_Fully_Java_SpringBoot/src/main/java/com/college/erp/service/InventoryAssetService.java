package com.college.erp.service;

import com.college.erp.model.InventoryAsset;
import com.college.erp.repository.InventoryAssetRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InventoryAssetService {

    private final InventoryAssetRepository repo;

    public InventoryAssetService(InventoryAssetRepository repo) {
        this.repo = repo;
    }

    public void save(InventoryAsset asset) {
        repo.save(asset);
    }

    public List<InventoryAsset> getAll() {
        return repo.findAll();
    }

    public InventoryAsset getById(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void markDisposed(Long id) {
        InventoryAsset a = repo.findById(id).get();
        a.setStatus("Disposed");
        repo.save(a);
    }

    public long countTotal() {
        return repo.count();
    }

    public long countActive() {
        return repo.countActive();
    }

    public long countUnderRepair() {
        return repo.countUnderRepair();
    }

    public Double getTotalAssetValue() {
        Double v = repo.getTotalAssetValue();
        return v != null ? v : 0.0;
    }
}