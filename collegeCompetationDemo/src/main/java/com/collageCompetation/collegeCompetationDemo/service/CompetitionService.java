package com.collageCompetation.collegeCompetationDemo.service;

import com.collageCompetation.collegeCompetationDemo.entity.Competition;
import com.collageCompetation.collegeCompetationDemo.entity.User;
import com.collageCompetation.collegeCompetationDemo.exception.ResourceNotFoundException; // Ensure this exception class exists and is correctly defined
import com.collageCompetation.collegeCompetationDemo.repository.CompetitionRepository;
import com.collageCompetation.collegeCompetationDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional; // Import Optional

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    // Get all competitions
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    // Get competitions by name
    public List<Competition> getCompetitionsByName(String name) {
        List<Competition> competitions = competitionRepository.findByName(name);
        if (competitions.isEmpty()) {
            throw new ResourceNotFoundException("No competitions found with name: " + name);
        }
        return competitions;
    }

    // Get competitions by date
    public List<Competition> getCompetitionsByDate(String date) {
        List<Competition> competitions = competitionRepository.findByDate(date);
        if (competitions.isEmpty()) {
            throw new ResourceNotFoundException("No competitions found with date: " + date);
        }
        return competitions;
    }

    @Transactional
    public Competition registerCompetition(Competition competition) {
        // --- This part is CRUCIAL for associating the correct User entity ---
        Long userId;
        if (competition.getUser() != null) {
            userId = competition.getUser().getId();
        } else {
            userId = null;
        }

        if (userId == null) {
            // Throw a custom exception or a more specific HTTP error if user ID is missing in the payload
            throw new IllegalArgumentException("User ID must be provided in the request body for competition registration.");
        }

        // Fetch the full User entity from the database using the provided ID
        // This ensures a managed entity is linked, and its full data can be retrieved.
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId)); // Use ResourceNotFoundException

        // Set the fully managed (and populated) User entity to the competition
        competition.setUser(existingUser);

        // Optional: If you manage the bidirectional relationship from the User side,
        // you might want to add the competition to the user's list as well.
        // This line assumes you have an 'addCompetition' method in your User entity
        // and a @JsonManagedReference on the 'competitions' list in User.java.
        // existingUser.addCompetition(competition);

        // Save the competition. Hibernate will handle the relationship.
        Competition savedCompetition = competitionRepository.save(competition);

        // Return the saved competition which now has the fully loaded user object.
        return savedCompetition;
    }
}