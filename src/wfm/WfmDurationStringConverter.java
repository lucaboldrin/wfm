/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfm;

import java.time.Duration;
import javafx.util.StringConverter;

/**
 *
 * @author 37226125
 */
public class WfmDurationStringConverter extends StringConverter<Duration> {
    
    public WfmDurationStringConverter() {
        super();
    }
    
    @Override
    public Duration fromString(String value) {
        if (value == null)
            return null;
        long h,m;
        Duration dur;
        int separatorPos = value.indexOf(':');
        if (separatorPos == -1) {
            dur = Duration.ofMinutes(Long.parseLong(value));
        } else {
            h = Long.parseLong(value.substring(0,separatorPos));
            m = Long.parseLong(value.substring(separatorPos + 1));
            dur = Duration.ofMinutes(h*60 + m);
        }
        return dur;
    }
    
    @Override
    public String toString(Duration value) {
        if (value == null)
            return "";
        long h = value.toHours();
        long m = value.toMinutes() % 60;
        StringBuilder temp = new StringBuilder();
        if (h < 10)
            temp.append("0");
        temp.append(h);
        temp.append(":");
        if (m < 10)
            temp.append("0");
        temp.append(m);
        return temp.toString();
    }
}
