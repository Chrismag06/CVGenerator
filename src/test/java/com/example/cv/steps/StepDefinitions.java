package com.example.cv.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinitions {

    @Given("un modèle de CV existe")
    public void un_modele_de_cv_existe() {
        System.out.println("Modèle CV prêt");
    }

    @Then("le CV est généré")
    public void le_cv_est_genere() {
        System.out.println("CV généré !");
    }
}
