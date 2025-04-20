package com.example.cv.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.example.cv.service.CvImportService;
import com.example.cv.dto.ImportResult;
import com.example.cv.utils.randomNames;

import io.cucumber.java.Before;
import io.cucumber.java.be.I;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ImportCVfromJSONSteps {

    private final Map<String, Object> context = new HashMap<>();

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CvImportService cvImportService; 

    //private ResponseEntity<String> response;
    private String actualResponse;
    private ImportResult importResult; // Utilisé pour stocker le résultat de l'importation
    private String jsonPayload;

    String contenuOK;
    
    @Before
    public void setUp() {
        contenuOK = randomNames.jsonCVAleatoire(); 
        System.out.println("➡️ Contenu JSON valide : " + contenuOK);
    }

    private File creerFichierTemporaire(String nomPrefixe, String contenu) throws IOException {
        Path temp = Files.createTempFile(nomPrefixe, ".json");
        Files.writeString(temp, contenu, StandardCharsets.UTF_8);
        return temp.toFile();
    }

    @Given("un fichier JSON valide")  
    public void un_fichier_json_valide() throws IOException{
        context.put("fichier", creerFichierTemporaire("cv_valide", contenuOK));
    }

    @Given("un fichier JSON mal formé") 
    public void un_fichier_json_mal_formé() throws IOException{
        String contenu = "{ nom: '" + randomNames.chaineAleatoire(5) + "', ,,, }";
        context.put("fichier", creerFichierTemporaire("cv_malforme", contenu));
    }

    @Given("un nom de fichier JSON incorrect")
    public void un_nom_de_fichier_json_incorrect() throws IOException{
        context.put("fichier", creerFichierTemporaire("cv_invalide?.json", contenuOK));
    }

    @Given("aucun paramètre de nom de fichier n'est fourni")
    public void aucun_parametre_de_nom_de_fichier_n_est_fourni() throws IOException{
        context.put("fichier", creerFichierTemporaire(null, contenuOK));
    }

    @Given("un fichier JSON vide")
    public void un_fichier_json_vide() throws IOException{
        String contenu = "";
        context.put("fichier", creerFichierTemporaire("cv_vide", contenu));
    }

    @Given("un fichier JSON sans champ \"nom\"")
    public void un_fichier_json_sans_champ_nom() throws IOException{
        String contenu = contenuOK.replaceAll("(?m)^\\s*\"nom\"\\s*:\\s*\".*?\",?\\s*\\n?", "");
        System.out.println("➡️ Contenu JSON sans champ nom : " + contenu);
        context.put("fichier", creerFichierTemporaire("cvSansChampNom", contenu));
    }

    @Given("un CV déjà présent dans la base")
    public void un_cv_deja_present_dans_la_base() throws IOException{
        // Code pour créer ou simuler un CV déjà présent dans la base
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-deja-present.json"));
    }

    @Given("un fichier XML")
    public void un_fichier_xml() throws IOException{
        String contenu = randomNames.xmlCVAleatoire();
        System.out.println("➡️ Contenu XML : " + contenu);
        context.put("fichier", creerFichierTemporaire("cvXml", contenu));
    }

    @Given("un très grand fichier JSON")
    public void un_tres_grand_fichier_json() throws IOException{
        // Code pour créer ou simuler un très grand fichier JSON
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-tres-grand.json"));
    }

    @Given("un fichier JSON partiellement invalide")
    public void un_fichier_json_partiellement_invalide() throws IOException{
        // Code pour créer ou simuler un fichier JSON partiellement invalide
        jsonPayload = Files.readString(Paths.get("src/test/resources/jsons/cv-partiellement-invalide.json"));
    }
 
    @When("j'importe le fichier")
    public void j_importe_le_fichier() {

        File fichier = (File) context.get("fichier");
        importResult = importService.importerFichierJSON(fichier);

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
