
package com.college.erp.controller.finance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance/feestructure")
public class FeeStructureController {
    @GetMapping
    public String page() {
        return "FeeStructureController working";
    }
}
