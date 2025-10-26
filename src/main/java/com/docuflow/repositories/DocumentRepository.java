package com.docuflow.repositories;

import com.docuflow.models.Document;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositorio en memoria para almacenar documentos
 * Simula una base de datos sin necesidad de JPA/Hibernate
 * 
 * En producción, esto sería una interface que extiende JpaRepository:
 * public interface DocumentRepository extends JpaRepository<Document, Long> { }
 */
@Repository
public class DocumentRepository {
    
    // Almacenamiento en memoria thread-safe
    private final Map<Long, Document> storage = new ConcurrentHashMap<>();
    
    // Generador de IDs
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * Guarda un documento y retorna el documento con ID asignado
     */
    public Document save(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null");
        }
        
        Long id = idGenerator.getAndIncrement();
        storage.put(id, document);
        
        return document;
    }
    
    /**
     * Encuentra un documento por ID
     */
    public Optional<Document> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        
        return Optional.ofNullable(storage.get(id));
    }
    
    /**
     * Encuentra todos los documentos
     */
    public List<Document> findAll() {
        return new ArrayList<>(storage.values());
    }
    
    /**
     * Encuentra documentos por tipo
     */
    public List<Document> findByType(String type) {
        List<Document> result = new ArrayList<>();
        
        for (Document doc : storage.values()) {
            if (doc.getType().equalsIgnoreCase(type)) {
                result.add(doc);
            }
        }
        
        return result;
    }
    
    /**
     * Elimina un documento por ID
     */
    public void deleteById(Long id) {
        if (id != null) {
            storage.remove(id);
        }
    }
    
    /**
     * Verifica si existe un documento con el ID dado
     */
    public boolean existsById(Long id) {
        return id != null && storage.containsKey(id);
    }
    
    /**
     * Cuenta el total de documentos
     */
    public long count() {
        return storage.size();
    }
    
    /**
     * Elimina todos los documentos
     */
    public void deleteAll() {
        storage.clear();
    }
    
    /**
     * Actualiza un documento existente
     */
    public Document update(Long id, Document document) {
        if (id == null || document == null) {
            throw new IllegalArgumentException("ID and document cannot be null");
        }
        
        if (!existsById(id)) {
            throw new IllegalArgumentException("Document with ID " + id + " does not exist");
        }
        
        storage.put(id, document);
        return document;
    }
}