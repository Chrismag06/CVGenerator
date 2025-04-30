Feature: Extraction du texte d'un PDF

  Scenario: Extraire le texte d'un fichier PDF existant
    Given un fichier PDF nommé "exemple.pdf"
    When j'extrais le texte du PDF
    Then le texte extrait n'est pas vide
