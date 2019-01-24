/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wfm;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Luca Boldrin <luca.boldrin@telecomitalia.it>
 */
public class XMLFactory {
    
    private static final XMLFactory factory = new XMLFactory();
    
    public static XMLFactory getInstance() {
        return factory;
    }
    
    public void parse(DefaultHandler handler, File aFile) throws    IOException,
                                                                    ParserConfigurationException, 
                                                                    SAXException { 
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(aFile, handler);    
    }
    
}
