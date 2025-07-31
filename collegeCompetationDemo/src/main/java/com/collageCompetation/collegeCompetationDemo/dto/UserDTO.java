package com.collageCompetation.collegeCompetationDemo.dto;

import com.collageCompetation.collegeCompetationDemo.entity.User; // Import the User entity

/**
 * Data Transfer Object (DTO) for User responses.
 * This class defines the subset of User entity fields that should be exposed
 * in API responses, ensuring sensitive information like passwords is not included.
 */
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String collegeName;
    // IMPORTANT: Do NOT include sensitive fields like 'password' here.
    // If 'role' is needed, you can add it, but consider if it's always necessary.
    // private User.Role role;

    // Default constructor (required by Jackson)
    public UserDTO() {}

    /**
     * Constructor to create a UserDTO from a User entity.
     * Maps relevant fields from the entity to the DTO.
     *
     * @param user The User entity to convert.
     */
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.collegeName = user.getCollegeName();
        // If you decide to include role:
        // this.role = user.getRole();
    }

    // --- Getters and Setters ---
    // These are necessary for Jackson to serialize the object into JSON.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
