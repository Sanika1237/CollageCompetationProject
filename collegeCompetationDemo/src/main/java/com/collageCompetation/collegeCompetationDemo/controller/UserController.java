package com.collageCompetation.collegeCompetationDemo.controller;

import com.collageCompetation.collegeCompetationDemo.entity.Competition;
import com.collageCompetation.collegeCompetationDemo.service.CompetitionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CompetitionService competitionService;

    public UserController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    // Register for competition
    @PostMapping("/competition/register/{userId}")
    public Competition registerCompetition(@PathVariable Long userId, @RequestBody Competition competition) {
        return competitionService.registerCompetition(userId, competition);
    }

    // View competitions registered by user
    @GetMapping("/competition/{userId}")
    public List<Competition> getUserCompetitions(@PathVariable Long userId) {
        return competitionService.getUserCompetitions(userId);
    }
}
