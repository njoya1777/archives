package com.mariam.auth.controller;

import com.mariam.auth.entities.Utilisateur;
import com.mariam.auth.repository.UtilisateurRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private UtilisateurRepository utilisateurRepository;

    @Override
    public void init() {
        utilisateurRepository = new UtilisateurRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Affiche le formulaire de login
        String resourcePath = "/templates/login.html";
        try (var inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page non trouvée");
                return;
            }
            response.setContentType("text/html");
            inputStream.transferTo(response.getOutputStream());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email").trim();
        String motDePasse = request.getParameter("mot_de_passe").trim();

        try {
            Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

            if (utilisateur == null || !BCrypt.checkpw(motDePasse, utilisateur.getMotDePasse())) {
                // Email non trouvé ou mot de passe incorrect
                request.setAttribute("error", "Email ou mot de passe incorrect");
                request.getRequestDispatcher("/templates/login.html").forward(request, response);
                return;
            }

            // Créer la session et stocker l'utilisateur
            HttpSession session = request.getSession();
            session.setAttribute("user", utilisateur);

            // Redirection vers le tableau de bord
            response.sendRedirect(request.getContextPath() + "/dashboard");

        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la connexion", e);
        }
    }
}

