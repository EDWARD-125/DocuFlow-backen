package com.docuflow.services;

import com.docuflow.dto.request.UpdateDocumentRequest;
import com.docuflow.factories.DocumentFactory;
import com.docuflow.factories.AbstractFactoryProvider;
import com.docuflow.builders.DocumentBuilder;
import com.docuflow.models.*;
import com.docuflow.prototypes.PrototypeRegistry;
import com.docuflow.repositories.DocumentRepository;
import com.docuflow.exceptions.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * ✅ Servicio CORREGIDO que usa correctamente cada patrón
 */
@Service
public class DocumentService {

    private final DocumentRepository repository;
    private final DocumentFactory documentFactory;
    private final AbstractFactoryProvider abstractFactoryProvider;
    private final DocumentBuilder documentBuilder;
    private final PrototypeRegistry prototypeRegistry;
    
    // Tipos válidos de documentos
    private static final String[] VALID_TYPES = {"INVOICE", "REPORT", "CONTRACT"};

    public DocumentService(
            DocumentRepository repository,
            DocumentFactory documentFactory,
            AbstractFactoryProvider abstractFactoryProvider,
            DocumentBuilder documentBuilder,
            PrototypeRegistry prototypeRegistry) {
        this.repository = repository;
        this.documentFactory = documentFactory;
        this.abstractFactoryProvider = abstractFactoryProvider;
        this.documentBuilder = documentBuilder;
        this.prototypeRegistry = prototypeRegistry;
    }

    // =====================================================
    // 🏭 FACTORY PATTERN - Documentos simples
    // =====================================================

    /**
     * ✅ CORRECTO: Usa Factory para crear según tipo
     */
    public Document createSimpleDocument(String type, String title, String author, String content) {
        validateDocumentType(type);
        
        // Factory Pattern crea la instancia correcta
        Document document = documentFactory.createDocument(type);
        
        // Asignar propiedades
        document.setTitle(title);
        document.setAuthor(author);
        document.setContent(content);
        document.setType(type);
        
        return repository.save(document);
    }

    // =====================================================
    // 🔨 BUILDER PATTERN - Documentos complejos
    // =====================================================

    /**
     * ✅ CORRECTO: Usa Builder para objetos con muchos campos
     */
    public ComplexDocument createComplexDocument(
            String title,
            String author,
            String content,
            String header,
            String footer) {
        
        // Builder Pattern construye paso a paso
        ComplexDocument document = documentBuilder
                .withTitle(title)
                .withAuthor(author)
                .withContent(content)
                .withType("COMPLEX")
                .withHeader(header)
                .withFooter(footer)
                .build();
        
        return (ComplexDocument) repository.save(document);
    }

    // =====================================================
    // 📋 PROTOTYPE PATTERN - Desde plantilla
    // =====================================================

    /**
     * ✅ CORRECTO: Clona plantilla existente
     */
    public DocumentTemplate createFromTemplate(String templateName, String author, String content) {
        // Prototype Pattern clona la plantilla
        DocumentTemplate template = prototypeRegistry.getTemplate(templateName);
        
        if (template == null) {
            throw new DocumentNotFoundException("Template not found: " + templateName);
        }
        
        // Personalizar el clon
        template.setAuthor(author);
        template.setContent(content);
        
        return (DocumentTemplate) repository.save(template);
    }

    // =====================================================
    // 🏗️ ABSTRACT FACTORY PATTERN - Exportar
    // =====================================================

    /**
     * ✅ CORRECTO: Usa Abstract Factory para formatos
     */
    public String exportDocument(Document document, String formatType) {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }
        
        try {
            // Abstract Factory obtiene la factory correcta
            var factory = abstractFactoryProvider.getFactory(formatType);
            var format = factory.createFormat();
            
            return format.export(document);
        } catch (IllegalArgumentException e) {
            throw new ExportException(
                "Failed to export document to format: " + formatType,
                formatType,
                document.getType()
            );
        }
    }

    // =====================================================
    // CRUD BÁSICO
    // =====================================================

    public List<Document> getAllDocuments() {
        return repository.findAll();
    }

    public Document getDocumentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    public Document updateDocument(UpdateDocumentRequest request) {
        Objects.requireNonNull(request, "Request cannot be null");

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

        return repository.update(existing.getId(), existing);
    }

    public void deleteDocument(Long id) {
        if (!repository.existsById(id)) {
            throw new DocumentNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public List<Document> getDocumentsByType(String type) {
        validateDocumentType(type);
        return repository.findByType(type);
    }

    // =====================================================
    // TEMPLATES
    // =====================================================

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
    // VALIDACIONES
    // =====================================================

    private void validateDocumentType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Document type cannot be empty");
        }
        
        boolean valid = Arrays.stream(VALID_TYPES)
                .anyMatch(validType -> validType.equalsIgnoreCase(type));
        
        if (!valid) {
            throw new InvalidDocumentTypeException(type, VALID_TYPES);
        }
    }
}