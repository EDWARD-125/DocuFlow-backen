package com.docuflow.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Representa una plantilla de documento base que puede clonarse.
 * Usada por el patrón Prototype.
 */
@Getter
@Setter
public class DocumentTemplate extends Document implements Cloneable {

    private String description;

    // 🔹 Constructor vacío
    public DocumentTemplate() {
        super();
    }

    // 🔹 Constructor completo (heredado de Document + description)
    public DocumentTemplate(Long id, String title, String author, String content, String type, String description) {
        super(id, title, author, content, type);
        this.description = description;
    }

    // 🔹 Constructor auxiliar (usado por PrototypeRegistry)
    public DocumentTemplate(String title, String description, String type) {
        super(null, title, null, null, type);
        this.description = description;
    }

    // 🔹 Clonación (Patrón Prototype)
    @Override
    public DocumentTemplate clone() {
        try {
            return (DocumentTemplate) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Error al clonar la plantilla de documento", e);
        }
    }

    // 🔹 Generación del documento
    @Override
    public String generateDocument() {
        return "Documento generado desde la plantilla: " + getTitle() + "\nContenido: " + getContent();
    }
}
