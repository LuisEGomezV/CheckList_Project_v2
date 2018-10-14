package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Controller {
    //declaracion de los paneles
    @FXML private AnchorPane startPanel;
    @FXML private AnchorPane newEntryPanel;
    @FXML private AnchorPane resumePanel;
    @FXML private AnchorPane fileReportPanel;
    @FXML private AnchorPane listEditPanel;
    @FXML private AnchorPane settingsPanel;
    @FXML private JFXTextField dataBaseTextField1;
    @FXML private JFXTextField reportTextField1;
    @FXML private JFXTextField dataBaseTextField2;
    @FXML private JFXTextField reportFileTextField2;

    String dataBasePath = new String();
    String reportPath = new String();


    //metodo para cerrar la apicacion
    public void onExitButtonClicked(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void ondataBaseFileClicked(MouseEvent event){
        JFileChooser saveDataBase = new JFileChooser();
        saveDataBase.showSaveDialog(null);
        File file1 = new File(saveDataBase.getSelectedFile()+".xlsx");
        dataBasePath = saveDataBase.getSelectedFile().getPath();//String que contiene la ruta donde se guarda la base de datos
        this.dataBaseTextField1.setText(dataBasePath + ".xlsx");
        try {
            BufferedWriter salida1 = new BufferedWriter(new FileWriter(file1));
        }catch(Exception e) {

        }

    }

    public void onDataBaseFileClickedSettings(MouseEvent event){
        JFileChooser saveDataBaseSettings = new JFileChooser();
        saveDataBaseSettings.showSaveDialog(null);
        File file3 = new File(saveDataBaseSettings.getSelectedFile()+".xlsx");
        dataBasePath = saveDataBaseSettings.getSelectedFile().getPath();//String que contiene la ruta donde se guarda la base de datos
        this.dataBaseTextField2.setText(dataBasePath + ".xlsx");
        try {
            BufferedWriter salida3 = new BufferedWriter(new FileWriter(file3));
        }catch(Exception e) {

        }
    }
    public void onReportFileClicked(MouseEvent event){
        JFileChooser saveReportFile = new JFileChooser();
        saveReportFile.showSaveDialog(null);
        File file2 = new File(saveReportFile.getSelectedFile()+".xlsx");
        reportPath = saveReportFile.getSelectedFile().getPath();//String que contiene la ruta donde se guarda el reporte
        this.reportTextField1.setText(reportPath + ".xlsx");
        try {
            BufferedWriter salida2 = new BufferedWriter(new FileWriter(file2));
        }catch(Exception e) {

        }
    }

    public void onReportFileClickedSettings(MouseEvent event){
        JFileChooser saveReportFileSettings = new JFileChooser();
        saveReportFileSettings.showSaveDialog(null);
        File file3 = new File(saveReportFileSettings.getSelectedFile()+".xlsx");
        reportPath = saveReportFileSettings.getSelectedFile().getPath();//String que contiene la ruta donde se guarda el reporte
        this.reportFileTextField2.setText(reportPath + ".xlsx");
        try {
            BufferedWriter salida2 = new BufferedWriter(new FileWriter(file3));
        }catch(Exception e) {

        }
    }
    //metodo para el boton de nuevo
    public void onNewButtonCliked(MouseEvent event){
        startPanel.setVisible(false);
        newEntryPanel.setVisible(true);
        resumePanel.setVisible(false);
        settingsPanel.setVisible(false);
        dataBaseTextField1.setDisable(true);
        reportTextField1.setDisable(true);
        this.dataBaseTextField1.setText("");
        this.dataBaseTextField2.setText("");
        this.reportTextField1.setText("");
        this.reportFileTextField2.setText("");
    }

    //metodo para el boton de resumir
    public void onResumeButtonCliked(MouseEvent event){
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(true);
        settingsPanel.setVisible(false);
    }

    //metodo para el boton de continuar
    public void onSaveContinueButtonClicked(MouseEvent event){
        startPanel.setVisible(true);
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(true);
        settingsPanel.setVisible(false);
        this.dataBaseTextField1.setText("");
        this.dataBaseTextField2.setText("");
        this.reportTextField1.setText("");
        this.reportFileTextField2.setText("");
    }

    public void onHomeButtonClicked(MouseEvent event){
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(false);
        startPanel.setVisible(true);
        settingsPanel.setVisible(false);
    }

    public void onFileEditClicked(MouseEvent event){
        if(fileReportPanel.isVisible()){
            fileReportPanel.setVisible(false);
            return;
        }
        fileReportPanel.setVisible(true);
    }

    public void onListEditClicked(MouseEvent event){
        if(listEditPanel.isVisible()){
            listEditPanel.setVisible(false);
            return;
        }
        listEditPanel.setVisible(true);
    }

    public void onSettingsButtonClicked(MouseEvent event){
        settingsPanel.setVisible(true);
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(false);
        dataBaseTextField2.setDisable(true);
        reportFileTextField2.setDisable(true);
    }
}