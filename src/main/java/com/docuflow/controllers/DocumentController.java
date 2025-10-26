package com.docuflow.controllers;

import com.docuflow.dto.request.*;
import com.docuflow.dto.response.DocumentResponse;
import com.docuflow.dto.response.ExportResponse;
import com.docuflow.dto.response.TemplateResponse;
import com.docuflow.dto.mapper.DocumentDTOMapper;
import com.docuflow.models.*;
import com.docuflow.services.DocumentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para gestionar documentos
 * ✅ USA DTOs correctamente
 * ✅ Valida con @Valid
 * ✅ Retorna DocumentResponse (no entidades)
 */
@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentDTOMapper documentMapper;

    public DocumentController(DocumentService documentService, DocumentDTOMapper documentMapper) {
        this.documentService = documentService;
        this.documentMapper = documentMapper;
    }

    // ============================================================
    // 🏭 FACTORY PATTERN - Crear documento simple
    // ============================================================

    /**
     * ✅ CORRECTO: Usa @RequestBody con DTO validado
     * POST /api/documents/simple
     * Body: {"type":"INVOICE", "title":"...", "author":"...", "content":"..."}
     */
    @PostMapping("/simple")
    public ResponseEntity<DocumentResponse> createSimpleDocument(
            @Valid @RequestBody CreateSimpleDocumentRequest request) {
        
        // Factory Pattern crea según el tipo
        Document document = documentService.createSimpleDocument(
            request.getType(),
            request.getTitle(),
            request.getAuthor(),
            request.getContent()
        );
        
        DocumentResponse response = documentMapper.toDocumentResponse(document, "Factory Pattern");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ============================================================
    // 🔨 BUILDER PATTERN - Crear documento complejo
    // ============================================================

    /**
     * ✅ CORRECTO: Usa Builder para objetos complejos
     * POST /api/documents/complex
     */
    @PostMapping("/complex")
    public ResponseEntity<DocumentResponse> createComplexDocument(
            @Valid @RequestBody CreateComplexDocumentRequest request) {
        
        // Builder Pattern construye paso a paso
        ComplexDocument document = documentService.createComplexDocument(
            request.getTitle(),
            request.getAuthor(),
            request.getContent(),
            request.getHeader(),
            request.getFooter()
        );
        
        DocumentResponse response = documentMapper.toDocumentResponse(document, "Builder Pattern");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ============================================================
    // 📋 PROTOTYPE PATTERN - Crear desde plantilla
    // ============================================================

    /**
     * ✅ CORRECTO: Clona plantilla existente
     * POST /api/documents/from-template
     */
    @PostMapping("/from-template")
    public ResponseEntity<DocumentResponse> createFromTemplate(
            @Valid @RequestBody CreateFromTemplateRequest request) {
        
        // Prototype Pattern clona plantilla
        DocumentTemplate document = documentService.createFromTemplate(
            request.getTemplateName(),
            request.getAuthor(),
            request.getContent()
        );
        
        DocumentResponse response = documentMapper.toDocumentResponse(document, "Prototype Pattern");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ============================================================
    // 🏗️ ABSTRACT FACTORY PATTERN - Exportar documento
    // ============================================================

    /**
     * ✅ CORRECTO: Usa Abstract Factory para formatos
     * POST /api/documents/export
     */
    @PostMapping("/export")
    public ResponseEntity<ExportResponse> exportDocument(
            @Valid @RequestBody ExportDocumentRequest request) {
        
        // Crear documento temporal para exportar
        Document document = documentService.createSimpleDocument(
            request.getDocumentType(),
            request.getDocumentName(),
            "System",
            request.getContent()
        );
        
        // Abstract Factory exporta al formato solicitado
        String exported = documentService.exportDocument(document, request.getFormatType());
        
        ExportResponse response = new ExportResponse(
            request.getDocumentName() + "." + request.getFormatType().toLowerCase(),
            request.getFormatType(),
            "/downloads/" + document.getId()
        );
        
        return ResponseEntity.ok(response);
    }

    // ============================================================
    // 📖 CRUD - Operaciones básicas
    // ============================================================

    @GetMapping
    public ResponseEntity<List<DocumentResponse>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        
        List<DocumentResponse> responses = documents.stream()
                .map(doc -> documentMapper.toDocumentResponse(
                    doc, 
                    documentMapper.determinePatternUsed(doc)
                ))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getDocumentById(@PathVariable Long id) {
        Document document = documentService.getDocumentById(id);
        
        DocumentResponse response = documentMapper.toDocumentResponse(
            document,
            documentMapper.determinePatternUsed(document)
        );
        
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentResponse> updateDocument(
            @PathVariable Long id,
            @Valid @RequestBody UpdateDocumentRequest request) {
        
        request.setId(id);
        Document updated = documentService.updateDocument(request);
        
        DocumentResponse response = documentMapper.toDocumentResponse(
            updated,
            "Document Updated"
        );
        
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<DocumentResponse>> getDocumentsByType(@PathVariable String type) {
        List<Document> documents = documentService.getDocumentsByType(type);
        
        List<DocumentResponse> responses = documents.stream()
                .map(doc -> documentMapper.toDocumentResponse(
                    doc,
                    documentMapper.determinePatternUsed(doc)
                ))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    // ============================================================
    // 📋 TEMPLATES
    // ============================================================

    @GetMapping("/templates")
    public ResponseEntity<List<String>> getAvailableTemplates() {
        List<String> templates = documentService.getAvailableTemplates();
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/templates/details")
    public ResponseEntity<List<TemplateResponse>> getAllTemplateDetails() {
        List<DocumentTemplate> templates = documentService.getAllTemplateDetails();
        
        List<TemplateResponse> responses = templates.stream()
                .map(documentMapper::toTemplateResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    // ============================================================
    // 📊 ESTADÍSTICAS
    // ============================================================

    @GetMapping("/count")
    public ResponseEntity<Long> countDocuments() {
        long count = documentService.countDocuments();
        return ResponseEntity.ok(count);
    }
}