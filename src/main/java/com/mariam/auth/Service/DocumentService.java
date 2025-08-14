package com.mariam.auth.Service;

import com.mariam.auth.entities.Document;
import com.mariam.auth.repository.DocumentRepository;

import java.sql.SQLException;
import java.util.List;

public class DocumentService {

    private DocumentRepository repository;

    public DocumentService(DocumentRepository repository) {
        this.repository = repository;
    }

    public void ajouterDocument(Document document) throws SQLException {
        // Tu pourrais générer un QR code ici avant l'enregistrement
        repository.save(document);
    }

    public List<Document> listerDocuments() throws SQLException {
        return repository.findAll();
    }

    public Document trouverDocumentParId(int id) throws SQLException {
        return repository.findById(id);
    }
}

