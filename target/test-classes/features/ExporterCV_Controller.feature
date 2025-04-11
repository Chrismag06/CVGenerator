Feature: je veux pouvoir exporter un CV existant avec un nom de candidat et un nom de CV via une API

@export_cv_Controller
  Scenario: Export d'un CV existant d'un candidat
    When je fais une requête POST sur "/api/cv/export" avec les paramètres :
      | nomCandidat | Maggiore   |
      | prenomCandidat | Christophe |
      | nomCV | BC          |
    Then la réponse est "Exportation du CV réussie."
