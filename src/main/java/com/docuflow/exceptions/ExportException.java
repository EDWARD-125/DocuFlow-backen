package com.docuflow.exceptions;

/**
 * Excepción lanzada cuando falla la exportación de un documento
 */
public class ExportException extends RuntimeException {
    
    private String formatType;
    private String documentType;
    
    public ExportException(String message) {
        super(message);
    }
    
    public ExportException(String message, String formatType) {
        super(message);
        this.formatType = formatType;
    }
    
    public ExportException(String message, String formatType, String documentType) {
        super(message);
        this.formatType = formatType;
        this.documentType = documentType;
    }
    
    public ExportException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ExportException(String message, Throwable cause, String formatType) {
        super(message, cause);
        this.formatType = formatType;
    }
    
    public String getFormatType() {
        return formatType;
    }
    
    public String getDocumentType() {
        return documentType;
    }
}
