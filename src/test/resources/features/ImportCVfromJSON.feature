Feature: Je veux pouvoir importer un CV à partir d'un JSON


@ImportValide
    Scenario: Import d'un CV à partir d'un fichier JSON
        Given fichier JSON
        When import du fichier JSON
        then la réponse est "Importation du CV est réussie"

@Erreur du formatage du fichier
    Scenario: Import d'un CV à partir d'un CV mal formaté
        Given fichier JSON mal formaté
        When import du fichier JSON
        then la réponse est "Erreur lors de l'Importation du fichier JSON mal formaté"

@Erreur fichier introuvable
    Scenario: Import d'un CV echoué car le nom de fichier JSON est incorrect 
        Given nom incorrect de fichier JSON
        When Import du fichier JSON
        then la réponse est "Erreur lors de l'Importation du fichier fichier introuvable"

@ErreurNomduFichierManquant
    Scenario: Import d'un CV échoué car le nom de fichier JSON est manquant
        Given pas de paramètres de nom de fichier
        When Import du fichier JSON
        then la réponse est "Erreur lors de l'Importation du fichier - nom de fichier manquant"


