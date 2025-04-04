package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CvGenerator {
    public static void main(String[] args) {
        // Chemins des fichiers
        String jsonPath = "cv_data.json";
        String templatePath = "templates/cv_template.tex";
        String outputPath = "cv_generated.tex";

        System.out.println("Lecture du JSON depuis : " + jsonPath);
        System.out.println("Lecture du template depuis : " + templatePath);
        System.out.println("Génération du fichier : " + outputPath);

        try {
            // Charger les données du CV depuis JSON
            ObjectMapper objectMapper = new ObjectMapper();
            CvData cv = objectMapper.readValue(CvGenerator.class.getClassLoader().getResourceAsStream(jsonPath), CvData.class);
            
            // Charger le modèle LaTeX from classpath
            InputStream templateStream = CvGenerator.class.getClassLoader().getResourceAsStream(templatePath);
            if (templateStream == null) {
                throw new IOException("Could not find the " + templatePath + " in classpath");
            }

            // Charger le modèle LaTeX
            String content = new String(templateStream.readAllBytes()); 
         
            // Remplacer les variables dans le modèle
            content = content.replace("\\VAR{name}", cv.name);
            content = content.replace("\\VAR{email}", cv.email);
            content = content.replace("\\VAR{phone}", cv.phone);
            content = content.replace("\\VAR{skills}", String.join(", ", cv.skills));
            content = content.replace("\\VAR{experience}", formatListLatex(cv.experience));
            content = content.replace("\\VAR{education}", formatListLatex(cv.education));

            // Sauvegarder le fichier LaTeX généré
            Files.write(Paths.get(outputPath), content.getBytes());
            System.out.println("CV LaTeX généré : " + outputPath);

            compileLatex(outputPath);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour formater une liste en LaTeX
    public static String formatListLatex(java.util.List<String> items) {
        StringBuilder sb = new StringBuilder("\\begin{itemize}\n");
        for (String item : items) {
            sb.append("  \\item ").append(item).append("\n");
        }
        sb.append("\\end{itemize}");
        return sb.toString();
    }

    public static void compileLatex(String texFile) {
        try {
            ProcessBuilder pb = new ProcessBuilder("pdflatex", "-output-directory", "output", texFile);
            pb.inheritIO();
            Process process = pb.start();
            process.waitFor();
            System.out.println("Compilation LaTeX terminée.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    

}
