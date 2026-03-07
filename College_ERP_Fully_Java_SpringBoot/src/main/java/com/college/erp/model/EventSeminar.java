package com.college.erp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_seminars")
public class EventSeminar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;
    private String eventType;        // Seminar, Workshop, Cultural, Sports, Technical, Other
    private String organizer;
    private String department;
    private String venue;
    private String startDate;
    private String endDate;
    private String startTime;
    private String expectedAttendees;
    private String chiefGuest;
    private Double budget;
    private String description;
    private String status;           // Upcoming, Ongoing, Completed, Cancelled

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public String getOrganizer() { return organizer; }
    public void setOrganizer(String organizer) { this.organizer = organizer; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getExpectedAttendees() { return expectedAttendees; }
    public void setExpectedAttendees(String expectedAttendees) { this.expectedAttendees = expectedAttendees; }

    public String getChiefGuest() { return chiefGuest; }
    public void setChiefGuest(String chiefGuest) { this.chiefGuest = chiefGuest; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}