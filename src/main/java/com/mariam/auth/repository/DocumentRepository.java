package com.mariam.auth.repository;

import com.mariam.auth.entities.Document;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentRepository {

    public List<Document> findAll() throws SQLException {
        List<Document> documents = new ArrayList<>();

        String sql = "SELECT id, user_id, nom_document, type_document, chemin_fichier, qr_code, statut, date_creation " +
                     "FROM documents";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Document doc = new Document();
                doc.setId(rs.getInt("id"));
                doc.setUserId(rs.getInt("user_id"));
                doc.setNomDocument(rs.getString("nom_document"));
                doc.setTypeDocument(rs.getString("type_document"));
                doc.setCheminFichier(rs.getString("chemin_fichier"));
                doc.setQrCode(rs.getString("qr_code"));
                doc.setStatut(rs.getString("statut"));
                doc.setDateCreation(rs.getTimestamp("date_creation").toLocalDateTime());

                documents.add(doc);
            }
        }

        return documents;
    }

    public void save(Document document) throws SQLException {
        String sql = "INSERT INTO documents (user_id, nom_document, type_document, chemin_fichier, qr_code, statut) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, document.getUserId());
            stmt.setString(2, document.getNomDocument());
            stmt.setString(3, document.getTypeDocument());
            stmt.setString(4, document.getCheminFichier());
            stmt.setString(5, document.getQrCode());
            stmt.setString(6, document.getStatut());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    document.setId(generatedKeys.getInt(1));
                }
            }
        }
    }
}
