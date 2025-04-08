fonctionalité: je veux pouvoir exporter un CV existant avec un nom de candidat et un nom de CV

  Scenario: Export d'un CV existant d'un candidat
    Given un candidat avec les informations suivantes :
      | nom       | prenom     
      | Maggiore  | Christophe 
    And le suivant :
      | nom         | Business Central      |
    When je crée un CV intitulé "CV - DEV - Christophe Maggiore - 20250320"
    Then le CV est exporté avec succès
