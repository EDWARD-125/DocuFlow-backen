package com.docuflow.models;

public class Invoice extends Document {
    private String invoiceNumber;
    private double amount;

    public Invoice() {
        super(); // ✅ Constructor vacío
    }

    public Invoice(Long id, String title, String author, String content, String type, String invoiceNumber, double amount) {
        super(id, title, author, content, type);
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
    }

    @Override
    public String generateDocument() {
        return "Invoice Document:\n" +
               "Invoice Number: " + invoiceNumber + "\n" +
               "Amount: " + amount + "\n" +
               "Author: " + getAuthor() + "\n" +
               "Content: " + getContent();
    }
}
