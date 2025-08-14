package com.mariam.auth.controller;

import com.mariam.auth.entities.Document;
import com.mariam.auth.repository.DocumentRepository;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/bibliotheque")
public class BibliothequeController extends HttpServlet {

    private TemplateEngine templateEngine;
    private DocumentRepository documentRepository;

    @Override
    public void init() throws ServletException {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/Templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        documentRepository = new DocumentRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        JakartaServletWebApplication app = JakartaServletWebApplication.buildApplication(getServletContext());
        WebContext ctx = new WebContext(app.buildExchange(request, response));

        try {
            List<Document> documents = documentRepository.findAll();
            ctx.setVariable("documents", documents);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des documents", e);
        }

        templateEngine.process("bibliotheque", ctx, response.getWriter());
    }
}
