@ignore
Feature: Validation de la structure du JSON

  Scenario: Vérifier que le JSON généré respecte la structure attendue
    Given un fichier JSON généré
    When je valide le fichier JSON contre le modèle attendu
    Then la validation doit être réussie
