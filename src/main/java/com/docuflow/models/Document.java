package com.docuflow.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase base abstracta para todos los tipos de documentos.
 */
@Getter
@Setter
public abstract class Document {

    private Long id;
    private String title;
    private String author;
    private String content;
    private String header;
    private String footer;
    private String type;

    // 🔹 Constructores
    public Document() {}

    public Document(Long id, String title, String author, String content, String type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.type = type;
    }

    // 🔹 Método abstracto (debe implementarse en subclases)
    public abstract String generateDocument();

    // 🔹 Método opcional para mostrar información del documento
    @Override
    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", título='" + title + '\'' +
                ", autor='" + author + '\'' +
                ", tipo='" + type + '\'' +
                '}';
    }
}
