Feature: Importer un CV à partir d'un fichier JSON

  @ImportValide
  Scenario: Import d'un CV à partir d'un fichier JSON bien formé
    Given un fichier JSON valide
    When j'importe ce fichier
    Then la réponse est "Importation du CV est réussie"

  @ErreurFormatFichier
  Scenario: Échec d'import à cause d'un fichier JSON mal formé
    Given un fichier JSON mal formé
    When j'importe ce fichier
    Then la réponse est "Erreur lors de l'importation du fichier JSON mal formaté"

  @FichierIntrouvable
  Scenario: Échec d'import à cause d'un nom de fichier incorrect
    Given un nom de fichier JSON incorrect
    When j'importe ce fichier
    Then la réponse est "Erreur lors de l'importation du fichier fichier introuvable"

  @NomFichierManquant
  Scenario: Échec d'import à cause d'un nom de fichier manquant
    Given aucun paramètre de nom de fichier n'est fourni
    When j'importe le fichier
    Then la réponse est "Erreur lors de l'importation du fichier - nom de fichier manquant"

  @FichierVide
  Scenario: Échec d'import à cause d'un fichier JSON vide
    Given un fichier JSON vide
    When j'importe ce fichier
    Then la réponse est "Erreur : le fichier JSON est vide ou ne contient pas de données valides"

  @ChampsObligatoiresManquants
  Scenario: Échec d'import à cause de champs obligatoires manquants dans le fichier JSON
    Given un fichier JSON sans champ "nom"
    When j'importe ce fichier
    Then la réponse est "Erreur : champ 'nom' manquant dans le fichier JSON"

  @DoublonCV
  Scenario: Échec d'import d'un CV déjà existant
    Given un CV déjà présent dans la base
    When j'importe un fichier JSON identique
    Then la réponse est "Erreur : ce CV existe déjà"

  @FormatInvalide
  Scenario: Échec d'import d'un fichier au format non JSON
    Given un fichier XML
    When j'importe ce fichier
    Then la réponse est "Erreur : format de fichier non pris en charge"

  @Timeout
  Scenario: Échec d'import à cause d'un dépassement de temps
    Given un très grand fichier JSON
    When j'importe ce fichier
    Then la réponse est "Erreur : délai d'import dépassé"

  @ImportPartiel
  Scenario: Import partiel d’un fichier JSON avec des sections invalides
    Given un fichier JSON partiellement invalide
    When j'importe ce fichier
    Then la réponse est "Import partiel : certaines données ont été ignorées"

