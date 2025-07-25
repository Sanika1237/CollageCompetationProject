package com.collageCompetation.collegeCompetationDemo.repository;

import com.collageCompetation.collegeCompetationDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    // Derived query to find user by username
    Optional<User> findByUsername(String username);

    // Custom Query for Find all users with a specific role
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findUsersByRole(User.Role role);

    // Native Query for Count users by role
    @Query(value = "SELECT COUNT(*) FROM users WHERE role = ?1", nativeQuery = true)
    int countUsersByRole(String role);

    // JPQL Query for Find users registered for competitions by date
    @Query("SELECT DISTINCT c.registeredBy FROM Competition c WHERE c.date = :date")
    List<User> findUsersByCompetitionDate(Date date);

    // JPQL Query for Find users registered for competitions by competition name
    @Query("SELECT DISTINCT c.registeredBy FROM Competition c WHERE c.competitionName = :competitionName")
    List<User> findUsersByCompetitionName(String competitionName);

    // Native Query for Find users by competition date
    @Query(value = "SELECT DISTINCT u.* FROM users u " +
            "JOIN competitions c ON u.id = c.user_id " +
            "WHERE c.date = ?1", nativeQuery = true)
    List<User> findUsersByCompetitionDateNative(Date date);

    // Native Query for Find users by competition name
    @Query(value = "SELECT DISTINCT u.* FROM users u " +
            "JOIN competitions c ON u.id = c.user_id " +
            "WHERE c.competition_name = ?1", nativeQuery = true)
    List<User> findUsersByCompetitionNameNative(String competitionName);
}
