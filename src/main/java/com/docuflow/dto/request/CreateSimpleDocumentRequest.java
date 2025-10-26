package com.docuflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para crear un documento simple.
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateSimpleDocumentRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    @Size(min = 3, max = 50)
    private String author;

    @NotBlank
    @Size(min = 10, max = 1000)
    private String content;

    @NotBlank
    private String type; // 🔹 Este campo faltaba

}
