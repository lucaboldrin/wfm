/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wfm;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author 37226125
 */
public class Wfm extends Application {
    
    public static final int DEFAULT_SCENE_WIDTH = 700;
    public static final int DEFAULT_SCENE_HEIGHT = 300;
    public static final int DEFAULT_WR_NUMBER = 6;
    public static final int DEFAULT_COLUMN_NUMBER = 4;
    
    private File jobTypeFile;
    private Exception initEx = null; 
    private TextField startTimeTF;
    private GridPane root;
    private Button resetButton;
    private Label label;

    
    private SimpleObjectProperty<LocalTime> startTimeProperty;
    private final ArrayList<JobType> jobTypeList;
    private final ArrayList<WfmPref> prefList;
    
    public Wfm() {
        jobTypeList = new ArrayList<>();
        prefList = new ArrayList<>();
    }
    
    private void initControls() {
        root = new GridPane();
        root.setVgap(5);
        root.setPadding(new Insets(5,5,5,5));
        startTimeProperty = new SimpleObjectProperty<>(this,"startTimeProperty");
        startTimeTF = new TextField("08:00");
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");
        TextFormatter<LocalTime> tf = new TextFormatter<>(new LocalTimeStringConverter(dtf1, dtf2),LocalTime.of(8,0));
        startTimeTF.setTextFormatter(tf);
        startTimeProperty.bind(tf.valueProperty());
        HBox header = new HBox();
        resetButton = new Button("Reset");
        label = new Label("Inizio turno :");
        label.setGraphic(startTimeTF);
        label.setContentDisplay(ContentDisplay.RIGHT);
        header.setSpacing(5);
        header.getChildren().addAll(label,resetButton);
        root.addRow(1, header);
        WrControl wrControl1 = new WrControl(new WrBean(),new WrUI(jobTypeList),startTimeProperty);
        WrControl wrControl2 = new WrControl(new WrBean(),new WrUI(jobTypeList),wrControl1.getWrBean().endTimeProperty());
        WrControl wrControl3 = new WrControl(new WrBean(),new WrUI(jobTypeList),wrControl2.getWrBean().endTimeProperty());
        WrControl wrControl4 = new WrControl(new WrBean(),new WrUI(jobTypeList),wrControl3.getWrBean().endTimeProperty());
        WrControl wrControl5 = new WrControl(new WrBean(),new WrUI(jobTypeList),wrControl4.getWrBean().endTimeProperty());
        WrControl wrControl6 = new WrControl(new WrBean(),new WrUI(jobTypeList),wrControl5.getWrBean().endTimeProperty());
        root.addRow(2, wrControl1.getWrUI());
        root.addRow(3, wrControl2.getWrUI());
        root.addRow(4, wrControl3.getWrUI());
        root.addRow(5, wrControl4.getWrUI());
        root.addRow(6, wrControl5.getWrUI());
        root.addRow(7, wrControl6.getWrUI());
        resetButton.setOnAction(eh -> {
            wrControl1.getWrUI().reset();
            wrControl2.getWrUI().reset();
            wrControl3.getWrUI().reset();
            wrControl4.getWrUI().reset();
            wrControl5.getWrUI().reset();
            wrControl6.getWrUI().reset();
        });
    }
    
    
    @Override
    public void init() {
        jobTypeFile = new File("jobtype.xml");
        XMLFactory xmlFactory = XMLFactory.getInstance();
        try {
            xmlFactory.parse(new JobTypeHandler(jobTypeList), jobTypeFile);
            //jt = new ArrayList<>(pool.keySet());
            Collections.sort(jobTypeList, new Comparator<JobType>() {
                @Override
                public int compare(JobType jt1, JobType jt2) {
                    return jt1.getCode().compareTo(jt2.getCode());
                }
            });
            xmlFactory.parse(new WfmPrefHandler(prefList), new File("prefs.xml"));
            initControls();
        } catch(IOException | ParserConfigurationException | SAXException ex) {
            initEx = ex;
        }
    }
    
    @Override
    public void start(Stage primaryStage) {   
        if (initEx != null) {
            primaryStage.setTitle("Errore di inizializzazione");
            primaryStage.setScene(getErrorScene(DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT));
        } else {
            primaryStage.setTitle("WFM JobType");
            primaryStage.setScene(getWorkingScene(DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT));

        }
        primaryStage.show();
    }

    private Scene getErrorScene(int width, int height) {
        StackPane root = new StackPane();
        TextArea msgArea = new TextArea();
        root.getChildren().add(msgArea);
        msgArea.setText("\n" + initEx.getMessage()+"\n");
        return new Scene(root, width, height); 
    } 
    
    private Scene getWorkingScene(int width, int height) {
        return new Scene(root,width,height);
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
