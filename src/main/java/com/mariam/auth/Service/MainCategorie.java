
package com.mariam.auth.Service;

import com.mariam.auth.entities.Categorie;
import com.mariam.auth.repository.CategorieRepository;

public class MainCategorie {
    public static void main(String[] args) {
        try {
            CategorieRepository repo = new CategorieRepository();

            // Exemple de création d'une catégorie
            String nom = "Informatique";
            String description = "Catégorie pour documents liés à l'informatique";
            int utilisateurId = 1; // Assurez-vous qu'un utilisateur avec cet ID existe

            Categorie categorie = new Categorie(nom, description, utilisateurId);
            repo.save(categorie);

            // Liste des catégories
            repo.listCategories().forEach(c ->
                System.out.println(c.getId() + " | " + c.getNom() + " | " + c.getDescription() + " | Utilisateur ID: " + c.getUtilisateurId())
            );

            // Recherche par ID
            Categorie catTrouvee = repo.findById(1);
            if (catTrouvee != null) {
                System.out.println("Catégorie trouvée : " + catTrouvee.getNom());
            } else {
                System.out.println("Aucune catégorie trouvée avec cet ID.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
