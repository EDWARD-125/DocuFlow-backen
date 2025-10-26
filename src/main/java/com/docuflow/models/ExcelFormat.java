// ExcelFormat.java
package com.docuflow.models;

public class ExcelFormat implements DocumentFormat {
    @Override
    public String export(Document document) {
        return "Excel Export: " + document.generateDocument();
    }

    @Override
    public String getFormatType() {
        return "EXCEL";
    }
}