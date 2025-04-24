package com.example.cv.steps;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.cv.service.CvImportService;
import com.example.cv.dto.ImportResult;
import com.example.cv.utils.JsonCleaner;
import com.example.cv.utils.randomNames;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ImportCVfromJSONSteps {

    private final Map<String, Object> context = new HashMap<>();

    private static final String CONTEXT_FICHIER = "fichier";

    @Autowired
    private CvImportService cvImportService; 

    //private ResponseEntity<String> response;
    private String actualResponse;
    private ImportResult importResult; 
    
    String contenuOK;

    private File creerFichierTemporaire(String nomPrefixe, String contenuString) throws IOException {
        Path temp = Files.createTempFile(nomPrefixe, ".json");
        Files.writeString(temp, contenuString, StandardCharsets.UTF_8);
        return temp.toFile();
    }

    private void mettreDansContexteFichier(String nom, String contenu) throws IOException {
        context.put(CONTEXT_FICHIER, creerFichierTemporaire(nom, contenu));
    }
    

    @Before
    public void setUp() {
        contenuOK = randomNames.jsonCVAleatoire(); 
        System.out.println("➡️ Contenu JSON valide : " + contenuOK);
    }

    @Given("un fichier JSON valide")  
    public void un_fichier_json_valide() throws IOException{
        context.put(CONTEXT_FICHIER, creerFichierTemporaire("cv_valide", contenuOK));
    }

    @Given("un fichier JSON mal formé") 
    public void un_fichier_json_mal_formé() throws IOException{
        String contenu = "{ nom: '" + randomNames.chaineAleatoire(5) + "', ,,, }";
        context.put(CONTEXT_FICHIER, creerFichierTemporaire("cv_malforme", contenu));
    }

    @Given("un nom de fichier JSON incorrect")
    public void un_nom_de_fichier_json_incorrect() throws IOException{
        context.put(CONTEXT_FICHIER, creerFichierTemporaire("cv_invalide?.json", contenuOK));
    }

    @Given("aucun paramètre de nom de fichier n'est fourni")
    public void aucun_parametre_de_nom_de_fichier_n_est_fourni() throws IOException{
        context.put(CONTEXT_FICHIER, creerFichierTemporaire(null, contenuOK));
    }

    @Given("un fichier JSON vide")
    public void un_fichier_json_vide() throws IOException{
        String contenu = "";
        context.put(CONTEXT_FICHIER, creerFichierTemporaire("cv_vide", contenu));
    }

    @Given("un fichier JSON sans champ \"nom\"")
    public void un_fichier_json_sans_champ_nom() throws IOException{
        String contenu = contenuOK.replaceAll("(?m)^\\s*\"nom\"\\s*:\\s*\".*?\",?\\s*\\n?", "");
        System.out.println("➡️ Contenu JSON sans champ nom : " + contenu);
        context.put(CONTEXT_FICHIER, creerFichierTemporaire("cvSansChampNom", contenu));
    }

    @Given("un CV déjà présent dans la base")
    public void un_cv_deja_present_dans_la_base() throws IOException{
        importResult = cvImportService.importCvFromJson(creerFichierTemporaire(null, contenuOK));
        context.put(CONTEXT_FICHIER, creerFichierTemporaire(null, contenuOK));
    }

    //Scenario: Échec d'import d'un fichier au format non JSON
    @Given("un fichier XML")
    public void un_fichier_xml() throws IOException{
        String contenu = randomNames.xmlCVAleatoire();
        System.out.println("➡️ Contenu XML : " + contenu);
        context.put(CONTEXT_FICHIER, creerFichierTemporaire("cvXml", contenu));
    }

    //Scenario: Échec d'import à cause d'un dépassement de temps
    @Given("un très grand fichier JSON")
    public void un_tres_grand_fichier_json() throws IOException{
        String contenuTresGrand = randomNames.bigJsonCVAleatoire();
        context.put(CONTEXT_FICHIER, creerFichierTemporaire("cvXml", contenuTresGrand));
    }

    @Given("un fichier JSON partiellement invalide : sans champ {string}")
    public void un_fichier_json_partiellement_invalide_sans_champ(String champ) throws IOException {
        try {
            String contenu = JsonCleaner.retirerCoordonnees(contenuOK, champ);
            System.out.println("➡️ Contenu JSON sans champ nom : " + contenu);
            context.put(CONTEXT_FICHIER, creerFichierTemporaire("cvSansChamp", contenu));
        } catch (Exception e) {
            throw new IOException("Erreur lors de la suppression du champ " + champ + " dans le JSON", e);
        }
    }
    
    @When("j'importe le fichier")
    public void j_importe_le_fichier() {
        importResult = cvImportService.importCvFromJson((File) context.get(CONTEXT_FICHIER));
        actualResponse = importResult.getMessage(); // Récupérer le message de la réponse
        System.out.println("➡️ Importation du fichier : " + (String) context.get(CONTEXT_FICHIER));
        System.out.println("⬅️ Résultat de l'importation : " + importResult);     
    }

    @Then("la réponse est {string}")
    public void la_réponse_est(String expectedResponse) {
        Assertions.assertEquals(expectedResponse, actualResponse, "La réponse ne correspond pas à celle attendue.");
    }
    
}
