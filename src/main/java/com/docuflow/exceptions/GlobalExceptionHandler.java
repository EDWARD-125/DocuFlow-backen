package com.docuflow.exceptions;

import com.docuflow.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Manejador global de excepciones
 * Centraliza el manejo de errores y proporciona respuestas consistentes
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Maneja DocumentNotFoundException
     */
    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDocumentNotFound(
            DocumentNotFoundException ex, 
            WebRequest request) {
        
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage()
        );
        error.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    /**
     * Maneja InvalidDocumentTypeException
     */
    @ExceptionHandler(InvalidDocumentTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDocumentType(
            InvalidDocumentTypeException ex, 
            WebRequest request) {
        
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage()
        );
        error.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    /**
     * Maneja ExportException
     */
    @ExceptionHandler(ExportException.class)
    public ResponseEntity<ErrorResponse> handleExportException(
            ExportException ex, 
            WebRequest request) {
        
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Export Failed",
                ex.getMessage()
        );
        error.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    /**
     * Maneja errores de validación (@Valid)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        
        List<String> details = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.add(error.getField() + ": " + error.getDefaultMessage());
        }
        
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "Invalid input data",
                details
        );
        error.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    /**
     * Maneja IllegalArgumentException
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex,
            WebRequest request) {
        
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage()
        );
        error.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    /**
     * Maneja cualquier otra excepción no capturada
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request) {
        
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error occurred: " + ex.getMessage()
        );
        error.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}