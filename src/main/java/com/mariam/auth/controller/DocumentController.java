package com.mariam.auth.controller;

import com.mariam.auth.entities.Document;
import com.mariam.auth.repository.DocumentRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet("/upload-document")
@MultipartConfig
public class DocumentController extends HttpServlet {

    private DocumentRepository documentRepository;

    @Override
    public void init() throws ServletException {
        documentRepository = new DocumentRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/templates/upload-document.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        int utilisateurId = Integer.parseInt(request.getParameter("utilisateurId"));
        String categorieIdParam = request.getParameter("categorieId");
        Integer categorieId = (categorieIdParam != null && !categorieIdParam.isEmpty()) ? Integer.parseInt(categorieIdParam) : null;

        Part filePart = request.getPart("fichier");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        String hash = generateSHA256(filePath);

        Document doc = new Document(
                titre,
                description,
                fileName,
                filePath,
                hash,
                null, // QR Code path sera généré plus tard
                utilisateurId,
                categorieId
        );
        doc.setDateDepot(LocalDateTime.now());

        try {
            documentRepository.save(doc);
            response.sendRedirect(request.getContextPath() + "/documents");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'enregistrement du document", e);
        }
    }

    private String generateSHA256(String filePath) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] fileBytes = java.nio.file.Files.readAllBytes(new File(filePath).toPath());
            byte[] hashBytes = digest.digest(fileBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new IOException("Erreur lors du calcul du hash", e);
        }
    }
}
