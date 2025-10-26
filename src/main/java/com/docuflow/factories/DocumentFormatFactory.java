// DocumentFormatFactory.java (Abstract Factory Interface)
package com.docuflow.factories;

import com.docuflow.models.DocumentFormat;

public interface DocumentFormatFactory {
    DocumentFormat createFormat();
}