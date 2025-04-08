package com.example.cv.steps;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class ExportCvSteps {
  
    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> responseEntity;

    @Given("the user has a CV with the following details:")
    public void the_user_has_a_cv_with_the_following_details(Map<String, String> cvDetails) {
        // Here you would typically set up the CV in your system using the provided details.
        // This is just a placeholder for demonstration purposes.
        System.out.println("CV Details: " + cvDetails);
    }

}