package com.mariam.auth.repository;

import com.mariam.auth.entities.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurRepository {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/axiadocs";
        String user = "root";
        String password = "rootpassword";
        return DriverManager.getConnection(url, user, password);
    }

    public void save(Utilisateur utilisateur) throws SQLException {
        String sql = "INSERT INTO utilisateur(nom, email, motDePasse, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getEmail());
            stmt.setString(3, utilisateur.getMotDePasse());
            stmt.setString(4, utilisateur.getRole());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    utilisateur.setId(rs.getInt(1));
                }
            }
        }
    }

    public boolean existsByEmail(String email) throws SQLException {
        String sql = "SELECT COUNT(*) FROM utilisateur WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public List<Utilisateur> listUsers() throws SQLException {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Utilisateur u = new Utilisateur(
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        rs.getString("role")
                );
                u.setId(rs.getInt("id"));
                utilisateurs.add(u);
            }
        }
        return utilisateurs;
    }


    public Utilisateur findByEmail(String email) throws SQLException {
    String sql = "SELECT * FROM utilisateur WHERE email = ?";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, email);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Utilisateur u = new Utilisateur(
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getString("motDePasse"),
                    rs.getString("role")
                );
                u.setId(rs.getInt("id"));
                return u;
            }
        }
    }
    return null;
}

}
