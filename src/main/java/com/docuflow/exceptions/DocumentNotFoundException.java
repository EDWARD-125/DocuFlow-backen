package com.docuflow.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un documento
 */
public class DocumentNotFoundException extends RuntimeException {
    
    private Long documentId;
    
    public DocumentNotFoundException(String message) {
        super(message);
    }
    
    public DocumentNotFoundException(Long documentId) {
        super("Document not found with ID: " + documentId);
        this.documentId = documentId;
    }
    
    public DocumentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DocumentNotFoundException(Long documentId, Throwable cause) {
        super("Document not found with ID: " + documentId, cause);
        this.documentId = documentId;
    }
    
    public Long getDocumentId() {
        return documentId;
    }
}