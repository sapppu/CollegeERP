package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "research_publications")
public class Research {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String authors;
    private String publicationType;
    private String journalConferenceName;
    private String publishedDate;
    private String volume;
    private String issueNumber;
    private String pageNumbers;
    private String doi;
    private String description;
    private String status;
    private String facultyUsername;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String t) { this.title = t; }

    public String getAuthors() { return authors; }
    public void setAuthors(String a) { this.authors = a; }

    public String getPublicationType() { return publicationType; }
    public void setPublicationType(String p) { this.publicationType = p; }

    public String getJournalConferenceName() { return journalConferenceName; }
    public void setJournalConferenceName(String j) { this.journalConferenceName = j; }

    public String getPublishedDate() { return publishedDate; }
    public void setPublishedDate(String p) { this.publishedDate = p; }

    public String getVolume() { return volume; }
    public void setVolume(String v) { this.volume = v; }

    public String getIssueNumber() { return issueNumber; }
    public void setIssueNumber(String i) { this.issueNumber = i; }

    public String getPageNumbers() { return pageNumbers; }
    public void setPageNumbers(String p) { this.pageNumbers = p; }

    public String getDoi() { return doi; }
    public void setDoi(String d) { this.doi = d; }

    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }

    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    public String getFacultyUsername() { return facultyUsername; }
    public void setFacultyUsername(String f) { this.facultyUsername = f; }
}