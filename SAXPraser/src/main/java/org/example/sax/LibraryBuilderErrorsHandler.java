package org.example.sax;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class LibraryBuilderErrorsHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("WARN: " + exception);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("ERROR: " + exception);
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL ERROR: " + exception);
    }
}
