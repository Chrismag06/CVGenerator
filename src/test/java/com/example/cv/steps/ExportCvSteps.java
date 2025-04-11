package com.example.cv.steps;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cv.service.CvExportService;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest
public class ExportCvSteps {

    private String nomCandidat;
    private String prenomCandidat;
    private String nomCV;
    private boolean cvExporteAvecSucces;

    @Autowired
    private CvExportService cvExportService;
  
    @Given("un candidat avec les informations suivantes :")
    public void un_candidat_avec_les_informations_suivantes(Map<String, String> candidatInfo) {
        nomCandidat = candidatInfo.get("nom");
        prenomCandidat = candidatInfo.get("prenom");
        System.out.println("Candidat: " + prenomCandidat + " " + nomCandidat);
    }

    @Given("le nom de CV suivant :")
    public void le_nom_de_cv_suivant(Map<String, String> cvNomInfo) {
        this.nomCV = cvNomInfo.get("nom");
        System.out.println("Nom de CV: " + nomCV);
    }

    @When("je crée un CV intitulé {string}")
    public void je_crée_un_cv_intitulé(String nomFichierCV) {
        try {
            cvExportService.exportCv(nomCandidat, prenomCandidat, nomCV);
            this.cvExporteAvecSucces = true;
        } catch (Exception e) {
            this.cvExporteAvecSucces = false;
        }
    }

    @Then("le CV est exporté avec succès")
    public void le_cv_est_exporté_avec_succès() {
        assertTrue(cvExporteAvecSucces, "L'exportation du CV a échoué.");
        System.out.println("Le CV a été exporté avec succès.");
    }

}