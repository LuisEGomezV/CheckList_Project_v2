package sample;

import com.CongresoCEUAA.AttendaceSystem.AttendantsList;
import com.CongresoCEUAA.Congress;
import com.CongresoCEUAA.FileSystem.ExcelReader;
import com.CongresoCEUAA.FileSystem.FilesData.CollectionData;
import com.CongresoCEUAA.FileSystemTEST;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //declaracion de los paneles
    @FXML private AnchorPane startPanel;
    @FXML private AnchorPane newEntryPanel;
    @FXML private AnchorPane resumePanel;
    @FXML private AnchorPane fileReportPanel;
    @FXML private AnchorPane listEditPanel;
    @FXML private AnchorPane settingsPanel;
    @FXML private AnchorPane generarReportePanel;
    @FXML private AnchorPane guardarPanel;
    @FXML private JFXTextField dataBaseTextField1;
    @FXML private JFXTextField reportTextField1;
    @FXML private JFXTextField dataBaseTextField2;
    @FXML private JFXTextField reportFileTextField2;
    @FXML private JFXTextField conferenceListTextField;
    @FXML private JFXTextField conferenceListSettingsTextField;
    @FXML private JFXTextField idTextField;
    @FXML private JFXTextField firtsRowTextField;
    @FXML private JFXTextField idColumTextField;
    @FXML private JFXTextField nameColumTextField;
    @FXML private JFXTextField groupColumTextField;
    @FXML private JFXComboBox<String> comoBoxCheckList;
    @FXML private JFXComboBox<String> comboBoxSettings;

    String dataBasePath = new String();
    String reportPath = new String();
    int i;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firtsRowTextField.addEventFilter(KeyEvent.ANY, handlerNumbers);
        idColumTextField.addEventFilter(KeyEvent.ANY, handlerNumbers);
        nameColumTextField.addEventFilter(KeyEvent.ANY, handlerNumbers);
        groupColumTextField.addEventFilter(KeyEvent.ANY, handlerNumbers);
        dataBaseTextField1.setDisable(true);
    }

    //metodo para cerrar la apicacion
    public void onExitButtonClicked(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void onGuardarButtonClicked(MouseEvent event){
        generarReportePanel.setVisible(false);
        guardarPanel.setVisible(true);
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(false);
        settingsPanel.setVisible(false);
    }
    public void onGenerateReportButton(MouseEvent event){
        generarReportePanel.setVisible(true);
        guardarPanel.setVisible(false);
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(false);
        settingsPanel.setVisible(false);
        /*
        JFileChooser generateReport = new JFileChooser();
        generateReport.showSaveDialog(null);
        File fileReport = new File(generateReport.getSelectedFile()+".xlsx");
        try {
            BufferedWriter salida1 = new BufferedWriter(new FileWriter(fileReport));
        }catch(Exception e) {

        }
        */
    }

    Congress GetCurrentCongress()
    {
        try
        {
            if(FileSystemTEST.currentCongress == null)
                System.out.println("No current congress");

            return FileSystemTEST.currentCongress;
        }
        catch (Exception e)
        {
            System.out.println("No current congress");
        }

        return null;
    }



    void AddEvent(JFXTextField field)
    {
        String name = field.getText();
        if(name.length() <= 0 || name == "" || name == " ")
            return;

        GetCurrentCongress().AddEvent(name,null,null);
        field.setText("");

        UpdateComboBoxes();
    }

    void UpdateComboBoxes()
    {
        try
        {
            if(GetCurrentCongress() == null)
            {
                System.out.println("No current congress");
                return;
            }


            ObservableList<String> list = FXCollections.observableArrayList(GetCurrentCongress().GetAllEventNames());
            comoBoxCheckList.setItems(list);
            comboBoxSettings.setItems(list);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void RemoveEvent(JFXComboBox<String> box)
    {
        String name = box.getValue();
        GetCurrentCongress().RemoveEvent(name);
        UpdateComboBoxes();
    }

    public void onAddButtonSettingsClicked(MouseEvent event){

        AddEvent(conferenceListSettingsTextField);
    }

    public void onEliminarButtonClicked(MouseEvent event)
    {
        System.out.println("asdasd");
    }

    public void onEliminarSettingsButtonClicked(MouseEvent event){
        RemoveEvent(comboBoxSettings);
    }

    public void ondataBaseFileClicked(MouseEvent event){
        JFileChooser saveDataBase = new JFileChooser();
        saveDataBase.showSaveDialog(null);
        //File file1 = new File(saveDataBase.getSelectedFile()+".xlsx");
        dataBasePath = saveDataBase.getSelectedFile().getPath();//String que contiene la ruta donde se guarda la base de datos
        this.dataBaseTextField1.setText(dataBasePath + ".xlsx");
        /*
        try {
            BufferedWriter salida1 = new BufferedWriter(new FileWriter(file1));
        }catch(Exception e) {

        }
        */
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
        generarReportePanel.setVisible(false);
        guardarPanel.setVisible(false);
        this.dataBaseTextField1.setText("");
        this.dataBaseTextField2.setText("");
        this.reportTextField1.setText("");
        this.reportFileTextField2.setText("");
        UpdateComboBoxes();
    }

    //metodo para el boton de resumir
    public void onResumeButtonCliked(MouseEvent event){
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(true);
        settingsPanel.setVisible(false);
        generarReportePanel.setVisible(false);
        guardarPanel.setVisible(false);
    }

    //metodo para el boton de continuar
    public void onSaveContinueButtonClicked(MouseEvent event){
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        settingsPanel.setVisible(false);
        generarReportePanel.setVisible(false);
        guardarPanel.setVisible(false);
        this.dataBaseTextField1.setText("");
        this.dataBaseTextField2.setText("");
        this.reportTextField1.setText("");
        this.reportFileTextField2.setText("");
        fileReportPanel.setVisible(false);
        listEditPanel.setVisible(false);
        CollectionData collectionData = new CollectionData();
        String firtsRowValue = firtsRowTextField.getText();
        String idColumValue = idColumTextField.getText();
        String nameColumValue = nameColumTextField.getText();
        String groupColumValue = groupColumTextField.getText();
        collectionData.IDsColumn = Integer.parseInt(idColumValue);
        collectionData.namesColumn = Integer.parseInt(nameColumValue);
        collectionData.groupColumn= Integer.parseInt(groupColumValue);
        collectionData.dataStartRow = Integer.parseInt(firtsRowValue);
        AttendantsList atgroup = ExcelReader.GenerateAttendantsGroupFile("C:/Users/Ana Karen Hernandez/Desktop/Prueba.xlsx", collectionData);
        if(atgroup == null) // Si no se generó la lista, retorna null
        {
            System.out.println("Lista de asistentes no generada " );
            return;
        }
        resumePanel.setVisible(true);
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
        generarReportePanel.setVisible(false);
        guardarPanel.setVisible(false);
    }

    EventHandler<KeyEvent> handlerNumbers = new EventHandler<KeyEvent>(){
        private boolean willConsume = false;

        @Override
        public void handle(KeyEvent event) {
            JFXTextField temp = (JFXTextField) event.getSource();
            if(willConsume){
                event.consume();
            }

            if(!event.getText().matches("[0-9]") && event.getCode() != KeyCode.BACK_SPACE) {
                if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                    willConsume = true;
                } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                    willConsume = false;
                }
            }
        }
    };


}