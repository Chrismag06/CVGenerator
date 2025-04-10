package com.example.cv;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@SpringBootTest
@ActiveProfiles("test")
@IncludeEngines("cucumber")
@SelectClasspathResource("features") // dossier où sont tes .feature dans test/resources
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.example.cv.steps") // le package de tes classes StepDefs
public class CucumberTest {
}
    