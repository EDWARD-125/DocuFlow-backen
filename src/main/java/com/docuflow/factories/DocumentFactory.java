package com.docuflow.factories;

import com.docuflow.models.*;
import org.springframework.stereotype.Component;

/**
 * ✅ CORRECTO: Ahora es un @Component de Spring
 */
@Component
public class DocumentFactory {

    /**
     * Factory Method - Crea documentos según el tipo
     */
    public Document createDocument(String type) {
        switch (type.toUpperCase()) {
            case "INVOICE":
                return new Invoice();
            case "REPORT":
                return new Report();
            case "CONTRACT":
                return new Contract();
            default:
                throw new IllegalArgumentException("Unknown document type: " + type);
        }
    }
}