package com.mariam.auth.Service;

import com.mariam.auth.entities.Utilisateur;
import com.mariam.auth.repository.UtilisateurRepository;
import java.util.List;

public class MainUtilisateur {
    public static void main(String[] args) {
        try {
            UtilisateurRepository repo = new UtilisateurRepository();

            Utilisateur utilisateur = new Utilisateur("Mariam", "mariam@example.com", "monmotdepasse", "ADMIN");
            repo.save(utilisateur);

            System.out.println("L'email existe déjà ? " + repo.existsByEmail("mariam@example.com"));

            List<Utilisateur> utilisateurs = repo.listUsers();
            utilisateurs.forEach(u ->
                System.out.println(u.getId() + " | " + u.getNom() + " | " + u.getEmail() + " | " + u.getRole())
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
