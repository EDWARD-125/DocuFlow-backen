package com.docuflow.exceptions;

/**
 * Excepción lanzada cuando se intenta crear un documento con tipo inválido
 */
public class InvalidDocumentTypeException extends RuntimeException {
    
    private String invalidType;
    private String[] validTypes;
    
    public InvalidDocumentTypeException(String message) {
        super(message);
    }
    
    public InvalidDocumentTypeException(String invalidType, String[] validTypes) {
        super(buildMessage(invalidType, validTypes));
        this.invalidType = invalidType;
        this.validTypes = validTypes;
    }
    
    public InvalidDocumentTypeException(String message, Throwable cause) {
        super(message, cause);
    }
    
    private static String buildMessage(String invalidType, String[] validTypes) {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid document type: '").append(invalidType).append("'. ");
        sb.append("Valid types are: ");
        
        if (validTypes != null && validTypes.length > 0) {
            sb.append(String.join(", ", validTypes));
        }
        
        return sb.toString();
    }
    
    public String getInvalidType() {
        return invalidType;
    }
    
    public String[] getValidTypes() {
        return validTypes;
    }
}