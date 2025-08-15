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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private UtilisateurRepository utilisateurRepository;

    @Override
    public void init() throws ServletException {
        utilisateurRepository = new UtilisateurRepository();
    }

    // Méthode helper pour afficher un template HTML depuis resources
    private void renderTemplate(HttpServletResponse response, String templatePath) throws IOException {
        try (var inputStream = getClass().getResourceAsStream(templatePath)) {
            if (inputStream == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page non trouvée");
                return;
            }
            response.setContentType("text/html");
            inputStream.transferTo(response.getOutputStream());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        renderTemplate(response, "/Templates/register.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("mot_de_passe");

        // Validation basique
        if (nom == null || nom.isEmpty() ||
            email == null || email.isEmpty() ||
            motDePasse == null || motDePasse.isEmpty()) {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<p style='color:red;text-align:center;'>Tous les champs sont obligatoires.</p>");
            renderTemplate(response, "/Templates/dashboard.html");
            return;
        }

        try {
            if (utilisateurRepository.existsByEmail(email)) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<p style='color:red;text-align:center;'>Cet email est déjà utilisé.</p>");
                renderTemplate(response, "/Templates/dashbord.html");
                return;
            }

            // Hachage du mot de passe
            String hashedPassword = BCrypt.hashpw(motDePasse, BCrypt.gensalt());

            // Création de l'utilisateur
            Utilisateur utilisateur = new Utilisateur(nom, email, hashedPassword, "UTILISATEUR");
            utilisateurRepository.save(utilisateur);

            // Création de la session et stockage de l'utilisateur
            HttpSession session = request.getSession();
            session.setAttribute("user", utilisateur);

            // Redirection vers le dashboard directement
            response.sendRedirect(request.getContextPath() + "/dashboard");

        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'enregistrement de l'utilisateur", e);
        }
    }
}
