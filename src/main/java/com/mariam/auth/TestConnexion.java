package com.mariam.auth;

import com.mariam.auth.repository.DataBaseConnection;
import java.sql.Connection;

public class TestConnexion {
    public static void main(String[] args) {
        try {
            Connection conn = DataBaseConnection.getConnection();
            if (conn != null) {
                System.out.println("✅ Test réussi : connexion établie !");
                DataBaseConnection.closeConnection();
            }
        } catch (Exception e) {
            System.err.println("❌ Erreur : " + e.getMessage());
        }
    }
}

