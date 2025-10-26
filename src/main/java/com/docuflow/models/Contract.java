package com.docuflow.models;

public class Contract extends Document {

    private String company;
    private String signature;

    public Contract() {
        super(); // ✅ Constructor vacío necesario para la Factory
    }

    public Contract(Long id, String title, String author, String content, String type, String company, String signature) {
        super(id, title, author, content, type);
        this.company = company;
        this.signature = signature;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String generateDocument() {
        return "Contract Document:\n" +
               "Title: " + getTitle() + "\n" +
               "Author: " + getAuthor() + "\n" +
               "Company: " + company + "\n" +
               "Signature: " + signature + "\n" +
               "Content: " + getContent();
    }
}


