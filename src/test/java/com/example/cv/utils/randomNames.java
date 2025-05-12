package com.example.cv.utils;

import java.util.List;
import java.util.Random;

import com.example.cv.CvData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class randomNames {

    private static final Random random = new Random();

    public static String chaineAleatoire(int longueur) {
        String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longueur; i++) {
            sb.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return sb.toString();
    }

    public static String emailAleatoire() {
        return chaineAleatoire(6).toLowerCase() + "@" + chaineAleatoire(4).toLowerCase() + ".com";
    }

    public static String jsonCVAleatoire() {
        CvData cv = new CvData();
        cv.name = chaineAleatoire(6);
        cv.email = emailAleatoire();
        cv.phone = "06" + (10000000 + random.nextInt(89999999));
        cv.skills = List.of(chaineAleatoire(5), chaineAleatoire(5), chaineAleatoire(5));
        cv.experience = List.of("Exp " + chaineAleatoire(4), "Exp " + chaineAleatoire(4));
        cv.education = List.of("Diplôme " + chaineAleatoire(4));

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cv);
        } catch (Exception e) {
            throw new RuntimeException("Erreur de génération du JSON", e);
        }
    }

    public static String bigJsonCVAleatoire() {
        String json;

        json = "[";
        for (int i = 0; i < 10_000; i++) {
            json += "{\"nom\":\"Nom" + i + "\", \"email\":\"email" + i + "@test.com\"}";
            if (i < 9999) json += ",";
        }
        json += "]";

        return json;
    }

    public static String xmlCVAleatoire() {
        String valString1 = chaineAleatoire(5);
        String valString2 = chaineAleatoire(5);
        String valString3 = chaineAleatoire(5);
        String valString4 = chaineAleatoire(5);
        String valString5 = chaineAleatoire(5);
        String xml = """
            <?xml version="1.0" encoding="UTF-8"?>
            <%s>
                <%s>%s</%s>
                <%s>%s</%s>
            </%s>
        """.formatted(
                valString1,
                valString2, valString3, valString2,
                valString4, valString5, valString4,
                valString1
        );
        return xml;
    }
}
