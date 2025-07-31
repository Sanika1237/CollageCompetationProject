package com.collageCompetation.collegeCompetationDemo.controller;

import com.collageCompetation.collegeCompetationDemo.entity.User;
import com.collageCompetation.collegeCompetationDemo.repository.UserRepository;
import com.collageCompetation.collegeCompetationDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/by-competition")
    public ResponseEntity<List<User>> getUsersByCompetitionName(@RequestParam String competitionName) {
        return ResponseEntity.ok(userService.findUsersByCompetitionName(competitionName));
    }

    // THIS IS THE METHOD TO CHANGE
    @GetMapping("/users/by-date")
    public ResponseEntity<List<User>> getUsersByCompetitionDate(@RequestParam String date) { // Change 'dateString' to 'date' here
        try {
            LocalDate localDate = LocalDate.parse(date); // Parse the incoming 'date' string
            return ResponseEntity.ok(userService.findUsersByCompetitionDate(localDate)); // Pass LocalDate to service
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAllWithCompetitions();
        return ResponseEntity.ok(users);
    }
}