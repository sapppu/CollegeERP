package com.college.erp.service;

import com.college.erp.model.Student;
import com.college.erp.model.User;
import com.college.erp.repository.StudentRepository;
import com.college.erp.repository.UserRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminStudentService {

    private final StudentRepository studentRepo;
    private final UserRepository userRepo;

    public AdminStudentService(StudentRepository studentRepo, UserRepository userRepo) {
        this.studentRepo = studentRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public void saveStudentWithUser(Student student, String password) {
        User user = new User();
        user.setUsername(student.getUsername());
        user.setPassword(password);
        user.setRole("ROLE_STUDENT");
        userRepo.save(user);
        studentRepo.save(student);
    }

    @Transactional
    public void updateStudent(Student student) {
        studentRepo.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
        userRepo.findByUsername(student.getUsername())
                .ifPresent(userRepo::delete);
        studentRepo.deleteById(id);
    }

    /**
     * Import students from an Excel file (.xlsx or .xls).
     * Expected columns (header row):
     *   Name | EnrollmentNo | Email | Phone | Gender | DOB | Address | Department | Year | Username | Password
     *
     * @return ImportResult with counts and error details
     */
    @Transactional
    public ImportResult importStudentsFromExcel(MultipartFile file) {
        List<String> errors = new ArrayList<>();
        int successCount = 0;
        int totalRows = 0;

        try (InputStream is = file.getInputStream()) {
            Workbook workbook;
            String fileName = file.getOriginalFilename();
            if (fileName != null && fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(is);
            } else {
                workbook = new XSSFWorkbook(is);
            }

            Sheet sheet = workbook.getSheetAt(0);
            int lastRow = sheet.getLastRowNum();

            // Read header row to determine column indices
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                errors.add("Excel file is empty or has no header row.");
                workbook.close();
                return new ImportResult(0, 0, errors);
            }

            int[] colMap = mapColumns(headerRow);

            for (int i = 1; i <= lastRow; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                totalRows++;
                try {
                    Student student = new Student();
                    student.setName(getCellString(row, colMap[0]));
                    student.setEnrollmentNo(getCellString(row, colMap[1]));
                    student.setEmail(getCellString(row, colMap[2]));
                    student.setPhone(getCellString(row, colMap[3]));
                    student.setGender(getCellString(row, colMap[4]));
                    student.setDob(getCellString(row, colMap[5]));
                    student.setAddress(getCellString(row, colMap[6]));
                    student.setDepartment(getCellString(row, colMap[7]));
                    student.setYear(getCellString(row, colMap[8]));
                    student.setUsername(getCellString(row, colMap[9]));
                    String password = getCellString(row, colMap[10]);

                    // Basic validations
                    if (student.getName() == null || student.getName().isBlank()) {
                        errors.add("Row " + (i + 1) + ": Name is required.");
                        continue;
                    }
                    if (student.getEnrollmentNo() == null || student.getEnrollmentNo().isBlank()) {
                        errors.add("Row " + (i + 1) + ": Enrollment No is required.");
                        continue;
                    }
                    if (student.getUsername() == null || student.getUsername().isBlank()) {
                        errors.add("Row " + (i + 1) + ": Username is required.");
                        continue;
                    }
                    if (password == null || password.isBlank()) {
                        errors.add("Row " + (i + 1) + ": Password is required.");
                        continue;
                    }

                    // Check for duplicate username
                    if (userRepo.findByUsername(student.getUsername()).isPresent()) {
                        errors.add("Row " + (i + 1) + ": Username '" + student.getUsername() + "' already exists.");
                        continue;
                    }

                    // Save user and student
                    User user = new User();
                    user.setUsername(student.getUsername());
                    user.setPassword(password);
                    user.setRole("ROLE_STUDENT");
                    userRepo.save(user);
                    studentRepo.save(student);
                    successCount++;

                } catch (Exception e) {
                    errors.add("Row " + (i + 1) + ": " + e.getMessage());
                }
            }
            workbook.close();

        } catch (Exception e) {
            errors.add("Failed to read Excel file: " + e.getMessage());
        }

        return new ImportResult(successCount, totalRows, errors);
    }

    /**
     * Map header names to column indices.
     * Returns an int array of size 11 for:
     *   [0]=Name, [1]=EnrollmentNo, [2]=Email, [3]=Phone, [4]=Gender,
     *   [5]=DOB, [6]=Address, [7]=Department, [8]=Year, [9]=Username, [10]=Password
     * Defaults to positional (0–10) if headers don't match.
     */
    private int[] mapColumns(Row headerRow) {
        int[] map = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Each entry: index → list of exact-match aliases (normalized: lowercase, no spaces/underscores)
        String[][] aliases = {
            /* 0  Name         */ {"name", "fullname", "studentname"},
            /* 1  EnrollmentNo */ {"enrollmentno", "enrollno", "enrollment", "rollno", "rollnumber"},
            /* 2  Email        */ {"email", "emailid", "emailaddress"},
            /* 3  Phone        */ {"phone", "phoneno", "phonenumber", "mobile", "mobileno"},
            /* 4  Gender       */ {"gender", "sex"},
            /* 5  DOB          */ {"dob", "dateofbirth", "birthdate"},
            /* 6  Address      */ {"address", "fulladdress"},
            /* 7  Department   */ {"department", "dept", "departmentname", "branch"},
            /* 8  Year         */ {"year", "academicyear", "studyyear"},
            /* 9  Username     */ {"username", "userid", "loginid"},
            /* 10 Password     */ {"password", "pass", "loginpassword"}
        };

        for (int c = 0; c <= headerRow.getLastCellNum(); c++) {
            Cell cell = headerRow.getCell(c);
            if (cell == null) continue;
            String val = cell.getStringCellValue().trim().toLowerCase()
                    .replace(" ", "").replace("_", "").replace(".", "");
            for (int e = 0; e < aliases.length; e++) {
                for (String alias : aliases[e]) {
                    if (val.equals(alias)) {
                        map[e] = c;
                        break;
                    }
                }
            }
        }
        return map;
    }

    private String getCellString(Row row, int colIndex) {
        if (colIndex < 0) return "";
        Cell cell = row.getCell(colIndex);
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING  -> cell.getStringCellValue().trim();
            case NUMERIC -> {
                double d = cell.getNumericCellValue();
                // If it looks like a whole number, drop the decimal
                if (d == Math.floor(d) && !Double.isInfinite(d)) {
                    yield String.valueOf((long) d);
                }
                yield String.valueOf(d);
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default      -> "";
        };
    }

    // ── Inner class for import results ──
    public static class ImportResult {
        private final int successCount;
        private final int totalRows;
        private final List<String> errors;

        public ImportResult(int successCount, int totalRows, List<String> errors) {
            this.successCount = successCount;
            this.totalRows = totalRows;
            this.errors = errors;
        }

        public int getSuccessCount()  { return successCount; }
        public int getTotalRows()     { return totalRows; }
        public int getFailedCount()   { return totalRows - successCount; }
        public List<String> getErrors() { return errors; }
        public boolean hasErrors()    { return !errors.isEmpty(); }
    }
}