package com.docuflow.dto.response;

public class DocumentResponseDTO {
    private String id;
    private String name;
    private String content;
    private String patternUsed;

    public DocumentResponseDTO() {}

    public DocumentResponseDTO(String id, String name, String content, String patternUsed) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.patternUsed = patternUsed;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getPatternUsed() {
        return patternUsed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPatternUsed(String patternUsed) {
        this.patternUsed = patternUsed;
    }
}
