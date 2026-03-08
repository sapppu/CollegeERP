package com.college.erp.controller.student;

import com.college.erp.model.FeeStructure;
import com.college.erp.model.Student;
import com.college.erp.repository.FeeStructureRepository;
import com.college.erp.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FeePaymentController {

    private final StudentRepository studentRepo;
    private final FeeStructureRepository feeRepo;

    public FeePaymentController(StudentRepository studentRepo,
                                FeeStructureRepository feeRepo) {
        this.studentRepo = studentRepo;
        this.feeRepo     = feeRepo;
    }

    @GetMapping("/student/feepayment")
    public String viewFees(Authentication auth, Model model) {
        String username = auth.getName();
        Student student = studentRepo.findByUsername(username);
        model.addAttribute("student", student);

        if (student != null) {
            // Fees matching student's department + all-department fees
            List<FeeStructure> deptFees = feeRepo.findByDepartment(student.getDepartment());
            List<FeeStructure> allFees  = feeRepo.findByDepartment("All Departments");
            deptFees.addAll(allFees);

            double total = deptFees.stream()
                    .mapToDouble(f -> f.getAmount() != null ? f.getAmount() : 0)
                    .sum();

            model.addAttribute("feeList",    deptFees);
            model.addAttribute("totalFees",  total);
            model.addAttribute("totalCount", deptFees.size());
        }

        return "student/fee-payment";
    }
}