package com.mariam.auth.repository;

import com.mariam.auth.entities.Categorie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieRepository {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/axiadocs", "root", "rootpassword");
    }

    // Sauvegarder une catégorie
    public void save(Categorie categorie) throws SQLException {
        String sql = "INSERT INTO Categorie (nom, description, utilisateurId) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categorie.getNom());
            stmt.setString(2, categorie.getDescription());
            stmt.setInt(3, categorie.getUtilisateurId());
            stmt.executeUpdate();
        }
    }

    // Lister toutes les catégories
    public List<Categorie> listCategories() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String sql = "SELECT * FROM Categorie";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categorie categorie = new Categorie(
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getInt("utilisateurId")
                );
                categorie.setId(rs.getInt("id"));
                categories.add(categorie);
            }
        }
        return categories;
    }

    // Trouver par ID
    public Categorie findById(int id) throws SQLException {
        String sql = "SELECT * FROM Categorie WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Categorie categorie = new Categorie(
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getInt("utilisateurId")
                );
                categorie.setId(rs.getInt("id"));
                return categorie;
            }
        }
        return null;
    }
}
