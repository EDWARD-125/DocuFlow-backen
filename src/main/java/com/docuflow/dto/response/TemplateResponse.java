package com.docuflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateResponse {
    private Long id;
    private String templateName;
    private String description;

    // 🔹 Agregamos estos dos porque el mapper los está usando:
    private String type;
    private String content;
}
