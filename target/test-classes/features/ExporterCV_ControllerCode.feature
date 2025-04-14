Feature: je veux pouvoir exporter un CV existant avec un code de candidat et un nom de CV via une API

@export_cv_Controller
  Scenario: Export d'un CV existant d'un candidat
    When je fais une requête POST sur "/api/cv/exportCode" avec le code candidat :
      | codeCandidat | CM |
      | nomCV | BC |
    Then la réponse avec le code candidat est "Exportation du CV réussie."
