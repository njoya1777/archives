package com.mariam.auth.Service;

import com.mariam.auth.entities.User;
import com.mariam.auth.repository.UsersRepository;

public class MainUser {
    public static void main(String[] args) {
        try {
            UsersRepository repo = new UsersRepository();

            // Exemple de création d'utilisateur
            String nom = "Mariam";
            String prenom = "njoya";
            String email = "mariam@example.com";
            String motDePasse = "monmotdepasse";
            String role = "ADMIN";

            User user = new User(nom, prenom, email, motDePasse, role);

        repo.save(user);

            // Exemple de vérification d'email
            boolean existe = repo.existsByEmail(email);
            System.out.println("L'email existe déjà ? " + existe);

            // Liste des utilisateurs
            repo.listUsers();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
