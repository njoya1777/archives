package com.mariam.auth.controller;

import com.mariam.auth.entities.Document;
import com.mariam.auth.repository.DocumentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/envoi-doc")
public class DocumentController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Récupérer les paramètres du formulaire
            String votreNom = request.getParameter("votreNom");
            String nomDocument = request.getParameter("nomDocument");
            String typeDocument = request.getParameter("typeDocument");

            // Création de l'objet Document
            Document document = new Document();
            document.setVotreNom(votreNom);
            document.setNomDocument(nomDocument);
            document.setTypeDocument(typeDocument);
            document.setDateCreation(LocalDateTime.now());

            // Sauvegarde en base
            DocumentRepository repo = new DocumentRepository();
            repo.save(document);

            // Redirection vers la bibliothèque
            response.sendRedirect(request.getContextPath() + "/bibliotheque");

        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'enregistrement du document", e);
        }
    }
}
