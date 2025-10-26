package com.docuflow.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExportResponse {
    private String fileName;
    private String format;
    private String downloadUrl;
}
