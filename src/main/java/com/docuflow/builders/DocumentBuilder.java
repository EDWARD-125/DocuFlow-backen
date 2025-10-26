package com.docuflow.builders;

import org.springframework.stereotype.Component;
import com.docuflow.models.ComplexDocument;

/**
 * Builder externo para construir objetos ComplexDocument.
 * Aplica el patrón Builder sin depender de clases internas.
 */
@Component
public class DocumentBuilder {

    private String title;
    private String author;
    private String content;
    private String header;
    private String footer;
    private String type;

    public DocumentBuilder() {}

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

    public DocumentBuilder withHeader(String header) {
        this.header = header;
        return this;
    }

    public DocumentBuilder withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public DocumentBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Construye un objeto ComplexDocument completo.
     * @return ComplexDocument construido
     */
    public ComplexDocument build() {
        ComplexDocument doc = new ComplexDocument();
        doc.setTitle(this.title);
        doc.setAuthor(this.author);
        doc.setContent(this.content);
        doc.setHeader(this.header);
        doc.setFooter(this.footer);
        doc.setType(this.type);
        return doc;
    }
}
