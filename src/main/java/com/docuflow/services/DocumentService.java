package com.docuflow.services;

import com.docuflow.dto.request.UpdateDocumentRequest;
import com.docuflow.factories.AbstractFactoryProvider;
import com.docuflow.models.*;
import com.docuflow.prototypes.PrototypeRegistry;
import com.docuflow.repositories.DocumentRepository;
import com.docuflow.exceptions.DocumentNotFoundException;
import com.docuflow.builders.DocumentBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Servicio que contiene la lógica de negocio para documentos.
 */
@Service
public class DocumentService {

    private final DocumentRepository repository;
    private final PrototypeRegistry prototypeRegistry;
    private final AbstractFactoryProvider abstractFactoryProvider;
    private final DocumentBuilder documentBuilder;

    public DocumentService(DocumentRepository repository,
                           PrototypeRegistry prototypeRegistry,
                           AbstractFactoryProvider abstractFactoryProvider,
                           DocumentBuilder documentBuilder) {
        this.repository = repository;
        this.prototypeRegistry = prototypeRegistry;
        this.abstractFactoryProvider = abstractFactoryProvider;
        this.documentBuilder = documentBuilder;
    }

    // =====================================================
    // CRUD PRINCIPAL
    // =====================================================

    public Document createDocument(String title, String author, String content, String type) {
        Objects.requireNonNull(title, "title cannot be null");
        Document doc = documentBuilder
                .withTitle(title)
                .withAuthor(author)
                .withContent(content)
                .withType(type)
                .build();
        return repository.save(doc);
    }

    public List<Document> getAllDocuments() {
        return repository.findAll();
    }

    public Document getDocumentById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
    }

    /**
     * Actualiza un documento usando un DTO (UpdateDocumentRequest).
     */
    public Document updateDocument(UpdateDocumentRequest request) {
        Objects.requireNonNull(request, "El objeto de actualización no puede ser nulo");

        Document existing = repository.findById(request.getId())
                .orElseThrow(() -> new DocumentNotFoundException(request.getId()));

        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            existing.setTitle(request.getTitle());
        }
        if (request.getAuthor() != null && !request.getAuthor().isBlank()) {
            existing.setAuthor(request.getAuthor());
        }
        if (request.getContent() != null) {
            existing.setContent(request.getContent());
        }

        repository.update(existing.getId(), existing);
        return existing;
    }

    public void deleteDocument(Long id) {
        if (!repository.existsById(id)) {
            throw new DocumentNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public List<Document> getDocumentsByType(String type) {
        return repository.findByType(type);
    }

    // =====================================================
    // FUNCIONALIDAD EXTRA
    // =====================================================

    public String exportDocument(Document document, String formatType) {
        if (document == null) throw new IllegalArgumentException("Document cannot be null");
        if (formatType == null || formatType.trim().isEmpty())
            throw new IllegalArgumentException("Format type is required");

        var factory = abstractFactoryProvider.getFactory(formatType);
        var format = factory.createFormat();
        return format.export(document);
    }

    public List<String> getAvailableTemplates() {
        return prototypeRegistry.getTemplateNames();
    }

    public List<DocumentTemplate> getAllTemplateDetails() {
        return prototypeRegistry.getAllTemplates();
    }

    public long countDocuments() {
        return repository.count();
    }

    // =====================================================
    // MÉTODOS COMPATIBLES CON CONTROLADOR
    // =====================================================

    public Document createSimpleDocument(String title) {
        Document doc = documentBuilder
                .withTitle(title)
                .withAuthor("Desconocido")
                .withContent("Documento simple generado automáticamente.")
                .withType("simple")
                .build();
        return repository.save(doc);
    }

    public Document createComplexDocument(String title, String author, String content, String type, String extraInfo) {
        Document doc = documentBuilder
                .withTitle(title)
                .withAuthor(author)
                .withContent(content + "\n" + extraInfo)
                .withType(type)
                .build();
        return repository.save(doc);
    }

    public Document createFromTemplate(String templateName, String author) {
        DocumentTemplate template = prototypeRegistry.getTemplate(templateName);
        if (template == null) {
            throw new IllegalArgumentException("Plantilla no encontrada: " + templateName);
        }
        Document document = template.clone();
        document.setAuthor(author);
        return repository.save(document);
    }
}
