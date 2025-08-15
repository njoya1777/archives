package com.mariam.auth.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {

    // Méthode helper pour afficher un template HTML depuis /resources
    private void renderTemplate(HttpServletResponse response, String templatePath) throws IOException {
        try (var inputStream = getClass().getResourceAsStream(templatePath)) {
            if (inputStream == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page non trouvée");
                return;
            }
            response.setContentType("text/html");
            inputStream.transferTo(response.getOutputStream());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


      

        // Ici, si besoin, tu peux passer des infos utilisateur à la page
        // Mais comme on ne fait pas encore de moteur de template, elles devront être codées dans le HTML
        renderTemplate(response, "/Templates/dashboard.html");
    }
}
