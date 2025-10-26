package com.docuflow.factories;

import com.docuflow.models.Document;
import com.docuflow.models.*;

public class DocumentFactory {

    public Document createDocument(String type) {
        switch (type.toUpperCase()) {
            case "INVOICE":
                return new Invoice();
            case "REPORT":
                return new Report();
            case "CONTRACT":
                return new Contract();
            default:
                throw new IllegalArgumentException("Tipo de documento no soportado: " + type);
        }
    }
}
