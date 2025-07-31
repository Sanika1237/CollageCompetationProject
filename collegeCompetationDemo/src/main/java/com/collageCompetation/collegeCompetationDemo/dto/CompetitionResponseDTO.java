package com.collageCompetation.collegeCompetationDemo.dto;

import com.collageCompetation.collegeCompetationDemo.entity.Competition;
import com.collageCompetation.collegeCompetationDemo.entity.User; // Import the User entity if needed for conversion

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for Competition responses.
 * This class defines the structure of the JSON response when a Competition object is returned.
 * It includes a nested UserDTO to control which user fields are exposed.
 */
public class CompetitionResponseDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private String collegeName;
    private UserDTO user; // Nested DTO for User

    // Default constructor (required by Jackson for deserialization if you have other constructors)
    public CompetitionResponseDTO() {}

    /**
     * Constructor to create a CompetitionResponseDTO from a Competition entity.
     * It maps the entity fields to DTO fields and converts the associated User entity to a UserDTO.
     *
     * @param competition The Competition entity to convert.
     */
    public CompetitionResponseDTO(Competition competition) {
        this.id = competition.getId();
        this.name = competition.getName();
        // Ensure date is handled correctly. If Competition entity uses LocalDate, this is fine.
        this.date = competition.getDate();
        this.collegeName = competition.getCollegeName();

        // Check if the user is loaded and not null before creating the UserDTO
        // This handles cases where the user might be lazy-loaded and not initialized,
        // or if the user field is null for some reason.
        if (competition.getUser() != null) {
            // Create a UserDTO from the User entity
            this.user = new UserDTO(competition.getUser());
        }
    }

    // --- Getters and Setters ---
    // These are necessary for Jackson to serialize the object into JSON.

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
