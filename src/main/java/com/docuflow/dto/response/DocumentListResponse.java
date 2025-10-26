package com.docuflow.dto.response;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para respuestas que contienen listas de documentos
 */
public class DocumentListResponse {

    private List<DocumentSummary> documents;
    private int totalCount;
    private String message;

    // Constructors
    public DocumentListResponse() {}

    public DocumentListResponse(List<DocumentSummary> documents, String message) {
        this.documents = documents;
        this.totalCount = documents != null ? documents.size() : 0;
        this.message = message;
    }

    // Getters y Setters
    public List<DocumentSummary> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentSummary> documents) {
        this.documents = documents;
        this.totalCount = documents != null ? documents.size() : 0;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Clase interna para resúmenes de documentos
    public static class DocumentSummary {
        private Long id;
        private String type;
        private String title;
        private LocalDateTime createdAt;

        public DocumentSummary() {}

        public DocumentSummary(Long id, String type, String title, LocalDateTime createdAt) {
            this.id = id;
            this.type = type;
            this.title = title;
            this.createdAt = createdAt;
        }

        // Getters y Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    }
}
