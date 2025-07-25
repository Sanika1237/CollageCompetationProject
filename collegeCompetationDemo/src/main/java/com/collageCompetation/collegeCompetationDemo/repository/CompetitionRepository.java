package com.collageCompetation.collegeCompetationDemo.repository;

import com.collageCompetation.collegeCompetationDemo.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition,Long> {

    // Find competitions by user ID (JPQL)
    @Query("SELECT c FROM Competition c WHERE c.registeredBy.id = :userId")
    List<Competition> findCompetitionsByUser(Long userId);

    // Find competitions by competition name (JPQL)
    @Query("SELECT c FROM Competition c WHERE c.competitionName = :competitionName")
    List<Competition> findCompetitionsByName(String competitionName);

    // Find competitions by date (JPQL)
    @Query("SELECT c FROM Competition c WHERE c.date = :date")
    List<Competition> findCompetitionsByDate(Date date);

    // Find competitions by both name and date (JPQL)
    @Query("SELECT c FROM Competition c WHERE c.competitionName = :competitionName AND c.date = :date")
    List<Competition> findCompetitionsByNameAndDate(String competitionName, Date date);

    // Competitions by user ID(Native query)
    @Query(value = "SELECT * FROM competitions WHERE user_id = ?1", nativeQuery = true)
    List<Competition> findCompetitionsByUserNative(Long userId);

    //Competitions by competition name(Native query)
    @Query(value = "SELECT * FROM competitions WHERE competition_name = ?1", nativeQuery = true)
    List<Competition> findCompetitionsByNameNative(String competitionName);

    //Competitions by date(Native query)
    @Query(value = "SELECT * FROM competitions WHERE date = ?1", nativeQuery = true)
    List<Competition> findCompetitionsByDateNative(Date date);

    //Competitions by name and date(Native query)
    @Query(value = "SELECT * FROM competitions WHERE competition_name = ?1 AND date = ?2", nativeQuery = true)
    List<Competition> findCompetitionsByNameAndDateNative(String competitionName, Date date);
}
