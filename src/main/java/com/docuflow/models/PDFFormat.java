// PDFFormat.java
package com.docuflow.models;

public class PDFFormat implements DocumentFormat {
    @Override
    public String export(Document document) {
        return "PDF Export: " + document.generateDocument();
    }

    @Override
    public String getFormatType() {
        return "PDF";
    }
}
