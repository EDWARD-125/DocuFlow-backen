package com.docuflow.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDocumentRequest {

    @NotNull
    private Long id;

    @Size(min = 3, max = 100)
    private String title;

    @Size(min = 3, max = 100)
    private String author;

    private String content;
}
