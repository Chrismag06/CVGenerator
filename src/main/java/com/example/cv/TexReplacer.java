package com.example.cv;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class TexReplacer {
    public static void main(String[] args) throws IOException {
        // Charger le JSON
        InputStream jsonStream = TexReplacer.class.getClassLoader().getResourceAsStream("CV_data_CM.json");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = mapper.readValue(jsonStream, new TypeReference<Map<String, Object>>() {});

        // Charger le fichier .tex modèle
        InputStream texStream = TexReplacer.class.getClassLoader().getResourceAsStream("templates/CV_template_CM.tex");
        String texTemplate = new String(texStream.readAllBytes());

        // Remplacer les balises <KEY> par les valeurs du JSON
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            String placeholder = "\\VAR{" + key + "}";
            String value = String.valueOf(entry.getValue());

            System.out.println("remplacement de " + placeholder + " par " + value);
            texTemplate = texTemplate.replace(placeholder, value);
        }

        // Sauvegarder dans un nouveau fichier .tex
        String outputPath = "src/main/resources/output/CV_personnalise_CM.tex";
        Files.createDirectories(Paths.get("src/main/resources/output"));
        Files.write(Paths.get(outputPath), texTemplate.getBytes());

        System.out.println("✅ Fichier TeX généré avec succès à : " + outputPath);
    }
}
