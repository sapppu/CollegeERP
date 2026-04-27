# College ERP System 🎓

A comprehensive, fully functional Enterprise Resource Planning (ERP) web application built for colleges and universities. Developed using Java, Spring Boot, Spring Security, Hibernate (JPA), and Thymeleaf. This system streamlines academic, administrative, and financial processes into a unified platform.

## 🌟 Key Features

*   **Role-Based Access Control (RBAC):** Dedicated dashboards and interfaces for `Admin`, `Faculty`, and `Student` roles.
*   **Centralized Utility Services:** Shared services (e.g., `ExcelService` for robust import/export across all modules).
*   **Comprehensive Modules:** Covers everything from student admissions and HR payroll to fee structure and attendance tracking.
*   **Modern, Responsive UI:** Uses Thymeleaf with CSS grids, flexbox, and modern design principles.
*   **Bulk Data Management:** Extensive support for downloading data templates and bulk-uploading records via `.xlsx` or `.xls`.

---

## 🏗️ Architecture & MVC Structure

This application strictly follows the **Model-View-Controller (MVC)** architectural pattern, leveraging the capabilities of Spring Boot.

### 1. Model (Database Entities & Data Access)
*   **Location:** `src/main/java/com/college/erp/model/`
*   **Explanation:** This layer contains standard JPA entity classes (e.g., `Student.java`, `Faculty.java`, `Attendance.java`). These classes define exactly how data is stored in the underlying PostgreSQL database. Hibernate translates these Java objects into database tables auto-magically (because of `spring.jpa.hibernate.ddl-auto=update`).
*   **Repositories:** `src/main/java/com/college/erp/repository/` interface layer extends Spring Data JPL's `JpaRepository` to provide built-in CRUD operations (Create, Read, Update, Delete) without needing to write raw SQL.

### 2. View (User Interface)
*   **Location:** `src/main/resources/templates/`
*   **Explanation:** The application uses **Thymeleaf**, a server-side Java template engine. HTML pages are bound to the backend data provided by the controllers.
*   **Structure:** Views are divided into subdirectories (`/admin/`, `/faculty/`, `/student/`) corresponding to the user's role.
*   **Data Binding:** Controllers pass Java objects to the view using the `Model` object (e.g., `model.addAttribute("students", studentList);`). Thymeleaf renders this data directly into the HTML tree before sending it to the user.

### 3. Controller (Request Handling & Routing)
*   **Location:** `src/main/java/com/college/erp/controller/`
*   **Explanation:** Controllers act as the traffic cops. They listen for HTTP requests (GET, POST), communicate with the service layer to process data, and decide which View (Thymeleaf template) to return to the user.
*   **Structure:** Separated logically by domain:
    *   `admin/` (e.g., `AdminStudentController`, `CourseSyllabusController`)
    *   `faculty/` (e.g., `AttendanceManagementController`)
    *   `finance/` (e.g., `FeeStructureController`, `ExpenseBudgetController`)

### 4. Service Layer (Business Logic)
*   **Location:** `src/main/java/com/college/erp/service/`
*   **Explanation:** Acts as an intermediate layer between Controllers and Repositories. This is where the heavy lifting and business rules happen.
*   **Example (`ExcelService.java`):** A custom utility service built using Apache POI. It provides a generalized way to export database records into a `.xlsx` file and read incoming user-uploaded Excel files. Controllers call this service instead of handling complex file streams themselves.

---

## 🛠️ Technology Stack

*   **Backend:** Java 17+, Spring Boot 3.x
*   **Security:** Spring Security (DaoAuthenticationProvider, Form Login)
*   **Database:** PostgreSQL (Configured via application.properties)
*   **ORM:** Hibernate / Spring Data JPA
*   **Frontend:** HTML5, CSS3, Thymeleaf, Vanilla JavaScript
*   **File Processing:** Apache POI (Excel import/export)
*   **Build Tool:** Maven

---

## 🗄️ Database Configuration & Entities

If you are asked to make changes to the database structure, here is how the system is wired:

