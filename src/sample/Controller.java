package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    @FXML private JFXTextField conferenceListTextField;
    @FXML private JFXTextField conferenceListSettingsTextField;
    @FXML private JFXComboBox<String> comBoxConference;
    @FXML private JFXComboBox<String> comoBoxCheckList;
    @FXML private JFXComboBox<String> comboBoxSettings;
    @FXML private Label limitConferenceLabel;
    @FXML private Label limitConferenceLabelSettings;
    @FXML private Label nullValueLabel;

    String dataBasePath = new String();
    String reportPath = new String();
    String llenarLista[]  = new String[10];
    String llenarListaaux[]  = new String[10];
    String vacia[] = {" "," "," "," "," "," "," "," "," "," "};
    int i;

    /*
    ObservableList<String> lista = FXCollections.observableArrayList(
            "conferencia1",
                    "conferencia2",
                    "conferencia3"
    );
    */

    //ObservableList<String> lista = FXCollections.observableArrayList(llenarLista);

    //metodo para cerrar la apicacion
    public void onExitButtonClicked(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void onAddButtonClicked(MouseEvent event){
        if(i < 10){
            llenarLista[i] = conferenceListTextField.getText();
            llenarListaaux[i] = conferenceListTextField.getText();
            if(llenarLista[i] == ""){
                nullValueLabel.setVisible(true);
            }
            else{
                ObservableList<String> lista = FXCollections.observableArrayList(llenarLista);
                comBoxConference.setItems(lista);
                comoBoxCheckList.setItems(lista);
                comboBoxSettings.setItems(lista);
                this.conferenceListTextField.setText("");
                i ++;
            }
        }
        else{
            limitConferenceLabel.setVisible(true);
        }
    }

    public void onAddButtonSettingsClicked(MouseEvent event){
        if(i < 10){
            llenarLista[i] = conferenceListSettingsTextField.getText();
            llenarListaaux[i] = conferenceListSettingsTextField.getText();
            ObservableList<String> lista = FXCollections.observableArrayList(llenarLista);
            comoBoxCheckList.setItems(lista);
            comboBoxSettings.setItems(lista);
            this.conferenceListSettingsTextField.setText("");
            i ++;
        }
        else{
            limitConferenceLabelSettings.setVisible(true);
        }
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
        i = 0;
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
        limitConferenceLabel.setVisible(false);
        for(int j = 0 ; j < 10 ; j ++){
            llenarLista[j] = " ";
            //limitConferenceLabel.setVisible(true);
        }
        ObservableList<String> lista = FXCollections.observableArrayList(vacia);
        comBoxConference.setItems(lista);
        comoBoxCheckList.setItems(lista);
        comboBoxSettings.setItems(lista);
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
        limitConferenceLabel.setVisible(false);
        limitConferenceLabelSettings.setVisible(false);
        fileReportPanel.setVisible(false);
        listEditPanel.setVisible(false);
        ObservableList<String> lista = FXCollections.observableArrayList(vacia);
        comBoxConference.setItems(lista);
    }

    public void onHomeButtonClicked(MouseEvent event){
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(false);
        startPanel.setVisible(true);
        settingsPanel.setVisible(false);
        fileReportPanel.setVisible(false);
        listEditPanel.setVisible(false);
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