package com.mariam.auth.entities;

import java.time.LocalDateTime;

public class Document {
    private int id;
    private String votreNom;
    private String nomDocument;
    private String typeDocument;
    private LocalDateTime dateCreation;

    // Constructeur vide
    public Document() {}

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getVotreNom() { return votreNom; }
    public void setVotreNom(String votreNom) { this.votreNom = votreNom; }

    public String getNomDocument() { return nomDocument; }
    public void setNomDocument(String nomDocument) { this.nomDocument = nomDocument; }

    public String getTypeDocument() { return typeDocument; }
    public void setTypeDocument(String typeDocument) { this.typeDocument = typeDocument; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
}
