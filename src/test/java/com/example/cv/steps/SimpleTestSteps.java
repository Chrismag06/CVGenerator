package com.example.cv.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SimpleTestSteps {

    private boolean actionEffectuee = false;

    @Given("un exemple simple")
    public void un_exemple_simple() {
        System.out.println("Étape Given exécutée");
    }

    @When("une action est effectuée")
    public void une_action_est_effectuée() {
        System.out.println("Étape When exécutée");
        actionEffectuee = true;
    }

    @Then("le résultat attendu est vérifié")
    public void le_résultat_attendu_est_vérifié() {
        System.out.println("Étape Then exécutée");
        assertTrue(actionEffectuee);
    }
}