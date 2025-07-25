package com.collageCompetation.collegeCompetationDemo.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "competition_name",nullable = false)
    private String competitionName;

    @Column(name = "date",nullable = false)
    private java.sql.Date date;

    @Column(name = "college_name")
    private String collegeName;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User registeredBy;

    public Competition() {
    }

    public Competition(String competitionName, Date date, String collegeName, User registeredBy) {
        this.competitionName = competitionName;
        this.date = date;
        this.collegeName = collegeName;
        this.registeredBy = registeredBy;
    }

    public Competition(Long id, String competitionName, Date date, String collegeName, User registeredBy) {
        this.id = id;
        this.competitionName = competitionName;
        this.date = date;
        this.collegeName = collegeName;
        this.registeredBy = registeredBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public User getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(User registeredBy) {
        this.registeredBy = registeredBy;
    }

}
