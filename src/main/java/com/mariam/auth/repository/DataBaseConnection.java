package com.mariam.auth.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String URL = "jdbc:mysql://172.19.0.4:3306/axiadocs?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "rootpassword";
    private static Connection connection;

    static {
        try {
            // Charger explicitement le driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL chargé.");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement du driver MySQL : " + e.getMessage());
        }
    }

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion à la base de données réussie.");
            } catch (SQLException e) {
                System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    // Méthode optionnelle pour fermer la connexion
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion à la base de données fermée.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }
}
