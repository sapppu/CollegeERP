
package com.college.erp.controller.finance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance/expensebudget")
public class ExpenseBudgetController {
    @GetMapping
    public String page() {
        return "ExpenseBudgetController working";
    }
}
