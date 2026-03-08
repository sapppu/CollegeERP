package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lesson_plans")
public class LessonPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String topic;
    private String department;
    private String year;
    private String semester;
    private String planDate;
    private String duration;
    private String teachingMethod;
    private String resources;
    private String objectives;
    private String status;
    private String facultyUsername;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getPlanDate() { return planDate; }
    public void setPlanDate(String planDate) { this.planDate = planDate; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getTeachingMethod() { return teachingMethod; }
    public void setTeachingMethod(String teachingMethod) { this.teachingMethod = teachingMethod; }

    public String getResources() { return resources; }
    public void setResources(String resources) { this.resources = resources; }

    public String getObjectives() { return objectives; }
    public void setObjectives(String objectives) { this.objectives = objectives; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFacultyUsername() { return facultyUsername; }
    public void setFacultyUsername(String facultyUsername) { this.facultyUsername = facultyUsername; }
}