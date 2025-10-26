package com.docuflow.dto.mapper;

import com.docuflow.dto.response.DocumentResponse;
import com.docuflow.dto.response.TemplateResponse;
import com.docuflow.models.*;
import org.springframework.stereotype.Component;

/**
 * ✅ CORRECTO: Ahora es un @Component
 */
@Component
public class DocumentDTOMapper {

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
                document.generateDocument(), // Contenido generado
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

    public TemplateResponse toTemplateResponse(DocumentTemplate template) {
        if (template == null) return null;

        return new TemplateResponse(
                template.getId(),
                template.getTitle(),
                template.getDescription(),
                template.getType(),
                template.getContent()
        );
    }

    public String determinePatternUsed(Document document) {
        if (document instanceof DocumentTemplate) {
            return "Prototype Pattern";
        } else if (document instanceof ComplexDocument) {
            return "Builder Pattern";
        } else if (document instanceof Contract ||
                   document instanceof Report ||
                   document instanceof Invoice) {
            return "Factory Pattern";
        }
        return "Unknown Pattern";
    }
}
