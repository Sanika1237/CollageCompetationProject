package com.collageCompetation.collegeCompetationDemo.repository;

import com.collageCompetation.collegeCompetationDemo.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    // Find competitions by name
    @Query("SELECT c FROM Competition c WHERE c.name = :name")
    List<Competition> findByName(String name);

    // Find competitions by date
    @Query("SELECT c FROM Competition c WHERE c.date = :date")
    List<Competition> findByDate(String date);
}
