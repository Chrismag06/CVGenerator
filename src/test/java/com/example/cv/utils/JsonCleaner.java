package com.example.cv.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonCleaner {

    public static String retirerCoordonnees(String json, String toRemove) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode racine = mapper.readTree(json);

        if (racine.isObject()) {
            ObjectNode objet = (ObjectNode) racine;
            objet.remove("coordonnees"); 
        }

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(racine);
    }

}
