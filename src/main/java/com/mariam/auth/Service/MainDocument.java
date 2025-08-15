package com.mariam.auth.Service;

import com.mariam.auth.entities.Document;
import com.mariam.auth.repository.DocumentRepository;

public class MainDocument {
    public static void main(String[] args) {
        try {
            DocumentRepository repo = new DocumentRepository();

            // Exemple de création d'un document
            String titre = "Rapport Annuel";
            String description = "Rapport complet pour l'année 2025";
            String nomFichierOriginal = "rapport_annuel.pdf";
            String cheminFichierStocke = "/documents/rapport_annuel.pdf";
            String hashDocument = "exemplehash123456"; // Ici tu peux générer SHA-256
            String qrCodePath = "/qr/rapport_annuel.png";
            int utilisateurId = 1; // Assurez-vous qu'un utilisateur avec cet ID existe
            Integer categorieId = 1;   // Peut être null si pas de catégorie

            Document document = new Document(
                    titre,
                    description,
                    nomFichierOriginal,
                    cheminFichierStocke,
                    hashDocument,
                    qrCodePath,
                    utilisateurId,
                    categorieId
            );

            // Sauvegarde en base
            repo.save(document);

            // Liste des documents
            repo.listDocuments().forEach(d ->
                System.out.println(
                        d.getId() + " | " + d.getTitre() + " | " + d.getDescription() + " | " +
                        d.getNomFichierOriginal() + " | " + d.getCheminFichierStocke() + " | " +
                        d.getDateDepot() + " | " + d.getHashDocument() + " | " +
                        d.getQrCodePath() + " | Utilisateur ID: " + d.getUtilisateurId() +
                        " | Catégorie ID: " + d.getCategorieId()
                )
            );

            // Recherche par ID
            Document docTrouve = repo.findById(1);
            if (docTrouve != null) {
                System.out.println("Document trouvé : " + docTrouve.getTitre());
            } else {
                System.out.println("Aucun document trouvé avec cet ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
