package com.mariam.auth.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.mariam.auth.entities.User;
import com.mariam.auth.repository.UsersRepository;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class UserController extends HttpServlet {

    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération des paramètres du formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("mot_de_passe");
        String role = request.getParameter("role"); // ADMIN ou PERSONNEL

        // Création d’un nouvel utilisateur (correspond à la nouvelle table)
        User user = new User(nom, prenom, email, motDePasse,role);

        try {
            usersRepository.save(user);


            // Redirection selon le rôle
            if ("ADMIN".equalsIgnoreCase(role)) {
                response.sendRedirect(request.getContextPath() + "/admin-dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/scanner");
            }

        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'enregistrement de l'utilisateur", e);
        }
    }
}
