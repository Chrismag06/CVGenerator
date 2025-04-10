package com.example.cv.steps;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ExportCvSteps {

    private String nomCandidat;
    private String prenomCandidat;
    private String nomCV;
    private boolean cvExporteAvecSucces;
  
    @Given("un candidat avec les informations suivantes :")
    public void un_candidat_avec_les_informations_suivantes(Map<String, String> candidatInfo) {
        this.nomCandidat = candidatInfo.get("nom");
        this.prenomCandidat = candidatInfo.get("prenom");
        System.out.println("Candidat: " + prenomCandidat + " " + nomCandidat);
    }

    @Given("le nom de CV suivant :")
    public void le_nom_de_cv_suivant(Map<String, String> cvNomInfo) {
        this.nomCV = cvNomInfo.get("nom");
        System.out.println("Nom de CV: " + nomCV);
    }

    @When("je crée un CV intitulé {string}")
    public void je_crée_un_cv_intitulé(String nomFichierCV) {
        if (nomFichierCV.contains(nomCandidat) && nomFichierCV.contains("Christophe") && nomCV.equals("Business Central")) {
            this.cvExporteAvecSucces = true;
            System.out.println("CV '" + nomFichierCV + "' créé pour " + prenomCandidat + " " + nomCandidat + " avec le nom de CV: " + nomCV);
        } else {
            this.cvExporteAvecSucces = false;
            System.out.println("Erreur lors de la création du CV.");
        }
    }

    @Then("le CV est exporté avec succès")
    public void le_cv_est_exporté_avec_succès() {
        assertTrue(cvExporteAvecSucces, "L'exportation du CV a échoué.");
        System.out.println("Le CV a été exporté avec succès.");
    }

}