package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonKeysReader {
    public static void main(String[] args) {
        try {
            // Charger le fichier JSON
            InputStream jsonStream = JsonKeysReader.class.getClassLoader().getResourceAsStream("CV_data_CM.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonStream);

            // File jsonFile = new File("CV_data_CM.json"); // Remplace par ton fichier JSON
            // ObjectMapper objectMapper = new ObjectMapper();
            // JsonNode rootNode = objectMapper.readTree(jsonFile);

            // Afficher les clés du JSON
            System.out.println("Clés du fichier JSON:");
            printKeys(rootNode, "");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printKeys(JsonNode node, String prefix) {
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                System.out.println(prefix + fieldName);
                printKeys(node.get(fieldName), prefix + fieldName + ".");
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                printKeys(node.get(i), prefix + "[" + i + "].");
            }
        }
    }
}
