package com.example.cv.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.cv.Candidat;
import com.example.cv.repository.CandidatRepository;

@SpringBootTest
@ActiveProfiles("test")
public class CvExportServiceTest {
    
    // @Autowired
    // private CvExportService cvExportService;

    // @Autowired
    // private CandidatRepository candidatRepository;

    // @BeforeEach
    // void setup() {
    //     candidatRepository.deleteAll();
    // }

    // @Test
    // void testExportTexDepuisCandidat() {
    //     // Given
    //     Candidat candidat = new Candidat("1234", "Maggiore", "Christophe", "Développeur Java");
    //     candidatRepository.save(candidat);

    //     // When
    //     String latex = cvExportService.exportCvCode("1234", "Développeur Java");

    //     // Then
    //     assertNotNull(latex);
    //     assertTrue(latex.contains("Christophe"));
    //     assertTrue(latex.contains("\\section")); // exemple d'une structure LaTeX
    // }
}
