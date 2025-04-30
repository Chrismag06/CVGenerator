Feature: Génération du JSON à partir du texte PDF

  Scenario: Transformer un texte extrait en structure JSON
    Given un texte extrait du PDF
    When je génère un JSON à partir du texte
    Then le JSON doit contenir les champs attendus
