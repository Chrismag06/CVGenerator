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

    public void exportCv(String nomCandidat, String prenomCandidat, String nomCV) {
        System.out.println("Paramètres reçus : " + nomCandidat + ", " + prenomCandidat + ", " + nomCV);
        nomFichierCV = nomCandidat + "_" + prenomCandidat + "_" + nomCV + ".pdf";
        System.out.println("Exportation du CV pour " + prenomCandidat + " " + nomCandidat + " avec le nom de fichier : " + nomFichierCV);
    }

    public void exportCvCode(String codeCandidat, String nomCV) {
        System.out.println("Paramètres reçus : " + codeCandidat + ": "  + nomCV);
        nomFichierCV = codeCandidat + "_"  + nomCV + ".pdf";
        System.out.println("Exportation du CV pour " + codeCandidat + "  avec le nom de fichier : " + nomFichierCV);
    }

}