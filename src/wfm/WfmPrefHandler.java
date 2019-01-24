/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfm;

import java.time.Duration;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import static wfm.JobTypeHandler.AT_NAME;
import static wfm.JobTypeHandler.AT_TIME;
import static wfm.JobTypeHandler.EL_JOBTYPE;

/**
 *
 * @author Luca Boldrin <luca.boldrin@telecomitalia.it>
 */
public class WfmPrefHandler extends DefaultHandler {
    
    public final static String EL_PREF="pref";
    public final static String EL_JB="jb";
    public final static String AT_NAME="name";
    public final static String AT_TUNING="tuning-2";
    
    private final ArrayList<WfmPref> list;
    private WfmPref currentPref;
    
    public WfmPrefHandler(ArrayList<WfmPref> list) {
        this.list = list;
        
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(EL_PREF)) {
            currentPref = new WfmPref();
        } else if (qName.equals(EL_JB)) {
            currentPref.add(attributes.getValue(AT_NAME), attributes.getValue(AT_TUNING));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(EL_PREF)) {
            list.add(currentPref);
        }
    }
    
    
}
