package com.docuflow.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateComplexDocumentRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    @Size(min = 3, max = 100)
    private String author;

    @NotBlank
    private String header;

    @NotBlank
    private String footer;

    private String content;
}
