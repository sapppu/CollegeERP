package com.college.erp.controller.faculty;

import com.college.erp.model.Faculty;
import com.college.erp.model.InternalMark;
import com.college.erp.model.Student;
import com.college.erp.repository.CourseRepository;
import com.college.erp.repository.FacultyRepository;
import com.college.erp.repository.StudentRepository;
import com.college.erp.service.InternalMarkService;
import com.college.erp.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InternalAssessmentController {

    private final InternalMarkService markService;
    private final FacultyRepository   facultyRepo;
    private final StudentRepository   studentRepo;
    private final CourseRepository    courseRepo;
    private final ExcelService        excelService;

    public InternalAssessmentController(InternalMarkService markService,
                                        FacultyRepository   facultyRepo,
                                        StudentRepository   studentRepo,
                                        CourseRepository    courseRepo,
                                        ExcelService        excelService) {
        this.markService = markService;
        this.facultyRepo = facultyRepo;
        this.studentRepo = studentRepo;
        this.courseRepo  = courseRepo;
        this.excelService = excelService;
    }

    @GetMapping("/faculty/internalassessment")
    public String view(Authentication auth,
                       @RequestParam(required = false) String filterSubject,
                       @RequestParam(required = false) String filterType,
                       Model model) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);
        model.addAttribute("faculty", faculty);

        List<InternalMark> allMarks = markService.getByFaculty(username);
        model.addAttribute("allMarks",     allMarks);
        model.addAttribute("totalEntries", markService.getTotalEntries(username));
        model.addAttribute("avgMarks",     markService.getAvgMarks(username));
        model.addAttribute("subjects",     markService.getSubjects(username));

        if (filterSubject != null && !filterSubject.isBlank()) {
            model.addAttribute("filteredMarks",
                    markService.getByFacultyAndSubject(username, filterSubject));
            model.addAttribute("filterSubject", filterSubject);
        } else if (filterType != null && !filterType.isBlank()) {
            model.addAttribute("filteredMarks",
                    markService.getByFacultyAndType(username, filterType));
            model.addAttribute("filterType", filterType);
        }

        List<Student> students = faculty != null
                ? studentRepo.findAll().stream()
                .filter(s -> faculty.getDepartment().equals(s.getDepartment()))
                .toList()
                : List.of();
        model.addAttribute("students", students);
        model.addAttribute("courses",
                faculty != null ? courseRepo.findByDepartment(faculty.getDepartment()) : List.of());

        return "faculty/internal-assessment";
    }

    @PostMapping("/faculty/save-marks-bulk")
    public String saveMarksBulk(Authentication auth,
                                @RequestParam String subject,
                                @RequestParam String assessmentType,
                                @RequestParam Double totalMarks,
                                @RequestParam String examDate,
                                @RequestParam List<String> usernames,
                                @RequestParam List<Double> marksObtained,
                                @RequestParam(required = false) List<String> remarks) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);

        if (usernames == null || marksObtained == null) {
            return "redirect:/faculty/internalassessment?error";
        }

        int count = Math.min(usernames.size(), marksObtained.size());

        for (int i = 0; i < count; i++) {
            String stuUsername = usernames.get(i);
            Student student = studentRepo.findAll().stream()
                    .filter(s -> stuUsername.equals(s.getUsername()))
                    .findFirst().orElse(null);
            if (student == null) continue;

            InternalMark mark = new InternalMark();
            mark.setStudentUsername(stuUsername);
            mark.setStudentName(student.getName());
            mark.setDepartment(faculty != null ? faculty.getDepartment() : "");
            mark.setYear(student.getYear());
            mark.setSubject(subject);
            mark.setAssessmentType(assessmentType);
            mark.setMarksObtained(marksObtained.get(i));
            mark.setTotalMarks(totalMarks);
            mark.setExamDate(examDate);
            mark.setRemarks(remarks != null && i < remarks.size() ? remarks.get(i) : "");
            mark.setFacultyUsername(username);
            markService.save(mark);
        }

        return "redirect:/faculty/internalassessment?success";
    }

    @GetMapping("/faculty/edit-mark/{id}")
    public String editMark(@PathVariable Long id, Model model, Authentication auth) {
        Faculty faculty = facultyRepo.findByUsername(auth.getName());
        model.addAttribute("mark",    markService.getById(id));
        model.addAttribute("faculty", faculty);
        model.addAttribute("courses",
                faculty != null ? courseRepo.findByDepartment(faculty.getDepartment()) : List.of());
        return "faculty/edit-mark";
    }

    @PostMapping("/faculty/update-mark")
    public String updateMark(InternalMark mark, Authentication auth) {
        mark.setFacultyUsername(auth.getName());
        markService.save(mark);
        return "redirect:/faculty/internalassessment";
    }

    @GetMapping("/faculty/delete-mark/{id}")
    public String deleteMark(@PathVariable Long id) {
        markService.delete(id);
        return "redirect:/faculty/internalassessment";
    }

    // ── EXPORT ──────────────────────────────────────────────────────────

    @GetMapping("/faculty/export-marks")
    public void exportMarks(Authentication auth, HttpServletResponse response) throws IOException {
        String username = auth.getName();
        String[] headers = {"ID", "Student Username", "Student Name", "Department", "Year", "Subject", "Assessment Type", "Marks Obtained", "Total Marks", "Percentage", "Exam Date", "Remarks"};
        List<Object[]> rows = new ArrayList<>();
        for (InternalMark m : markService.getByFaculty(username)) {
            double pct = (m.getTotalMarks() != null && m.getTotalMarks() > 0 && m.getMarksObtained() != null)
                    ? Math.round((m.getMarksObtained() / m.getTotalMarks()) * 10000.0) / 100.0 : 0;
            rows.add(new Object[]{m.getId(), m.getStudentUsername(), m.getStudentName(), m.getDepartment(), m.getYear(), m.getSubject(), m.getAssessmentType(), m.getMarksObtained(), m.getTotalMarks(), pct + "%", m.getExamDate(), m.getRemarks()});
        }
        excelService.exportToExcel(response, "internal_marks", "Internal Marks", headers, rows);
    }

    // ── IMPORT ──────────────────────────────────────────────────────────

    @PostMapping("/faculty/import-marks")
    public String importMarks(Authentication auth,
                              @RequestParam("file") MultipartFile file,
                              RedirectAttributes ra) {
        String username = auth.getName();
        Faculty faculty = facultyRepo.findByUsername(username);

        if (file.isEmpty()) { ra.addFlashAttribute("importError", "Please select an Excel file."); return "redirect:/faculty/internalassessment"; }
        if (!excelService.isValidExcelFile(file)) { ra.addFlashAttribute("importError", "Only .xlsx and .xls files are supported."); return "redirect:/faculty/internalassessment"; }

        List<String> errors = new ArrayList<>();
        int successCount = 0, totalRows = 0;
        try (Workbook workbook = excelService.openWorkbook(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) { ra.addFlashAttribute("importError", "Excel file is empty."); return "redirect:/faculty/internalassessment"; }

            String[][] aliases = {
                {"studentusername", "username", "studentid"},
                {"subject", "course", "coursename"},
                {"assessmenttype", "type", "examtype"},
                {"marksobtained", "marks", "obtained", "score"},
                {"totalmarks", "total", "maxmarks"},
                {"examdate", "date"},
                {"remarks", "comment", "notes"}
            };
            int[] colMap = excelService.mapColumns(headerRow, aliases);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                totalRows++;
                try {
                    String stuUsername = excelService.getCellString(row, colMap[0]);
                    if (stuUsername.isBlank()) { errors.add("Row " + (i+1) + ": Student Username is required."); continue; }

                    Student student = studentRepo.findAll().stream()
                            .filter(s -> stuUsername.equals(s.getUsername()))
                            .findFirst().orElse(null);
                    if (student == null) { errors.add("Row " + (i+1) + ": Student '" + stuUsername + "' not found."); continue; }

                    InternalMark mark = new InternalMark();
                    mark.setStudentUsername(stuUsername);
                    mark.setStudentName(student.getName());
                    mark.setDepartment(faculty != null ? faculty.getDepartment() : "");
                    mark.setYear(student.getYear());
                    mark.setSubject(excelService.getCellString(row, colMap[1]));
                    mark.setAssessmentType(excelService.getCellString(row, colMap[2]));
                    mark.setMarksObtained(excelService.getCellDouble(row, colMap[3]));
                    mark.setTotalMarks(excelService.getCellDouble(row, colMap[4]));
                    mark.setExamDate(excelService.getCellString(row, colMap[5]));
                    mark.setRemarks(excelService.getCellString(row, colMap[6]));
                    mark.setFacultyUsername(username);

                    if (mark.getSubject() == null || mark.getSubject().isBlank()) { errors.add("Row " + (i+1) + ": Subject is required."); continue; }
                    if (mark.getMarksObtained() == null) { errors.add("Row " + (i+1) + ": Marks Obtained is required."); continue; }
                    if (mark.getTotalMarks() == null) { errors.add("Row " + (i+1) + ": Total Marks is required."); continue; }

                    markService.save(mark);
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
        return "redirect:/faculty/internalassessment";
    }
}