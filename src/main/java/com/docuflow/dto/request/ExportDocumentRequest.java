package com.docuflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para exportar documentos en diferentes formatos.
 */
@Getter
@Setter
@NoArgsConstructor
public class ExportDocumentRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String documentName;

    @NotBlank
    private String documentType; // 🔹 Usado por getDocumentType()

    @NotBlank
    @Size(min = 10, max = 2000)
    private String content; // 🔹 Usado por getContent()

    @NotBlank
    @Pattern(regexp = "PDF|WORD|EXCEL|TXT", message = "Format type must be one of PDF, WORD, EXCEL, or TXT")
    private String formatType; // 🔹 Usado por getFormatType()

}
