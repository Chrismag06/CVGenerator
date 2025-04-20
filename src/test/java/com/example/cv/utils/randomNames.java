package com.example.cv.utils;

import java.util.Random;

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
        String json = """
        {
          "nom": "%s",
          "prenom": "%s",
          "email": "%s",
          "competences": ["%s", "%s", "%s"]
        }
        """.formatted(
                chaineAleatoire(6),
                chaineAleatoire(8),
                emailAleatoire(),
                chaineAleatoire(5),
                chaineAleatoire(5),
                chaineAleatoire(5)
        );
        return json;
    }
}
