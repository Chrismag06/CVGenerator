package com.example;

import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapReader {
    public static void main(String[] args) throws Exception {
        // Charge le fichier JSON depuis src/main/resources
        InputStream jsonStream = JsonMapReader.class.getClassLoader().getResourceAsStream("CV_data_CM.json");

        if (jsonStream == null) {
            System.err.println("❌ Fichier JSON introuvable !");
            return;
        }

        // Lit le JSON comme une Map
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(jsonStream, new TypeReference<Map<String, Object>>() {});

        // Affiche les clés et les valeurs
        System.out.println("🔍 Contenu du JSON :");
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            System.out.println("🗝️ Clé : " + entry.getKey());
            System.out.println("   📦 Type : " + entry.getValue().getClass().getSimpleName());
            System.out.println("   📝 Valeur : " + entry.getValue());
            System.out.println("-----");
        }
    }
}
