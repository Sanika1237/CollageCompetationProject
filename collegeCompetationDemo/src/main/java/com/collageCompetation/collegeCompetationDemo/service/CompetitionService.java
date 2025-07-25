package com.collageCompetation.collegeCompetationDemo.service;

import com.collageCompetation.collegeCompetationDemo.entity.Competition;
import com.collageCompetation.collegeCompetationDemo.entity.User;
import com.collageCompetation.collegeCompetationDemo.repository.CompetitionRepository;
import com.collageCompetation.collegeCompetationDemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final UserRepository userRepository;

    public CompetitionService(CompetitionRepository competitionRepository, UserRepository userRepository) {
        this.competitionRepository = competitionRepository;
        this.userRepository = userRepository;
    }

    public Competition registerCompetition(Long userId, Competition competition) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        competition.setRegisteredBy(user);
        return competitionRepository.save(competition);
    }

    public List<Competition> getUserCompetitions(Long userId) {
        return competitionRepository.findCompetitionsByUser(userId);
    }

}
