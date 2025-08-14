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

@WebServlet("/scanner")
public class ScannerController extends HttpServlet {

    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
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

        JakartaServletWebApplication application =
                JakartaServletWebApplication.buildApplication(getServletContext());
        var webExchange = application.buildExchange(request, response);

        WebContext ctx = new WebContext(webExchange);
        templateEngine.process("scanner", ctx, response.getWriter());
    }


    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

  

    try {
        // 🔹 Sauvegarde du document dans la base ou système de fichiers
        // documentRepository.save(documentData);
        // Ici tu fais ton traitement réel d’enregistrement du document

        // 🔄 Redirection vers la page formulaire
        response.sendRedirect(request.getContextPath() + "/formulaire");
    } catch (Exception e) {
        throw new ServletException("Erreur lors de l'enregistrement du document", e);
    }
}
 }