### Connection Settings
The database connection is defined in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/erp_college
spring.datasource.username=erp_user
spring.datasource.password=sappu0
spring.jpa.hibernate.ddl-auto=update
```
*Note: Because `ddl-auto` is set to `update`, any new fields you add to the Java Model classes will automatically be added as columns to the PostgreSQL database on startup.*

### Core Tables / Entities
There are 24 distinct entities. The most heavily used are:
1.  **`User.java`**: The core authentication table mapping `username`, `password`, and `role` (`ROLE_ADMIN`, `ROLE_FACULTY`, `ROLE_STUDENT`).
2.  **`Student.java`**: Contains all demographic and academic info for a student. Linked logically to a row in `User`.
3.  **`Faculty.java`**: Contains staff details. Linked logically to a row in `User`.
4.  **`Course.java`**: Defines syllabus mapping, credits, and course status.
5.  **`InternalMark.java` & `Attendance.java`**: Logically mapped transaction tables that record academic progress per student for a given subject.

### Changing a Database Field
If your teacher asks you to add a new field (e.g., adding an `AadharNumber` to a Student):
1.  **Model:** Open `src/main/java/com/college/erp/model/Student.java`. Add `private String aadharNumber;` and generate Getters/Setters.
2.  **View:** Open `src/main/resources/templates/admin/add-student.html` and add an `<input name="aadharNumber">` field.
3.  **Controller:** Spring Boot automatically binds HTML form names to Model properties. Saving will just work! If you want to include it in the Excel download, update the array in `AdminStudentController.java` inside the `exportStudents` method.

---

## 🔄 The Data Flow (A Real-World Example)

**Scenario: An Admin imports a list of Students.**

1.  **View:** Admin clicks the "Import from Excel" tab in `add-student.html` and uploads a file. The form submits a `POST` request to `/admin/import-students`.
2.  **Controller:** The `AdminStudentController.java` intercepts the `/admin/import-students` POST mapping. It receives the `MultipartFile`.
3.  **Service (Utility):** The controller passes the file to the shared `ExcelService.java`. The `ExcelService` reads the file, normalizes the header strings, maps columns to their data types, and pulls out text values line by line.
4.  **Service / Repository (Insert):** The controller loops over the extracted rows, populates a new `Student` instance, creates complementary `User` credentials, and fires `.save()` on both the `UserRepository` and `StudentRepository`.
5.  **Database:** Hibernate generates `INSERT INTO` SQL statements and commits them to PostgreSQL.
6.  **Response:** The controller returns a `redirect:/admin/students` mapping, adding `FlashAttributes` (success messages). The user sees the updated UI.

---

## 🛡️ Security Overview

The application utilizes Spring Security (`src/main/java/com/college/erp/config/SecurityConfig.java` if present, or default auto-configuration).
*   **Users Table:** Users authenticate against the `user` table.
*   **Role Mappings:**
    *   `/admin/**` paths are restricted to `ROLE_ADMIN`.
    *   `/faculty/**` paths are restricted to `ROLE_FACULTY`.
    *   `/student/**` paths are restricted to `ROLE_STUDENT`.
*   *Note for development:* Currently, passwords may be stored in plain text using a `NoOpPasswordEncoder` for ease of testing. In a production environment, this is easily swapped to a `BCryptPasswordEncoder`.

---

## ⚡ Recent Major Additions: The Excel Data Engine

Bulk management is crucial for real-world ERP systems. A robust Excel Import/Export engine was recently injected into almost every module.

*   **Export:** Buttons (`📥 Export Excel`) located on the top right of list tables dynamically generate `.xlsx` files with colored headers. (e.g., `/admin/export-courses`).
*   **Import:** Full drag-and-drop zones included on `Add New...` pages (e.g., `add-faculty.html`). The import system is fault-tolerant—it maps columns dynamically based on alias names (e.g., it treats "Phone Num" and "Mobile" as the same column automatically) and skips failing rows while saving successful ones, providing an itemized error report to the user on the spot.

**Where is it configured?**
Every controller utilizing this injects `private final ExcelService excelService;`.

---

## ▶️ Running the Project

1.  Ensure **PostgreSQL** is running on `localhost:5432` with a database named `erp_college`.
2.  Ensure credentials (`erp_user` / `sappu0`) match your local postgres setup.
3.  Using Maven, run `mvn clean install`.
4.  Run the application using your IDE or via terminal: `mvn spring-boot:run`.
5.  Access the server at `http://localhost:8081`.

**Default Login:**
(Assuming the database is seeded or manually injected): Log in with standard Admin, Faculty, or Student credentials registered in the `User` table.
