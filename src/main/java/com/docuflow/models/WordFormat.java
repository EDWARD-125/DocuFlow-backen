// WordFormat.java
package com.docuflow.models;

public class WordFormat implements DocumentFormat {
    @Override
    public String export(Document document) {
        return "Word Export: " + document.generateDocument();
    }

    @Override
    public String getFormatType() {
        return "WORD";
    }
}
