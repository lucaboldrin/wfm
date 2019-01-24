/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfm;

import java.time.LocalTime;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author 37226125
 */
public class WrControl implements ChangeListener {
    private final WrBean wr;
    private final WrUI wrUI;
    private final ObservableValue<LocalTime> initialTime;

    public WrControl(WrBean wr, WrUI wrUI, ObservableValue<LocalTime> InitialTime) {
        this.wr = wr;
        this.wrUI = wrUI;
        this.initialTime = InitialTime;
        wrUI.setController(this);
        InitialTime.addListener(this);
    }
    
    public WrUI getWrUI() {
        return wrUI;
    }
    
    public WrBean getWrBean() {
        return wr;
    }
    
    @Override
    public void changed(ObservableValue ov, Object oldVal, Object newVal) {
        if (newVal == null) {
            wrUI.updateEndTime(null);
        } else {
            wr.setStartTime(initialTime.getValue());
            if (newVal.getClass().equals(JobType.class)) {
                wr.setJobType((JobType)newVal);
            }
            if (newVal.getClass().equals(Integer.class)) {
                wr.setTuning((int)newVal);         
            }
            wrUI.updateEndTime(wr.getEndTime());
        }
    }  
}
