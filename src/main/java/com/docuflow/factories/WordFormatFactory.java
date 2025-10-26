// WordFormatFactory.java
package com.docuflow.factories;

import com.docuflow.models.DocumentFormat;
import com.docuflow.models.WordFormat;
import org.springframework.stereotype.Component;

@Component("wordFactory")
public class WordFormatFactory implements DocumentFormatFactory {
    @Override
    public DocumentFormat createFormat() {
        return new WordFormat();
    }
}
