package com.mariam.auth.entities;

import java.time.LocalDateTime;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String passwords;
    private String roles;

    // Constructeur principal
    public User(String nom, String prenom, String email, String motDePasse, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.passwords = motDePasse;
        this.roles = role;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getPasswords() { return passwords; }
    public String getRoles() { return roles; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswords(String passwords) { this.passwords = passwords; }
    public void setRoles(String roles) { this.roles = roles; }
}
