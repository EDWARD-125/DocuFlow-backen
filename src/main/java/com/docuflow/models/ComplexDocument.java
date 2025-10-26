package com.docuflow.models;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un documento complejo con secciones adicionales.
 */
@Getter
@Setter
public class ComplexDocument extends Document {

    private String header;
    private String footer;
    private List<String> sections;

    // 🔹 Constructor vacío
    public ComplexDocument() {
        super();
    }

    // 🔹 Constructor con parámetros
    public ComplexDocument(Long id, String title, String author, String content, String type,
                           String header, String footer, List<String> sections) {
        super(id, title, author, content, type);
        this.header = header;
        this.footer = footer;
        this.sections = sections;
    }

    // 🔹 Implementación obligatoria del método abstracto
    @Override
    public String generateDocument() {
        StringBuilder builder = new StringBuilder();
        builder.append("=== Documento Complejo ===\n");
        builder.append("Título: ").append(getTitle()).append("\n");
        builder.append("Autor: ").append(getAuthor()).append("\n");
        builder.append("Tipo: ").append(getType()).append("\n");
        builder.append("Encabezado: ").append(header != null ? header : "N/A").append("\n");
        builder.append("Contenido: ").append(getContent()).append("\n\n");

        if (sections != null && !sections.isEmpty()) {
            builder.append("Secciones:\n");
            for (String section : sections) {
                builder.append(" - ").append(section).append("\n");
            }
        }

        builder.append("\nPie de página: ").append(footer != null ? footer : "N/A").append("\n");

        return builder.toString();
    }

    // 🔹 BUILDER para usar en DocumentService
    public static class Builder {
        private Long id;
        private String title;
        private String author;
        private String content;
        private String type;
        private String header;
        private String footer;
        private List<String> sections;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder author(String author) { this.author = author; return this; }
        public Builder content(String content) { this.content = content; return this; }
        public Builder type(String type) { this.type = type; return this; }
        public Builder header(String header) { this.header = header; return this; }
        public Builder footer(String footer) { this.footer = footer; return this; }
        public Builder sections(List<String> sections) { this.sections = sections; return this; }

        public ComplexDocument build() {
            return new ComplexDocument(id, title, author, content, type, header, footer, sections);
        }
    }
}
