package com.mariam.auth.repository;

import com.mariam.auth.entities.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository {

    public List<Document> findAll() throws SQLException {
        List<Document> documents = new ArrayList<>();
        String sql = "SELECT id, votre_nom, nom_document, type_document, date_creation FROM documents";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Document doc = new Document();
                doc.setId(rs.getInt("id"));
                doc.setVotreNom(rs.getString("votre_nom"));
                doc.setNomDocument(rs.getString("nom_document"));
                doc.setTypeDocument(rs.getString("type_document"));
                doc.setDateCreation(rs.getTimestamp("date_creation").toLocalDateTime());

                documents.add(doc);
            }
        }
        return documents;
    }

    public void save(Document document) throws SQLException {
        String sql = "INSERT INTO documents (votre_nom, nom_document, type_document, date_creation) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, document.getVotreNom());
            stmt.setString(2, document.getNomDocument());
            stmt.setString(3, document.getTypeDocument());
            stmt.setTimestamp(4, Timestamp.valueOf(document.getDateCreation()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    document.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public Document findById(int id) throws SQLException {
        String sql = "SELECT id, votre_nom, nom_document, type_document, date_creation FROM documents WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Document doc = new Document();
                    doc.setId(rs.getInt("id"));
                    doc.setVotreNom(rs.getString("votre_nom"));
                    doc.setNomDocument(rs.getString("nom_document"));
                    doc.setTypeDocument(rs.getString("type_document"));
                    doc.setDateCreation(rs.getTimestamp("date_creation").toLocalDateTime());
                    return doc;
                }
            }
        }
        return null;
    }
}
