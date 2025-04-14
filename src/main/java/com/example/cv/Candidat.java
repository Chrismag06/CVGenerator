package com.example.cv;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    // @OneToMany(mappedBy = "candidat", cascade = CascadeType.ALL)
    // private List<CV> cvs = new ArrayList<>();

    // getters, setters, constructeurs
}
