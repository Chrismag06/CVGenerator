package com.example.cv.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cv.CvData;
import com.example.cv.dto.ImportResult;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CvImportService {
    /**
     * Imports a CV from a JSON file.
     *
     * @param jsonFile the JSON file containing the CV data
     * @return an ImportResult object indicating the success or failure of the import
     */ 

    public ImportResult importCvFromJson(File jsonFile) {
        try {
            if (jsonFile == null || !jsonFile.exists()) {
                return new ImportResult(false, "Erreur lors de l'importation du fichier - nom de fichier manquant", List.of());
            }
            if (!jsonFile.getName().endsWith(".json")) {
                return new ImportResult(false, "Erreur lors de l'importation : seule l'extension .json est autorisée", List.of());
            }
            if (jsonFile.length() == 0) {
                return new ImportResult(false, "Erreur : le fichier JSON est vide ou ne contient pas de données valides", List.of());
            }
            System.out.println("➡️ Fichier JSON : " + jsonFile.getName());
            System.out.println("➡️ Fichier JSON : " + jsonFile.toString());
            System.out.println("➡️ Contenu du fichier JSON : " + Files.readString(jsonFile.toPath()));

            ObjectMapper mapper = new ObjectMapper();
            CvData cv = mapper.readValue(jsonFile, CvData.class);
            return new ImportResult(true, "Importation du CV est réussie", Collections.emptyList());
        }catch (JsonParseException e) {
            return new ImportResult(false, "Erreur lors de l'importation : le fichier semble corrompu ou mal formé", List.of(e.getMessage()));
        }catch (InvalidPathException e) {
            return new ImportResult(false, "Nom de fichier invalide : caractères non autorisés", List.of(e.getMessage()));
        } catch (IOException e) {
            return new ImportResult(false, "Erreur de lecture du fichier", List.of(e.getMessage()));
        } catch (Exception e) {
            return new ImportResult(false, "Erreur inconnue", List.of(e.getMessage()));
        } 

    }

}
