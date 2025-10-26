package com.docuflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO para crear un documento a partir de una plantilla.
 */
@Getter
@Setter
@NoArgsConstructor
public class CreateFromTemplateRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String templateName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String author;

    @NotBlank
    @Size(min = 10, max = 2000)
    private String content; // 🔹 Este campo faltaba

}
