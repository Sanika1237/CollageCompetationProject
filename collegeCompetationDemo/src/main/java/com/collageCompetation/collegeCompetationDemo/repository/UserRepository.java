package com.collageCompetation.collegeCompetationDemo.repository;

import com.collageCompetation.collegeCompetationDemo.entity.User;
import com.collageCompetation.collegeCompetationDemo.entity.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // Import Param
import org.springframework.stereotype.Repository;

import java.time.LocalDate; // Import LocalDate
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    // Find users by role (ADMIN or USER)
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findUsersByRole(@Param("role") Role role); // Use @Param for clarity

    // Native Query: Count users by role
    @Query(value = "SELECT COUNT(*) FROM users WHERE role = ?1", nativeQuery = true)
    int countUsersByRole(String role);

    // Find users by competition name
    @Query("SELECT u FROM User u JOIN u.competitions c WHERE c.name = :competitionName")
    List<User> findUsersByCompetitionName(@Param("competitionName") String competitionName);


    // Find users by competition date
    // MODIFIED: Changed parameter type from String to LocalDate
    @Query("SELECT u FROM User u JOIN u.competitions c WHERE c.date = :date")
    List<User> findUsersByCompetitionDate(@Param("date") LocalDate date); // <-- Changed parameter type to LocalDate

    // You might also need this for fetching a single user by username with competitions
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.competitions WHERE u.username = :username")
    Optional<User> findByUsernameWithCompetitions(@Param("username") String username);

    // Custom query to fetch all users and their associated competitions eagerly
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.competitions") // Added DISTINCT to avoid duplicates
    List<User> findAllWithCompetitions();

    // Standard method to find by username
    Optional<User> findByUsername(String username);

    // Method to find by email
    Optional<User> findByEmail(String email);
}