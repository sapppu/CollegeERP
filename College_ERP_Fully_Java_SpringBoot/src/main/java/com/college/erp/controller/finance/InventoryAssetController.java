package com.college.erp.controller.finance;

import com.college.erp.model.InventoryAsset;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.service.InventoryAssetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class InventoryAssetController {

    private final InventoryAssetService assetService;
    private final DepartmentRepository deptRepo;

    public InventoryAssetController(InventoryAssetService assetService,
                                    DepartmentRepository deptRepo) {
        this.assetService = assetService;
        this.deptRepo = deptRepo;
    }

    @GetMapping("/admin/inventoryasset")
    public String viewAssets(Model model) {
        model.addAttribute("list", assetService.getAll());
        model.addAttribute("totalRecords", assetService.countTotal());
        model.addAttribute("activeCount", assetService.countActive());
        model.addAttribute("underRepairCount", assetService.countUnderRepair());
        model.addAttribute("totalAssetValue", assetService.getTotalAssetValue());
        return "admin/inventory-asset";
    }

    @GetMapping("/admin/add-asset")
    public String addAssetPage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-asset";
    }

    @PostMapping("/admin/save-asset")
    public String saveAsset(InventoryAsset asset) {
        assetService.save(asset);
        return "redirect:/admin/inventoryasset";
    }

    @GetMapping("/admin/edit-asset/{id}")
    public String editAssetPage(@PathVariable Long id, Model model) {
        model.addAttribute("asset", assetService.getById(id));
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-asset";
    }

    @PostMapping("/admin/update-asset")
    public String updateAsset(InventoryAsset asset) {
        assetService.save(asset);
        return "redirect:/admin/inventoryasset";
    }

    @GetMapping("/admin/delete-asset/{id}")
    public String deleteAsset(@PathVariable Long id) {
        assetService.delete(id);
        return "redirect:/admin/inventoryasset";
    }

    @GetMapping("/admin/dispose-asset/{id}")
    public String disposeAsset(@PathVariable Long id) {
        assetService.markDisposed(id);
        return "redirect:/admin/inventoryasset";
    }
}