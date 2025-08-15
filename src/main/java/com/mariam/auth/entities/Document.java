
package com.mariam.auth.entities;

import java.time.LocalDateTime;

public class Document {
    private int id;
    private String titre;
    private String description;
    private String nomFichierOriginal;
    private String cheminFichierStocke;
    private LocalDateTime dateDepot;
    private String hashDocument;
    private String qrCodePath;
    private int utilisateurId;
    private Integer categorieId; // Peut être null

    // Constructeur principal
    public Document(String titre, String description, String nomFichierOriginal, String cheminFichierStocke,
                    String hashDocument, String qrCodePath, int utilisateurId, Integer categorieId) {
        this.titre = titre;
        this.description = description;
        this.nomFichierOriginal = nomFichierOriginal;
        this.cheminFichierStocke = cheminFichierStocke;
        this.hashDocument = hashDocument;
        this.qrCodePath = qrCodePath;
        this.utilisateurId = utilisateurId;
        this.categorieId = categorieId;
        this.dateDepot = LocalDateTime.now();
    }

    // Getters
    public int getId() { return id; }
    public String getTitre() { return titre; }
    public String getDescription() { return description; }
    public String getNomFichierOriginal() { return nomFichierOriginal; }
    public String getCheminFichierStocke() { return cheminFichierStocke; }
    public LocalDateTime getDateDepot() { return dateDepot; }
    public String getHashDocument() { return hashDocument; }
    public String getQrCodePath() { return qrCodePath; }
    public int getUtilisateurId() { return utilisateurId; }
    public Integer getCategorieId() { return categorieId; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitre(String titre) { this.titre = titre; }
    public void setDescription(String description) { this.description = description; }
    public void setNomFichierOriginal(String nomFichierOriginal) { this.nomFichierOriginal = nomFichierOriginal; }
    public void setCheminFichierStocke(String cheminFichierStocke) { this.cheminFichierStocke = cheminFichierStocke; }
    public void setDateDepot(LocalDateTime dateDepot) { this.dateDepot = dateDepot; }
    public void setHashDocument(String hashDocument) { this.hashDocument = hashDocument; }
    public void setQrCodePath(String qrCodePath) { this.qrCodePath = qrCodePath; }
    public void setUtilisateurId(int utilisateurId) { this.utilisateurId = utilisateurId; }
    public void setCategorieId(Integer categorieId) { this.categorieId = categorieId; }
}
