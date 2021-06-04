package org.example.sax;

import org.example.model.Library;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Apteev Artem
 * @Data 04.06.21
 */
public final class LibraryLauncher {

    public final String path;

    public LibraryLauncher(String path) {
        this.path = path;
    }

    public Library launch() throws IOException {
        try (final InputStream is = LibraryLauncher.class.getResourceAsStream(this.path)) {
            return buildLibrary(is);
        }
    }

    private Library buildLibrary(final InputStream is) throws IOException {
        try {
            LibraryBuilderHandler handler = new LibraryBuilderHandler();
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            xmlReader.setErrorHandler(new LibraryBuilderErrorsHandler());
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(is));
            return handler.getLibrary();
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return new Library();
    }
}
