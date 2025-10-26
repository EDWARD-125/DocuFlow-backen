// PDFFormatFactory.java
package com.docuflow.factories;

import com.docuflow.models.DocumentFormat;
import com.docuflow.models.PDFFormat;
import org.springframework.stereotype.Component;

@Component("pdfFactory")
public class PDFFormatFactory implements DocumentFormatFactory {
    @Override
    public DocumentFormat createFormat() {
        return new PDFFormat();
    }
}