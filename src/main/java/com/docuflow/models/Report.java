package com.docuflow.models;

public class Report extends Document {
    private String summary;

    public Report() {
        super(); // ✅ Constructor vacío
    }

    public Report(Long id, String title, String author, String content, String type, String summary) {
        super(id, title, author, content, type);
        this.summary = summary;
    }

    @Override
    public String generateDocument() {
        return "Report Document:\n" +
               "Title: " + getTitle() + "\n" +
               "Author: " + getAuthor() + "\n" +
               "Summary: " + summary + "\n" +
               "Content: " + getContent();
    }
}
