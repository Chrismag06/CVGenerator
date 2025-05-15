package com.example.cv.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonCleaner {

    public static String retirerCoordonnees(String json, String toRemove) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode racine = mapper.readTree(json);

        System.out.println("➡️ Contenu JSON avant suppression : " + racine.toPrettyString());
        System.out.println("➡️ String to remove : " + toRemove);

        if (racine.isObject()) {
            ObjectNode objet = (ObjectNode) racine;
            objet.remove(toRemove); 
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(racine);
    }

}
