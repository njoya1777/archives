package com.mariam.auth.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.mariam.auth.entities.Document;
import com.mariam.auth.repository.DocumentRepository;

import java.io.IOException;

@WebServlet("/envoi-doc")
public class EnvoiDocController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Récupérer l'utilisateur connecté
            int userId = Integer.parseInt(request.getParameter("userId")); // hidden input

            String nomDocument = request.getParameter("nom_document"); // doit correspondre au name du formulaire
            String typeDocument = request.getParameter("type_document");

            // Gestion du fichier uploadé
            Part fichierPart = request.getPart("fichier");
            String cheminFichier = "/chemin/serveur/uploads/" + fichierPart.getSubmittedFileName();
            fichierPart.write(cheminFichier);

            // Création de l'objet Document
            Document document = new Document();
            document.setUserId(userId);
            document.setNomDocument(nomDocument);
            document.setTypeDocument(typeDocument);
            document.setCheminFichier(cheminFichier);
            document.setQrCode(""); // à générer plus tard
            document.setStatut("EN_ATTENTE");

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


