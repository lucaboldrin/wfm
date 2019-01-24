/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfm;

import java.time.Duration;

/**
 *
 * @author 37226125
 */
public class JobType {
    
    private final String code;
    private final Duration duration;
    private final WfmDurationStringConverter converter;
    private final String str;
    
    public JobType(String Code, Duration theDuration) {
        this.code = Code;
        this.duration = theDuration;
        this.converter = new WfmDurationStringConverter();     
        this.str = converter.toString(duration)+ "   " + code;
    }
    
    public JobType(String Code, String theDuration) {
        this(Code,Duration.ofMinutes(Long.parseLong(theDuration)));
    }
    
    public String getCode() {
        return code;
    } 
    
    
    public Duration getDuration() {
        return duration;
    }
    
    @Override
    public String toString() {
        return str;
    }
}
