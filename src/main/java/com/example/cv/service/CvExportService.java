package com.example.cv.service;

import org.springframework.stereotype.Service;

@Service
public class CvExportService {

    private String nomFichierCV;

    public CvExportService() {
    }

    public CvExportService(String nomFichierCV) {
        this.nomFichierCV = nomFichierCV;
    }

    public String exportCv(String nomCandidat, String prenomCandidat, String nomCV) {
        System.out.println("Paramètres reçus : " + nomCandidat + ", " + prenomCandidat + ", " + nomCV);
        nomFichierCV = nomCandidat + "_" + prenomCandidat + "_" + nomCV + ".pdf";
        System.out.println("Exportation du CV pour " + prenomCandidat + " " + nomCandidat + " avec le nom de fichier : " + nomFichierCV);
        return nomFichierCV;
    }
}