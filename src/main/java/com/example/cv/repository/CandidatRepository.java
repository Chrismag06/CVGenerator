package com.example.cv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cv.Candidat;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, String> {
    
}
