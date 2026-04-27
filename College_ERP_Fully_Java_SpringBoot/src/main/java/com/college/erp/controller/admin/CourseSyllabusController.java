package com.college.erp.controller.admin;

import com.college.erp.model.Course;
import com.college.erp.repository.DepartmentRepository;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.service.CourseService;
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
public class CourseSyllabusController {

    private final CourseService courseService;
    private final DepartmentRepository deptRepo;
    private final FacultyRepository facultyRepo;
    private final ExcelService excelService;

    public CourseSyllabusController(CourseService courseService,
                                    DepartmentRepository deptRepo,
                                    FacultyRepository facultyRepo,
                                    ExcelService excelService) {
        this.courseService = courseService;
        this.deptRepo = deptRepo;
        this.facultyRepo = facultyRepo;
        this.excelService = excelService;
    }

    @GetMapping("/admin/coursesyllabus")
    public String viewCourses(Model model) {
        model.addAttribute("list", courseService.getAll());
        model.addAttribute("totalCourses", courseService.getAll().size());
        model.addAttribute("activeCourses", courseService.countActive());
        model.addAttribute("theoryCourses", courseService.countTheory());
        model.addAttribute("practicalCourses", courseService.countPractical());
        return "admin/course-syllabus";
    }

    @GetMapping("/admin/add-course")
    public String addCoursePage(Model model) {
        model.addAttribute("departments", deptRepo.findAll());
        model.addAttribute("facultyList", facultyRepo.findAll());
        return "admin/add-course";
    }

    @PostMapping("/admin/save-course")
    public String saveCourse(Course course) {
        courseService.save(course);
        return "redirect:/admin/coursesyllabus";
    }

    @GetMapping("/admin/edit-course/{id}")
    public String editCoursePage(@PathVariable Long id, Model model) {
        model.addAttribute("course", courseService.getById(id));
        model.addAttribute("departments", deptRepo.findAll());
        model.addAttribute("facultyList", facultyRepo.findAll());
        return "admin/edit-course";
    }

    @PostMapping("/admin/update-course")
    public String updateCourse(Course course) {
        courseService.save(course);
        return "redirect:/admin/coursesyllabus";
    }

    @GetMapping("/admin/delete-course/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return "redirect:/admin/coursesyllabus";
    }

    // ── EXPORT ──────────────────────────────────────────────────────────

    @GetMapping("/admin/export-courses")
    public void exportCourses(HttpServletResponse response) throws IOException {
        String[] headers = {"ID", "Course Code", "Course Name", "Department", "Year", "Semester", "Credits", "Course Type", "Faculty", "Description", "Status"};
        List<Object[]> rows = new ArrayList<>();
        for (Course c : courseService.getAll()) {
            rows.add(new Object[]{c.getId(), c.getCourseCode(), c.getCourseName(), c.getDepartment(), c.getYear(), c.getSemester(), c.getCredits(), c.getCourseType(), c.getFacultyName(), c.getDescription(), c.getStatus()});
        }
        excelService.exportToExcel(response, "courses", "Courses", headers, rows);
    }

    // ── IMPORT ──────────────────────────────────────────────────────────

    @PostMapping("/admin/import-courses")
    public String importCourses(@RequestParam("file") MultipartFile file, RedirectAttributes ra) {
        if (file.isEmpty()) { ra.addFlashAttribute("importError", "Please select an Excel file."); return "redirect:/admin/add-course"; }
        if (!excelService.isValidExcelFile(file)) { ra.addFlashAttribute("importError", "Only .xlsx and .xls files are supported."); return "redirect:/admin/add-course"; }

        List<String> errors = new ArrayList<>();
        int successCount = 0, totalRows = 0;
        try (Workbook workbook = excelService.openWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) { ra.addFlashAttribute("importError", "Excel file is empty."); return "redirect:/admin/add-course"; }

            String[][] aliases = {
                {"coursecode", "code"},
                {"coursename", "name", "course"},
                {"department", "dept"},
                {"year"},
                {"semester", "sem"},
                {"credits", "credit"},
                {"coursetype", "type"},
                {"faculty", "facultyname", "instructor"},
                {"description", "desc"},
                {"status"}
            };
            int[] colMap = excelService.mapColumns(headerRow, aliases);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                totalRows++;
                try {
                    Course c = new Course();
                    c.setCourseCode(excelService.getCellString(row, colMap[0]));
                    c.setCourseName(excelService.getCellString(row, colMap[1]));
                    c.setDepartment(excelService.getCellString(row, colMap[2]));
                    c.setYear(excelService.getCellString(row, colMap[3]));
                    c.setSemester(excelService.getCellString(row, colMap[4]));
                    c.setCredits(excelService.getCellString(row, colMap[5]));
                    c.setCourseType(excelService.getCellString(row, colMap[6]));
                    c.setFacultyName(excelService.getCellString(row, colMap[7]));
                    c.setDescription(excelService.getCellString(row, colMap[8]));
                    String status = excelService.getCellString(row, colMap[9]);
                    c.setStatus(status.isBlank() ? "Active" : status);

                    if (c.getCourseCode() == null || c.getCourseCode().isBlank()) { errors.add("Row " + (i+1) + ": Course Code is required."); continue; }
                    if (c.getCourseName() == null || c.getCourseName().isBlank()) { errors.add("Row " + (i+1) + ": Course Name is required."); continue; }

                    courseService.save(c);
                    successCount++;
                } catch (Exception e) {
                    errors.add("Row " + (i+1) + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            errors.add("Failed to read Excel file: " + e.getMessage());
        }

        ra.addFlashAttribute("importSuccess", successCount);
        ra.addFlashAttribute("importFailed", totalRows - successCount);
        ra.addFlashAttribute("importTotal", totalRows);
        if (!errors.isEmpty()) ra.addFlashAttribute("importErrors", errors);
        return "redirect:/admin/add-course";
    }
}