/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfm;


import java.time.Duration;
import java.time.LocalTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author 37226125
 */
public class WrBean {
    
    private final SimpleObjectProperty<LocalTime> startTimeProperty;
    private final SimpleObjectProperty<LocalTime> endTimeProperty;
    private final SimpleObjectProperty<JobType> jobTypeProperty;
    private final SimpleIntegerProperty tuningProperty;
    
    public WrBean(LocalTime InitialTime, JobType theJob, int InitialTuning) {
        this.jobTypeProperty = new SimpleObjectProperty<>(this,"jobTypeProperty");
        this.startTimeProperty = new SimpleObjectProperty<>(this,"startTimeProperty");
        this.endTimeProperty = new SimpleObjectProperty<>(this,"endTimeProperty");
        this.tuningProperty = new SimpleIntegerProperty(this,"tuningProperty");
        this.tuningProperty.set(InitialTuning);
        if (InitialTime != null)
            this.startTimeProperty.setValue(InitialTime);
        if (theJob != null)            
            this.jobTypeProperty.setValue(theJob);
    }
    
    public WrBean() {
        this(null,null,0);
    }
    /**
     * 
     * @return the startTimeProperty
     */
    public ObjectProperty<LocalTime> startTimeProperty() {
        return startTimeProperty;
    }
    /**
     * 
     * @return the endTimeProperty
     */
    public ObjectProperty<LocalTime> endTimeProperty() {
        return endTimeProperty;
    }
    /**
     * 
     * @return the jobTypeProperty
     */
    public ObjectProperty<JobType> jobTypeProperty() {
        return jobTypeProperty;
    }
    /**
     * @return the tuningProperty
     */
    public SimpleIntegerProperty tuningProperty() {
        return tuningProperty;
    }
    /**
     * 
     * @return the startTime value
     */
    public LocalTime getStartTime() {
        return startTimeProperty.getValue();
    }
    /**
     * 
     * @return the endTime value
     */
    public LocalTime getEndTime() {
        return endTimeProperty.getValue();
    }
    /**
     * 
     * @return the jobType value
     */
    public JobType getJobType() {
        return jobTypeProperty.getValue();
    }
    /**
     * 
     * @return the tuning value
     */
    public int getTuning() {
        return tuningProperty.getValue();
    }
    /**
     * 
     * @param StartTime value to set
     */
    public void setStartTime(LocalTime StartTime) {
        startTimeProperty.setValue(StartTime);
        updateEndTime();
    }
    /**
     * 
     * @param theJobType value to set 
     */
    public void setJobType(JobType theJobType) {
        jobTypeProperty.setValue(theJobType);
        updateEndTime();
    }
    /**
     * 
     * @param Tuning value to set
     */
    public void setTuning(int Tuning) {
        tuningProperty.setValue(Tuning);
        updateEndTime();
    }

    
    private void updateEndTime() {
        if (jobTypeProperty.getValue() != null) {
            LocalTime st = startTimeProperty.getValue();
            JobType jt = jobTypeProperty.getValue();
            int tu = tuningProperty.get();
            endTimeProperty.setValue(st.plus(jt.getDuration().plus(Duration.ofMinutes(tu))));            
        }
    }
    
}
