package com.docuflow.builders;

import com.docuflow.models.ComplexDocument;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ✅ Builder CORREGIDO que crea objetos inmutables
 * - Construye con constructor, no con setters
 * - Valida antes de construir
 * - Fluent API para facilitar uso
 */
@Component
public class DocumentBuilder {

    private String title;
    private String author;
    private String content;
    private String type;
    private String header;
    private String footer;
    private List<String> sections;

    public DocumentBuilder() {
        this.sections = new ArrayList<>();
    }

    // Fluent API
    public DocumentBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public DocumentBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public DocumentBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public DocumentBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public DocumentBuilder withHeader(String header) {
        this.header = header;
        return this;
    }

    public DocumentBuilder withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public DocumentBuilder withSections(List<String> sections) {
        this.sections = sections != null ? new ArrayList<>(sections) : new ArrayList<>();
        return this;
    }

    public DocumentBuilder addSection(String section) {
        if (this.sections == null) {
            this.sections = new ArrayList<>();
        }
        this.sections.add(section);
        return this;
    }

    /**
     * ✅ CORRECTO: Valida y construye con constructor (inmutable)
     */
    public ComplexDocument build() {
        // Validaciones obligatorias
        if (title == null || title.isBlank()) {
            throw new IllegalStateException("Title is required for building a ComplexDocument");
        }
        
        if (content == null || content.isBlank()) {
            throw new IllegalStateException("Content is required for building a ComplexDocument");
        }
        
        // Valores por defecto
        if (author == null || author.isBlank()) {
            author = "Unknown";
        }
        
        if (type == null || type.isBlank()) {
            type = "COMPLEX";
        }
        
        // ✅ Construir con constructor (NO con setters)
        ComplexDocument document = new ComplexDocument(
            null,           // ID se asigna en repository
            this.title,
            this.author,
            this.content,
            this.type,
            this.header,
            this.footer,
            this.sections != null ? new ArrayList<>(this.sections) : new ArrayList<>()
        );
        
        // Reset builder para reutilización
        reset();
        
        return document;
    }

    /**
     * Resetea el builder para poder reutilizarlo
     */
    private void reset() {
        this.title = null;
        this.author = null;
        this.content = null;
        this.type = null;
        this.header = null;
        this.footer = null;
        this.sections = new ArrayList<>();
    }

    /**
     * Método de conveniencia para documentos estándar
     */
    public ComplexDocument buildStandard(String title, String content, String author) {
        return this.withTitle(title)
                .withContent(content)
                .withAuthor(author)
                .withType("COMPLEX")
                .withHeader("DocuFlow System")
                .withFooter("Page 1")
                .build();
    }
}
