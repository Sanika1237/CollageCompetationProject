package com.collageCompetation.collegeCompetationDemo.controller;


import com.collageCompetation.collegeCompetationDemo.entity.User;
import com.collageCompetation.collegeCompetationDemo.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get users by competition date
    @GetMapping("/users/byDate")
    public List<User> getUsersByCompetitionDate(@RequestParam Date date) {
        return userRepository.findUsersByCompetitionDate(date);
    }

    // Get users by competition name
    @GetMapping("/users/byName")
    public List<User> getUsersByCompetitionName(@RequestParam String competitionName) {
        return userRepository.findUsersByCompetitionName(competitionName);
    }
}
