package com.example.cv.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;

import com.example.cv.config.TestConfig;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestConfig.class)
public class ExportCvControllerSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private String response; 

    @When("je fais une requête POST sur {string} avec les paramètres :")
    public void je_fais_une_requête_post_sur_avec_les_paramètres(String url, DataTable dataTable) {
        Map<String, String> params = dataTable.asMap(String.class, String.class);
        this.response = restTemplate.postForObject(url, params, String.class);
        System.out.println("Réponse de l'API : " + response);
    }

    @Then("la réponse est {string}")
    public void la_réponse_est(String expectedResponse) {
        // assertEquals(expectedResponse, this.response);
        assertTrue(this.response.contains(expectedResponse),"La réponse ne contient pas le message attendu. Réponse obtenue : " + this.response);

    }

}