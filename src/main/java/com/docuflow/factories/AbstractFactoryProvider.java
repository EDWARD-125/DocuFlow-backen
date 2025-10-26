// AbstractFactoryProvider.java - ABSTRACT FACTORY PATTERN
package com.docuflow.factories;

import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class AbstractFactoryProvider {
    
    private final Map<String, DocumentFormatFactory> factories;
    
    public AbstractFactoryProvider(Map<String, DocumentFormatFactory> factories) {
        this.factories = factories;
    }
    
    public DocumentFormatFactory getFactory(String formatType) {
        String factoryKey = formatType.toLowerCase() + "Factory";
        DocumentFormatFactory factory = factories.get(factoryKey);
        
        if (factory == null) {
            throw new IllegalArgumentException("Unknown format type: " + formatType);
        }
        
        return factory;
    }
}