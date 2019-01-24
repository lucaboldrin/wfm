/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfm;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.util.converter.LocalTimeStringConverter;

/**
 *
 * @author 37226125
 */
public class WrUI extends HBox {
    
    public static final int DEFAULT_SPINNER_MIN_VALUE = -120;
    public static final int DEFAULT_SPINNER_MAX_VALUE = 120;
    public static final int DEFAULT_SPINNER_INIT_VALUE = 0;
    
    private final ComboBox<JobType> jobTypeCombo;
    private final Spinner tuningSpinner;
    private final TextField endTimeField;
    private ChangeListener wrControl;
    private final TextFormatter<LocalTime> tf;
    
    public WrUI( ArrayList<JobType> JobTypeList, int MinimunSpinnerValue, int MaximumSpinnerValue, int InitialSpinnerValue) {
        super();
        jobTypeCombo = new ComboBox<>(FXCollections.observableList(JobTypeList));
        tuningSpinner = new Spinner(MinimunSpinnerValue,MaximumSpinnerValue,InitialSpinnerValue);
        tuningSpinner.setEditable(true);
        endTimeField = new TextField();
        tf = new TextFormatter<>(new LocalTimeStringConverter(DateTimeFormatter.ofPattern("HH:mm"), DateTimeFormatter.ofPattern("HH:mm")));
        endTimeField.setTextFormatter(tf);
        endTimeField.setEditable(false);
        getChildren().addAll(jobTypeCombo,endTimeField,tuningSpinner);
        setSpacing(5);
    }
    
    public void reset() {
        tuningSpinner.getValueFactory().setValue(DEFAULT_SPINNER_INIT_VALUE);
        jobTypeCombo.getSelectionModel().clearSelection();
    }
    
    public WrUI( ArrayList<JobType> JobTypeList ) {
        this(JobTypeList,DEFAULT_SPINNER_MIN_VALUE,DEFAULT_SPINNER_MAX_VALUE,DEFAULT_SPINNER_INIT_VALUE);
    }
    
    public void updateEndTime(LocalTime localTime) {
        tf.setValue(localTime);
        //endTimeField.setText(tf.getValueConverter().toString(localTime));
    }
    
    public void setController(ChangeListener wrControl) {
        this.wrControl = wrControl;
        tuningSpinner.valueProperty().addListener(wrControl);
        jobTypeCombo.valueProperty().addListener(wrControl);
    }
    
    public void removeController(ChangeListener wrControl) {
        tuningSpinner.valueProperty().removeListener(wrControl);
        jobTypeCombo.valueProperty().removeListener(wrControl);
    }
    
    public void removeController() {
        removeController(this.wrControl);
    }
    
    public ChangeListener getController() {
        return wrControl;
    }
    
}
