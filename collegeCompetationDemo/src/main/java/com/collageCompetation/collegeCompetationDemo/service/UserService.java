package com.collageCompetation.collegeCompetationDemo.service;

import com.collageCompetation.collegeCompetationDemo.entity.User;
import com.collageCompetation.collegeCompetationDemo.entity.User.Role;
import com.collageCompetation.collegeCompetationDemo.exception.ResourceNotFoundException;
import com.collageCompetation.collegeCompetationDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate; // Import LocalDate
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create user (Admin or User)
    public User createUser(User user) {
        // Password encoding is already handled in AuthController before calling this
        return userRepository.save(user);
    }

    // Find user by username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get users by role (ADMIN or USER)
    public List<User> getUsersByRole(Role role) {
        return userRepository.findUsersByRole(role);
    }

    // Get users by competition name
    public List<User> findUsersByCompetitionName(String competitionName) {
        return userRepository.findUsersByCompetitionName(competitionName);
    }

    // Get users by competition date
    // This method now accepts LocalDate
    public List<User> findUsersByCompetitionDate(LocalDate date) {
        // This will require a corresponding method in UserRepository that accepts LocalDate
        // e.g., userRepository.findUsersByCompetitionDate(date);
        // Assuming your UserRepository has a method like:
        // List<User> findUsersByCompetitions_Date(LocalDate date);
        // Or a custom query in the repository to handle this.
        return userRepository.findUsersByCompetitionDate(date); // Assuming this method now takes LocalDate
    }
}