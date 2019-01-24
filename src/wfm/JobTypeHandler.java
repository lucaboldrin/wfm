/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfm;

import java.time.Duration;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author 37226125
 */
public class JobTypeHandler extends DefaultHandler {
    
    public final static String EL_JOBTYPE="jobtype";
    public final static String AT_NAME="name";
    public final static String AT_TIME="time";
    
    private final ArrayList<JobType> pool;
    //private final TimeStringConverter converter;
    
    public JobTypeHandler(ArrayList<JobType> pool) {
        this.pool = pool;
        //converter = new TimeStringConverter("HH:mm");
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals(EL_JOBTYPE)) {
            //pool.put(attributes.getValue(AT_NAME),converter.fromString(toTimeFormat(attributes.getValue(AT_TIME))));
            pool.add(new JobType(attributes.getValue(AT_NAME), Duration.ofMinutes(Integer.parseInt(attributes.getValue(AT_TIME)))));
        }
    }
    
}
