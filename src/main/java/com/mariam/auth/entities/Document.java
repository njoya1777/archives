package com.mariam.auth.entities;

import java.time.LocalDateTime;

public class Document {
    private int id;
    private int userId;
    private String nomDocument;
    private String typeDocument;
    private String cheminFichier;
    private String qrCode;
    private String statut;
    private LocalDateTime dateCreation;

    // Constructeur vide (obligatoire pour pouvoir faire new Document())
    public Document() {}

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getNomDocument() { return nomDocument; }
    public void setNomDocument(String nomDocument) { this.nomDocument = nomDocument; }

    public String getTypeDocument() { return typeDocument; }
    public void setTypeDocument(String typeDocument) { this.typeDocument = typeDocument; }

    public String getCheminFichier() { return cheminFichier; }
    public void setCheminFichier(String cheminFichier) { this.cheminFichier = cheminFichier; }

    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
}
