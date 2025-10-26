package com.docuflow.dto.mapper;

import com.docuflow.dto.response.DocumentResponse;
import com.docuflow.dto.response.TemplateResponse;
import com.docuflow.models.*;

/**
 * Mapper para convertir entidades Document / DocumentTemplate a DTOs.
 * Compatible con todos los patrones de diseño usados en el proyecto.
 */
public class DocumentDTOMapper {

    // =========================================================
    // 🧩 CONVERSIÓN A DOCUMENT RESPONSE
    // =========================================================

    public DocumentResponse toDocumentResponse(Document document, String patternUsed) {
        if (document == null) return null;

        return new DocumentResponse(
                document.getId(),
                document.getTitle(),
                document.getAuthor(),
                document.getType(),
                document.getContent(),
                patternUsed
        );
    }

    public DocumentResponse toDocumentResponse(ComplexDocument document, String patternUsed) {
        if (document == null) return null;

        return new DocumentResponse(
                document.getId(),
                document.getTitle(),
                document.getAuthor(),
                document.getType(),
                document.getContent(),
                patternUsed
        );
    }

    public DocumentResponse toDocumentResponse(DocumentTemplate template, String patternUsed) {
        if (template == null) return null;

        return new DocumentResponse(
                template.getId(),
                template.getTitle(),
                template.getAuthor(),
                "TEMPLATE",
                template.getContent(),
                patternUsed
        );
    }

    // =========================================================
    // 🧩 CONVERSIÓN A TEMPLATE RESPONSE
    // =========================================================

    public TemplateResponse toTemplateResponse(DocumentTemplate template) {
        if (template == null) return null;

        return new TemplateResponse(
                template.getId(),
                template.getTitle(),       // nombre
                "Plantilla disponible",    // descripción
                "Prototype",               // tipo
                template.getContent()      // contenido
        );
    }

    // =========================================================
    // ⚙️ DETERMINAR PATRÓN USADO
    // =========================================================

    /**
     * Determina el patrón de diseño usado según la clase del documento.
     */
    public String determinePatternUsed(Document document) {
        if (document instanceof ComplexDocument) {
            return "Builder Pattern";
        } else if (document instanceof DocumentTemplate) {
            return "Prototype Pattern";
        } else if (document instanceof Contract ||
                   document instanceof Report ||
                   document instanceof Invoice) {
            return "Factory Pattern";
        } else {
            return "Unknown Pattern";
        }
    }
}
