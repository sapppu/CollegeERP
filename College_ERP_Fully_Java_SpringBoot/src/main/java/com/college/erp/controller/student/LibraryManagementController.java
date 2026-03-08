package com.college.erp.controller.student;

import com.college.erp.model.Student;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.BookService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryManagementController {

    private final StudentRepository studentRepo;
    private final BookService bookService;

    public LibraryManagementController(StudentRepository studentRepo,
                                       BookService bookService) {
        this.studentRepo = studentRepo;
        this.bookService = bookService;
    }

    @GetMapping("/student/librarymanagement")
    public String viewLibrary(Authentication auth,
                              @RequestParam(required = false) String search,
                              Model model) {
        String username = auth.getName();
        Student student = studentRepo.findByUsername(username);
        model.addAttribute("student", student);

        if (student != null) {
            // All books & dept books
            model.addAttribute("allBooks",
                    bookService.getAll());
            model.addAttribute("deptBooks",
                    bookService.getAll().stream()
                            .filter(b -> student.getDepartment().equals(b.getDepartment()))
                            .toList());
            model.addAttribute("availableBooks",
                    bookService.getAvailable());

            // Search
            if (search != null && !search.isBlank()) {
                model.addAttribute("searchResults", bookService.search(search));
                model.addAttribute("searchQuery", search);
            }

            model.addAttribute("totalBooks",     bookService.getAll().size());
            model.addAttribute("availableCount", bookService.countAvailable());
            model.addAttribute("deptCount",      bookService.countByDept(student.getDepartment()));
        }

        return "student/library-management";
    }
}