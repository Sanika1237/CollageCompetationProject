package com.collageCompetation.collegeCompetationDemo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference; // Import this
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "competitions")
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "competitionName",nullable = false)
    private String name;  // Competition name

    @Column(nullable = false)
    private LocalDate date;  // Competition date

    @Column(name = "college_name", nullable = false)
    private String collegeName;

    @ManyToOne(fetch = FetchType.EAGER) // <-- Change LAZY to EAGER here
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    // Constructors
    public Competition() {}

    public Competition(Long id, String name, LocalDate date, String collegeName, User user) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.collegeName = collegeName;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
