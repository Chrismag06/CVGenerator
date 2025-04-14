package com.example.cv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cv.service.CvExportService;

import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/api/cv")
public class CvExportController {
    
    @Autowired
    CvExportService cvExportService;

    @PostConstruct
    public void init() {
        System.out.println("✅ ExportCvController est bien chargé !");
    }

    @PostMapping("/export")
    public ResponseEntity<String> ExportCV(@RequestParam String nomCandidat, @RequestParam String prenomCandidat,@RequestParam  String nomCV) {
        String nomFichierCV = null;
        if (nomCandidat == null || prenomCandidat == null || nomCV == null) {
            return new ResponseEntity<>("Tous les paramètres doivent être fournis.", HttpStatus.BAD_REQUEST);
        }
        try {
            System.out.println("nomCandidat:" + nomCandidat);
            System.out.println("prenomCandidat:" + prenomCandidat);
            System.out.println("nomCV:" + nomCV);
            cvExportService.exportCv(nomCandidat, prenomCandidat, nomCV);
            System.out.println("nomFichierCV:" + nomFichierCV);
            return new ResponseEntity<>("Exportation du CV réussie.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de l'exportation du CV : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/exportCode")
    public ResponseEntity<String> ExportCVCode(@RequestParam String codeCandidat, @RequestParam  String nomCV) {
        String nomFichierCV = null;
        if (codeCandidat == null || nomCV == null) {
            return new ResponseEntity<>("Tous les paramètres doivent être fournis.", HttpStatus.BAD_REQUEST);
        }
        try {
            System.out.println("codeCandidat:" + codeCandidat);
            System.out.println("nomCV:" + nomCV);
            cvExportService.exportCvCode(codeCandidat, nomCV);;
            System.out.println("nomFichierCV:" + nomFichierCV);
            return new ResponseEntity<>("Exportation du CV réussie.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de l'exportation du CV : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
}
