package com.collageCompetation.collegeCompetationDemo.controller;

import com.collageCompetation.collegeCompetationDemo.dto.CompetitionResponseDTO; // Import the DTO
import com.collageCompetation.collegeCompetationDemo.entity.Competition;
import com.collageCompetation.collegeCompetationDemo.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors; // Import for stream operations

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private CompetitionService competitionService;

    // Register for a competition
    @PostMapping("/register")
    public ResponseEntity<CompetitionResponseDTO> registerCompetition(@RequestBody Competition competition) {
        Competition savedCompetition = competitionService.registerCompetition(competition);
        // Convert the entity to DTO before returning
        return ResponseEntity.ok(new CompetitionResponseDTO(savedCompetition));
    }

    // View competitions registered by all users
    @GetMapping("/competitions")
    public ResponseEntity<List<CompetitionResponseDTO>> getAllCompetitions() {
        List<Competition> competitions = competitionService.getAllCompetitions();
        // Convert list of entities to list of DTOs
        List<CompetitionResponseDTO> dtos = competitions.stream()
                .map(CompetitionResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}