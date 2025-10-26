package com.docuflow.controllers;

import com.docuflow.models.Document;
import com.docuflow.models.DocumentTemplate;
import com.docuflow.services.DocumentService;
import com.docuflow.dto.request.UpdateDocumentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar las operaciones de documentos.
 * Demuestra el uso de patrones de diseño (Factory, Builder, Prototype, Abstract Factory)
 * de forma unificada sobre la clase base Document.
 * 
 * @author DocuFlow
 */
@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "*")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    // ============================================================
    // CREACIÓN DE DOCUMENTOS (Factory / Builder / Prototype)
    // ============================================================

    /**
     * Crea un documento simple usando el patrón Factory.
     */
    @PostMapping("/simple")
    public ResponseEntity<Document> createSimpleDocument(@RequestParam String title) {
        Document doc = documentService.createSimpleDocument(title);
        return new ResponseEntity<>(doc, HttpStatus.CREATED);
    }

    /**
     * Crea un documento complejo usando el patrón Builder.
     */
    @PostMapping("/complex")
    public ResponseEntity<Document> createComplexDocument(
            @RequestParam String title,
            @RequestParam String author,
            @RequestParam String content,
            @RequestParam String type,
            @RequestParam(required = false, defaultValue = "") String extraInfo) {

        Document doc = documentService.createComplexDocument(title, author, content, type, extraInfo);
        return new ResponseEntity<>(doc, HttpStatus.CREATED);
    }

    /**
     * Crea un documento desde una plantilla (Prototype Pattern).
     */
    @PostMapping("/from-template")
    public ResponseEntity<Document> createFromTemplate(
            @RequestParam String templateName,
            @RequestParam String author) {

        Document doc = documentService.createFromTemplate(templateName, author);
        return new ResponseEntity<>(doc, HttpStatus.CREATED);
    }

    // ============================================================
    // EXPORTAR DOCUMENTOS (Abstract Factory)
    // ============================================================

    /**
     * Exporta un documento a diferentes formatos (PDF, DOCX, HTML).
     */
    @PostMapping("/{id}/export")
    public ResponseEntity<String> exportDocument(
            @PathVariable Long id,
            @RequestParam String formatType) {

        Document doc = documentService.getDocumentById(id);
        String exported = documentService.exportDocument(doc, formatType);
        return ResponseEntity.ok(exported);
    }

    // ============================================================
    // CONSULTAS GENERALES
    // ============================================================

    /**
     * Obtiene todos los documentos almacenados.
     */
    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> docs = documentService.getAllDocuments();
        return ResponseEntity.ok(docs);
    }

    /**
     * Obtiene un documento por su ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        Document doc = documentService.getDocumentById(id);
        return ResponseEntity.ok(doc);
    }

    /**
     * Obtiene documentos filtrados por tipo (ej: INVOICE, REPORT, CONTRACT).
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Document>> getDocumentsByType(@PathVariable String type) {
        List<Document> docs = documentService.getDocumentsByType(type);
        return ResponseEntity.ok(docs);
    }

    // ============================================================
    // ACTUALIZACIÓN Y ELIMINACIÓN
    // ============================================================

    /**
     * Actualiza un documento usando un DTO (UpdateDocumentRequest).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(
            @PathVariable Long id,
            @RequestBody UpdateDocumentRequest request) {

        // Asignamos el ID de la ruta al DTO para que el servicio lo use correctamente
        request.setId(id);

        Document updated = documentService.updateDocument(request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Elimina un documento por su ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    // ============================================================
    // TEMPLATES DISPONIBLES
    // ============================================================

    /**
     * Lista los nombres de las plantillas disponibles.
     */
    @GetMapping("/templates")
    public ResponseEntity<List<String>> getAvailableTemplates() {
        List<String> templates = documentService.getAvailableTemplates();
        return ResponseEntity.ok(templates);
    }

    /**
     * Obtiene el detalle de todas las plantillas disponibles.
     */
    @GetMapping("/templates/details")
    public ResponseEntity<List<DocumentTemplate>> getAllTemplateDetails() {
        List<DocumentTemplate> templates = documentService.getAllTemplateDetails();
        return ResponseEntity.ok(templates);
    }

    // ============================================================
    // ESTADÍSTICAS / KPI
    // ============================================================

    /**
     * Devuelve el número total de documentos creados.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countDocuments() {
        long count = documentService.countDocuments();
        return ResponseEntity.ok(count);
    }
}
