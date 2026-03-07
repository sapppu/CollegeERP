package com.college.erp.controller.finance;

import com.college.erp.model.Expense;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExpenseBudgetController {

    private final ExpenseService expenseService;
    private final DepartmentRepository deptRepo;

    public ExpenseBudgetController(ExpenseService expenseService,
                                   DepartmentRepository deptRepo) {
        this.expenseService = expenseService;
        this.deptRepo = deptRepo;
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
}