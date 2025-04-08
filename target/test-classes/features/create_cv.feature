Feature: Création d'un CV

  Scenario: Ajouter un nouveau CV pour un candidat
    Given un candidat avec les informations suivantes :
      | nom       | prenom     | email                  | telephone   |
      | Maggiore  | Christophe | christophe@exemple.com | 0600000000  |
    And les compétences suivantes :
      | nom         | niveau       |
      | Java        | Avancé       |
      | Spring Boot | Intermédiaire |
    When je crée un CV intitulé "Développeur Java"
    Then le CV est enregistré avec succès
