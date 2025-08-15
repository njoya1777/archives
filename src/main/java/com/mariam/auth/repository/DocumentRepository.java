package com.mariam.auth.repository;

import com.mariam.auth.entities.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/axiadocs", "root", "rootpassword");
    }

    // Enregistrer un document
    public void save(Document document) throws SQLException {
        String sql = "INSERT INTO Document (titre, description, nomFichierOriginal, cheminFichierStocke, dateDepot, hashDocument, qrCodePath, utilisateurId, categorieId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, document.getTitre());
            stmt.setString(2, document.getDescription());
            stmt.setString(3, document.getNomFichierOriginal());
            stmt.setString(4, document.getCheminFichierStocke());
            stmt.setTimestamp(5, Timestamp.valueOf(document.getDateDepot()));
            stmt.setString(6, document.getHashDocument());
            stmt.setString(7, document.getQrCodePath());
            stmt.setInt(8, document.getUtilisateurId());

            if (document.getCategorieId() != null) {
                stmt.setInt(9, document.getCategorieId());
            } else {
                stmt.setNull(9, Types.INTEGER);
            }

            stmt.executeUpdate();
        }
    }

    // Récupérer tous les documents
    public List<Document> listDocuments() throws SQLException {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT * FROM Document";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Document doc = new Document(
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getString("nomFichierOriginal"),
                        rs.getString("cheminFichierStocke"),
                        rs.getString("hashDocument"),
                        rs.getString("qrCodePath"),
                        rs.getInt("utilisateurId"),
                        (rs.getObject("categorieId") != null) ? rs.getInt("categorieId") : null
                );
                doc.setId(rs.getInt("id"));
                doc.setDateDepot(rs.getTimestamp("dateDepot").toLocalDateTime());
                documents.add(doc);
            }
        }
        return documents;
    }

    // Récupérer un document par ID
    public Document findById(int id) throws SQLException {
        String sql = "SELECT * FROM Document WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Document doc = new Document(
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getString("nomFichierOriginal"),
                        rs.getString("cheminFichierStocke"),
                        rs.getString("hashDocument"),
                        rs.getString("qrCodePath"),
                        rs.getInt("utilisateurId"),
                        (rs.getObject("categorieId") != null) ? rs.getInt("categorieId") : null
                );
                doc.setId(rs.getInt("id"));
                doc.setDateDepot(rs.getTimestamp("dateDepot").toLocalDateTime());
                return doc;
            }
        }
        return null;
    }
}
