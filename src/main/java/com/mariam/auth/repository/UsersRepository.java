package com.mariam.auth.repository;

import com.mariam.auth.entities.User;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class UsersRepository {

    // Enregistrer un utilisateur
    public void save(User user) throws SQLException {
           String insertSql = "INSERT INTO users (nom, prenom, email, mot_de_passe, role) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DataBaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(insertSql)) {

        ps.setString(1, user.getNom());
        ps.setString(2, user.getPrenom());
        ps.setString(3, user.getEmail());
        ps.setString(4, hashPassword(user.getPasswords())); // assume que tu as une méthode hashPassword()
        ps.setString(5, user.getRoles());

        ps.executeUpdate();
        System.out.println("✅ Utilisateur ajouté avec succès !");
    }
    }

    // Vérifier si un email existe déjà
    public boolean existsByEmail(String email) throws SQLException {
        String sql = "SELECT id FROM users WHERE email = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Mettre à jour un utilisateur via son email
    public boolean updateByEmail(String oldEmail, String nom, String prenom, String password, String role) throws SQLException {
        String sql = "UPDATE users SET nom=?, prenom=?, passwords=?, roles=? WHERE email=?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nom);
            ps.setString(2, prenom);
            ps.setString(3, hashPassword(password));
            ps.setString(4, role);
            ps.setString(5, oldEmail);

            return ps.executeUpdate() > 0;
        }
    }

    // Supprimer un utilisateur
    public boolean deleteByEmail(String email) throws SQLException {
        String sql = "DELETE FROM users WHERE email=?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            return ps.executeUpdate() > 0;
        }
    }

    // Lister tous les utilisateurs
    public void listUsers() throws SQLException {
        String sql = "SELECT id, nom, prenom, role, email";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.printf("%d - %s %s - %s - %s - %s%n",
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("roles"),
                        rs.getString("email"),
                        rs.getTimestamp("creer_le"));
            }
        }
    }

    // Hashage du mot de passe
    private String hashPassword(String plain) {
        // ⚠️ Remplacer par un vrai hashage BCrypt ou Argon2 en prod
        return plain.trim();
    }
}
