package com.docuflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponse {
    private Long id;          // ✅ algunos mappers lo asignan
    private String title;
    private String author;
    private String type;      // ✅ lo usa el mapper (getType)
    private String content;
    private String patternUsed; // ✅ lo usa el mapper (setPatternUsed)
}
