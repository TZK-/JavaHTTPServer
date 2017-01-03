package granadat.httpserver.html;

import com.sun.org.apache.xerces.internal.parsers.SAXParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import granadat.httpserver.students.StudentsManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class XMLToHTMLParser implements Parsable, Htmlable {

    private DefaultHandler handler;

    public XMLToHTMLParser(StudentsManager sm) {
        this.handler = new XMLToHTMLHandler(sm);
    }

    @Override
    public void parse(String file) {
        XMLReader parser = null;

        try {
            parser = XMLReaderFactory.createXMLReader(SAXParser.class.getName());
        } catch (SAXException e) {
            e.printStackTrace();
        }

        try {
            InputSource is = new InputSource(new FileInputStream(new File(file)));
            parser.setContentHandler(this.handler);
            parser.parse(is);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getHtml() {
        return XMLToHTMLHandler.html;
    }

}
