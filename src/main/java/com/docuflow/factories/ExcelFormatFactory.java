// ExcelFormatFactory.java
package com.docuflow.factories;

import com.docuflow.models.DocumentFormat;
import com.docuflow.models.ExcelFormat;
import org.springframework.stereotype.Component;

@Component("excelFactory")
public class ExcelFormatFactory implements DocumentFormatFactory {
    @Override
    public DocumentFormat createFormat() {
        return new ExcelFormat();
    }
}