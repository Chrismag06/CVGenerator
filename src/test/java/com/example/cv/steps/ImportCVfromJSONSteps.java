package com.example.cv.steps;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class ImportCVfromJSONSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;
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
    }

    @Then("la réponse est \"Importation du CV est réussie\" ")
    public void la_réponse_est() {
        // Code pour vérifier la réponse
    }
   
    @Then("la réponse est \"Erreur lors de l'importation du fichier JSON mal formaté\"")
    public void la_réponse_est_erreur_json_mal_formé() {
        // Code pour vérifier la réponse d'erreur
    }

    @Then("la réponse est \"Erreur lors de l'importation du fichier fichier introuvable\"")
    public void la_réponse_est_erreur_fichier_introuvable() {
        // Code pour vérifier la réponse d'erreur
    }
    
    @Then("la réponse est \"Erreur lors de l'importation du fichier - nom de fichier manquant\"")
    public void la_réponse_est_erreur_nom_fichier_manquant() {
        // Code pour vérifier la réponse d'erreur
    }

    @Then("la réponse est \"Erreur : le fichier JSON est vide ou ne contient pas de données valides\"")
    public void la_réponse_est_erreur_fichier_vide() {
        // Code pour vérifier la réponse d'erreur
    }
    
    @Then("la réponse est \"Erreur : champ 'nom' manquant dans le fichier JSON\"  ")
    public void la_réponse_est_erreur_champ_nom_manquant() {
        // Code pour vérifier la réponse d'erreur
    }
    
    @Then(" la réponse est \"Erreur : ce CV existe déjà\"")
    public void la_réponse_est_erreur_cv_existe_deja() {
        // Code pour vérifier la réponse d'erreur
    }

    @Then("la réponse est \"Erreur : format de fichier non pris en charge\"")
    public void la_réponse_est_erreur_format_fichier_non_pris_en_charge() {
        // Code pour vérifier la réponse d'erreur
    }

    @Then("la réponse est \"Erreur : délai d'import dépassé\"")
    public void la_réponse_est_erreur_delai_import_depasse() {
        // Code pour vérifier la réponse d'erreur
    }

    @Then("la réponse est \"Import partiel : certaines données ont été ignorées\"")
    public void la_réponse_est_import_partiel() {
        // Code pour vérifier la réponse d'erreur
    }


}
