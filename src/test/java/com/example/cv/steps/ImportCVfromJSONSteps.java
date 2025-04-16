package com.example.cv.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.example.cv.service.CvImportService;
import com.example.cv.dto.ImportResult;

import io.cucumber.java.be.I;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ImportCVfromJSONSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CvImportService cvImportService; 

    //private ResponseEntity<String> response;
    private String actualResponse;
    private ImportResult importResult; // Utilisé pour stocker le résultat de l'importation
    private String jsonPayload;

    @Given("un fichier JSON valide")
    public void un_fichier_json_valide() throws Exception {
        // Code pour créer ou simuler un fichier JSON valide
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-valide.json"));
    }

    @Given("un fichier JSON mal formé") 
    public void un_fichier_json_mal_formé() throws Exception {
        // Code pour créer ou simuler un fichier JSON mal formé
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-mal-forme.json"));
    }

    @Given("un nom de fichier JSON incorrect")
    public void un_nom_de_fichier_json_incorrect() throws Exception {
        // Code pour créer ou simuler un nom de fichier JSON incorrect
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-invalide.json"));
    }

    @Given("aucun paramètre de nom de fichier n'est fourni")
    public void aucun_parametre_de_nom_de_fichier_n_est_fourni() throws Exception {
        // Code pour simuler l'absence de paramètre de nom de fichier
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-sans-nom.json"));
    }

    @Given("un fichier JSON vide")
    public void un_fichier_json_vide() throws Exception {
        // Code pour créer ou simuler un fichier JSON vide
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-vide.json"));
    }

    @Given("un fichier JSON sans champ \"nom\"")
    public void un_fichier_json_sans_champ_nom() throws Exception {
        // Code pour créer ou simuler un fichier JSON sans champ "nom"
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-sans-nom.json"));
    }

    @Given("un CV déjà présent dans la base")
    public void un_cv_deja_present_dans_la_base() throws Exception {
        // Code pour créer ou simuler un CV déjà présent dans la base
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-deja-present.json"));
    }

    @Given("un fichier XML")
    public void un_fichier_xml() throws Exception {
        // Code pour créer ou simuler un fichier XML
        jsonPayload = Files.readString(Paths.get("src/test/resources/xmls/cv.xml"));
    }

    @Given("un très grand fichier JSON")
    public void un_tres_grand_fichier_json() throws Exception {
        // Code pour créer ou simuler un très grand fichier JSON
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-tres-grand.json"));
    }

    @Given("un fichier JSON partiellement invalide")
    public void un_fichier_json_partiellement_invalide() throws Exception {
        // Code pour créer ou simuler un fichier JSON partiellement invalide
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-partiellement-invalide.json"));
    }
 
    @When("j'importe le fichier")
    public void j_importe_le_fichier() {
        // Code pour importer le fichier JSON
        importResult = cvImportService.importCvFromJson(jsonPayload);
        actualResponse = importResult.getMessage(); // Récupérer le message de la réponse
        System.out.println("➡️ Importation du fichier : " + jsonPayload);
        System.out.println("⬅️ Résultat de l'importation : " + importResult);     

    }

    @Then("la réponse est {string}")
    public void la_réponse_est(String expectedResponse) {
        Assertions.assertEquals(expectedResponse, actualResponse, "La réponse ne correspond pas à celle attendue.");
    }
    
}
