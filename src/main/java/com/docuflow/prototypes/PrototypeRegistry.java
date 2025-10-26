package com.docuflow.prototypes;

import com.docuflow.models.DocumentTemplate;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Registro de prototipos (Prototype Pattern)
 */
@Component
public class PrototypeRegistry {

    private final Map<String, DocumentTemplate> prototypes = new HashMap<>();

    @PostConstruct
    public void initializePrototypes() {
        prototypes.put("reporteBasico",
                new DocumentTemplate("Reporte Básico", "Plantilla de reporte estándar", "REPORT"));
        prototypes.put("contratoLaboral",
                new DocumentTemplate("Contrato Laboral", "Plantilla estándar de contrato laboral", "CONTRACT"));
        prototypes.put("facturaVenta",
                new DocumentTemplate("Factura de Venta", "Plantilla básica de factura", "INVOICE"));
    }

    // 🔹 Método original
    public DocumentTemplate getPrototype(String key) {
        DocumentTemplate prototype = prototypes.get(key);
        if (prototype != null) {
            return prototype.clone();
        }
        return null;
    }

    // 🔹 Método compatible con DocumentService (alias)
    public DocumentTemplate getTemplate(String name) {
        return getPrototype(name);
    }

    // 🔹 Método para obtener todas las plantillas (usado por DocumentService)
    public List<DocumentTemplate> getAllTemplates() {
        return new ArrayList<>(prototypes.values());
    }

    // 🔹 También podrías agregar uno para solo los nombres
    public List<String> getTemplateNames() {
        return new ArrayList<>(prototypes.keySet());
    }
}
