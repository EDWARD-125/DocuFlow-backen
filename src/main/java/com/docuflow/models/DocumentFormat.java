// DocumentFormat.java (Interface)
package com.docuflow.models;

public interface DocumentFormat {
    String export(Document document);
    String getFormatType();
}
