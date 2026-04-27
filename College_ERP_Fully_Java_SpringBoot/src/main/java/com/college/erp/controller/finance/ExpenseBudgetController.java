package com.college.erp.controller.finance;

import com.college.erp.model.Expense;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.service.ExpenseService;
import com.college.erp.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExpenseBudgetController {

    private final ExpenseService expenseService;
    private final DepartmentRepository deptRepo;
    private final ExcelService excelService;

    public ExpenseBudgetController(ExpenseService expenseService,
                                   DepartmentRepository deptRepo,
                                   ExcelService excelService) {
        this.expenseService = expenseService;
        this.deptRepo = deptRepo;
        this.excelService = excelService;
    }

    @GetMapping("/admin/expensebudget")
    public String viewExpenses(Model model) {
        model.addAttribute("list", expenseService.getAll());
        model.addAttribute("totalRecords", expenseService.getAll().size());
        model.addAttribute("approvedCount", expenseService.countApproved());
        model.addAttribute("pendingCount", expenseService.countPending());
        model.addAttribute("rejectedCount", expenseService.countRejected());
        model.addAttribute("totalApproved", expenseService.getTotalApproved());
        model.addAttribute("totalPending", expenseService.getTotalPending());
        return "admin/expense-budget";
    }

    @GetMapping("/admin/add-expense")
    public String addExpensePage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/add-expense";
    }

    @PostMapping("/admin/save-expense")
    public String saveExpense(Expense expense) {
        expenseService.save(expense);
        return "redirect:/admin/expensebudget";
    }

    @GetMapping("/admin/edit-expense/{id}")
    public String editExpensePage(@PathVariable Long id, Model model) {
        model.addAttribute("expense", expenseService.getById(id));
        model.addAttribute("departments", deptRepo.findAll());
        return "admin/edit-expense";
    }

    @PostMapping("/admin/update-expense")
    public String updateExpense(Expense expense) {
        expenseService.save(expense);
        return "redirect:/admin/expensebudget";
    }

    @GetMapping("/admin/delete-expense/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.delete(id);
        return "redirect:/admin/expensebudget";
    }

    @GetMapping("/admin/approve-expense/{id}")
    public String approveExpense(@PathVariable Long id) {
        expenseService.approve(id);
        return "redirect:/admin/expensebudget";
    }

    @GetMapping("/admin/reject-expense/{id}")
    public String rejectExpense(@PathVariable Long id) {
        expenseService.reject(id);
        return "redirect:/admin/expensebudget";
    }

    // ── EXPORT ──────────────────────────────────────────────────────────

    @GetMapping("/admin/export-expenses")
    public void exportExpenses(HttpServletResponse response) throws IOException {
        String[] headers = {"ID", "Title", "Category", "Department", "Amount", "Expense Date", "Academic Year", "Payment Mode", "Description", "Status"};
        List<Object[]> rows = new ArrayList<>();
        for (Expense e : expenseService.getAll()) {
            rows.add(new Object[]{e.getId(), e.getTitle(), e.getCategory(), e.getDepartment(), e.getAmount(), e.getExpenseDate(), e.getAcademicYear(), e.getPaymentMode(), e.getDescription(), e.getStatus()});
        }
        excelService.exportToExcel(response, "expenses", "Expenses", headers, rows);
    }

    // ── IMPORT ──────────────────────────────────────────────────────────

    @PostMapping("/admin/import-expenses")
    public String importExpenses(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        if (file.isEmpty()) { ra.addFlashAttribute("importError", "Please select an Excel file."); return "redirect:/admin/add-expense"; }
        if (!excelService.isValidExcelFile(file)) { ra.addFlashAttribute("importError", "Only .xlsx and .xls files are supported."); return "redirect:/admin/add-expense"; }

        List<String> errors = new ArrayList<>();
        int successCount = 0, totalRows = 0;
        try (Workbook workbook = excelService.openWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) { ra.addFlashAttribute("importError", "Excel file is empty."); return "redirect:/admin/add-expense"; }

            String[][] aliases = {
                {"title", "expensetitle"},
                {"category", "expensecategory"},
                {"department", "dept"},
                {"amount", "expenseamount"},
                {"expensedate", "date"},
                {"academicyear", "acadyear"},
                {"paymentmode", "payment", "mode"},
                {"description", "desc"},
                {"status"}
            };
            int[] colMap = excelService.mapColumns(headerRow, aliases);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                totalRows++;
                try {
                    Expense e = new Expense();
                    e.setTitle(excelService.getCellString(row, colMap[0]));
                    e.setCategory(excelService.getCellString(row, colMap[1]));
                    e.setDepartment(excelService.getCellString(row, colMap[2]));
                    e.setAmount(excelService.getCellDouble(row, colMap[3]));
                    e.setExpenseDate(excelService.getCellString(row, colMap[4]));
                    e.setAcademicYear(excelService.getCellString(row, colMap[5]));
                    e.setPaymentMode(excelService.getCellString(row, colMap[6]));
                    e.setDescription(excelService.getCellString(row, colMap[7]));
                    String status = excelService.getCellString(row, colMap[8]);
                    e.setStatus(status.isBlank() ? "Pending" : status);

                    if (e.getTitle() == null || e.getTitle().isBlank()) { errors.add("Row " + (i+1) + ": Title is required."); continue; }
                    if (e.getAmount() == null) { errors.add("Row " + (i+1) + ": Amount is required."); continue; }

                    expenseService.save(e);
                    successCount++;
                } catch (Exception ex) {
                    errors.add("Row " + (i+1) + ": " + ex.getMessage());
                }
            }
        } catch (Exception e) {
            errors.add("Failed to read Excel file: " + e.getMessage());
        }

        ra.addFlashAttribute("importSuccess", successCount);
        ra.addFlashAttribute("importFailed", totalRows - successCount);
        ra.addFlashAttribute("importTotal", totalRows);
        if (!errors.isEmpty()) ra.addFlashAttribute("importErrors", errors);
        return "redirect:/admin/add-expense";
    }
}