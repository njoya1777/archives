package com.mariam.auth.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/admin-dashboard")
public class AdminDashboardController extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        // Configurer Thymeleaf
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/Templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Contexte pour Thymeleaf
        JakartaServletWebApplication application =
                JakartaServletWebApplication.buildApplication(getServletContext());
        var webExchange = application.buildExchange(request, response);

        WebContext ctx = new WebContext(webExchange);

        // Si tu veux ajouter la liste des documents depuis la base
        // ctx.setVariable("documents", documentRepository.findAll());

        // Rendu de la page admin-dashboard.html
        templateEngine.process("admin-dashboard", ctx, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        // 🔹 Ici tu peux faire ton traitement en base
        // documentRepository.processAction(docId, action);

        // 🔄 Redirection vers le même dashboard pour rafraîchir
        response.sendRedirect(request.getContextPath() + "/admin-dashboard");
    }
}
