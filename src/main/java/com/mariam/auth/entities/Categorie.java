package com.mariam.auth.entities;

public class Categorie {
    private int id;
    private String nom;
    private String description;
    private int utilisateurId; // ID du propriétaire

    // Constructeur principal
    public Categorie(String nom, String description, int utilisateurId) {
        this.nom = nom;
        this.description = description;
        this.utilisateurId = utilisateurId;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getDescription() { return description; }
    public int getUtilisateurId() { return utilisateurId; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setDescription(String description) { this.description = description; }
    public void setUtilisateurId(int utilisateurId) { this.utilisateurId = utilisateurId; }
}

