package sample;
     import com.CongresoCEUAA.AttendaceSystem.AttendantsList;
import com.CongresoCEUAA.Congress;
import com.CongresoCEUAA.FileSystem.CollectionType;
import com.CongresoCEUAA.FileSystem.ExcelReader;
import com.CongresoCEUAA.FileSystem.ExcelWriter;
import com.CongresoCEUAA.FileSystem.FilesData.CollectionData;
import com.CongresoCEUAA.FileSystem.FilesData.FileData;
import com.CongresoCEUAA.FileSystem.FilesData.ReportData;
import com.CongresoCEUAA.FileSystem.SessionFileSystem;
import com.CongresoCEUAA.FileSystemTEST;
import com.CongresoCEUAA.SessionManager;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
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

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

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
    @FXML private JFXTextField nameCongressTextField;
    @FXML private JFXTextField conferenceListTextField;
    @FXML private JFXTextField conferenceListSettingsTextField;
    @FXML private JFXTextField idTextField;
    @FXML private JFXTextField firtsRowTextField;
    @FXML private JFXTextField idColumTextField;
    @FXML private JFXTextField nameColumTextField;
    @FXML private JFXTextField groupColumTextField;
    @FXML private JFXComboBox<String> comoBoxCheckList;
    @FXML private JFXComboBox<String> comboBoxSettings;
    @FXML private Label groupLabel;
    @FXML private Label groupColumGenerateReportLabel;
    @FXML private Label errorLabel;
    @FXML private JFXToggleButton toggleSheetsGroups;
    @FXML private JFXToggleButton toggleSheetsGroupsGenerateReport;
    @FXML private JFXToggleButton toggleSkipAttendance;

    //Text fields de la ventana generar reporte
    @FXML private JFXTextField firtsRowGenerateReportTextField;
    @FXML private JFXTextField idColumGenerateReportTextField;
    @FXML private JFXTextField nameColumGenerateReportTextField;
    @FXML private JFXTextField groupColumGenerateReportTextField;
    @FXML private JFXTextField reportTextField;
    @FXML private JFXTextField nameCongressGenerateReportTextField;


    String dataBasePath = new String();
    String reportPath = new String();
    String conPath = new String();
    String idText = new String();//cadena que contiene el id al tomar lista
    //String reportPath = new String();
    int i;
    boolean toogleSheetsGroups = false;
    boolean skipAttendance = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firtsRowTextField.addEventFilter(KeyEvent.ANY, handlerNumbers);
        idColumTextField.addEventFilter(KeyEvent.ANY, handlerNumbers);
        nameColumTextField.addEventFilter(KeyEvent.ANY, handlerNumbers);
        groupColumTextField.addEventFilter(KeyEvent.ANY, handlerNumbers);
        dataBaseTextField1.setDisable(true);

        firtsRowTextField.setText("1");
        idColumTextField.setText("0");
        nameColumTextField.setText("1");
        groupColumTextField.setText("2");

        toggleSheetsGroups.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(toggleSheetsGroups.isSelected() == true ){
                    groupLabel.setVisible(false);
                    groupColumTextField.setVisible(false);
                    toogleSheetsGroups = true;
                }
                else{
                    groupLabel.setVisible(true);
                    groupColumTextField.setVisible(true);
                    toogleSheetsGroups = false;
                }
                System.out.println("toggle: " + toogleSheetsGroups);
            }
        });

        toggleSheetsGroupsGenerateReport.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(toggleSheetsGroupsGenerateReport.isSelected() == true){
                    groupColumGenerateReportLabel.setVisible(false);
                    groupColumGenerateReportTextField.setVisible(false);
                    toogleSheetsGroups = true;
                }
                else{
                    groupColumGenerateReportLabel.setVisible(true);
                    groupColumGenerateReportTextField.setVisible(true);
                    toogleSheetsGroups = false;
                }
            }
        });

        toggleSkipAttendance.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(toogleSheetsGroups == true){
                    skipAttendance = true;
                }
                else{
                    skipAttendance = false;
                }
            }
        });
    }




    public void onEnterPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.TAB) {
            idText = idTextField.getText();
            this.idTextField.setText(null);
        }
    }

    public void onSaveContinueGenerateReportButtonClicked(MouseEvent event)
    {
        Congress currentCon = GetCurrentCongress();
        if(currentCon == null)
            return;

        //String path = "/Users/luisgomez/Desktop/";
        String path = reportPath;
        String name = currentCon.getCongressName() + ".con";

        ReportData data = GetRWData(ReportData.class, firtsRowGenerateReportTextField.getText(), idColumGenerateReportTextField.getText(),
                nameColumGenerateReportTextField.getText(), groupColumGenerateReportTextField.getText(), toogleSheetsGroups);

        data.skipWhenZeroAttendance = skipAttendance;
        System.out.println("sheetAsGroup: " + data.sheetAsGroup);

        ExcelWriter.GenerateReport(path+name,currentCon,data);
    }

    public void onSearchPathReportClicked(MouseEvent event){
        JFileChooser searchReportPath = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("con files","con");
        searchReportPath.setFileFilter(filter);
        searchReportPath.showOpenDialog(null);
        reportPath = searchReportPath.getSelectedFile().getPath();//String que contiene la ruta donde se guarda la base de datos
        this.reportTextField.setText(reportPath);
        generarReportePanel.setVisible(false);
        guardarPanel.setVisible(false);
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(true);
        settingsPanel.setVisible(false);
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

        firtsRowGenerateReportTextField.setText("1");
        idColumGenerateReportTextField.setText("0");
        nameColumGenerateReportTextField.setText("1");
        groupColumGenerateReportTextField.setText("2");


        // reportTextField;
       //nameCongressGenerateReportTextField;

    }

    Congress GetCurrentCongress()
    {
        try
        {
            /*if(FileSystemTEST.currentCongress == null)
                System.out.println("No current congress");

            return FileSystemTEST.currentCongress;*/

            return SessionManager.getCurrentSession();
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

    void AddAttendance()
    {
        String idString = comoBoxCheckList.getValue();

        Integer id;
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
        JFileChooser openDataBase = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files","xlsx");
        openDataBase.setFileFilter(filter);
        openDataBase.showOpenDialog(null);
        dataBasePath = openDataBase.getSelectedFile().getPath();//String que contiene la ruta donde se guarda la base de datos
        this.dataBaseTextField1.setText(dataBasePath);
        /*
        try {
            BufferedWriter salida1 = new BufferedWriter(new FileWriter(file1));
        }catch(Exception e) {

        }
        */
    }

    //metodo para el boton de nuevo
    public void onNewButtonCliked(MouseEvent event){
        i = 0;
        startPanel.setVisible(false);
        newEntryPanel.setVisible(true);
        resumePanel.setVisible(false);
        settingsPanel.setVisible(false);
        dataBaseTextField1.setDisable(true);
        generarReportePanel.setVisible(false);
        guardarPanel.setVisible(false);
        this.dataBaseTextField1.setText("");
        this.nameCongressTextField.setText("");
        UpdateComboBoxes();
    }

    //metodo para el boton de resumir
    public void onResumeButtonCliked(MouseEvent event){
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        settingsPanel.setVisible(false);
        generarReportePanel.setVisible(false);
        guardarPanel.setVisible(false);
        JFileChooser selectCon = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("con files","con");
        selectCon.setFileFilter(filter);
        selectCon.showOpenDialog(null);
        conPath = selectCon.getSelectedFile().getPath();//String que contiene la ruta donde se guarda la base de datos
        this.reportTextField.setText(conPath);
        resumePanel.setVisible(true);
    }

    //metodo para el boton de continuar
    public void onSaveContinueButtonClicked(MouseEvent event){
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        settingsPanel.setVisible(false);
        generarReportePanel.setVisible(false);
        guardarPanel.setVisible(false);
        this.dataBaseTextField1.setText("");
        this.nameCongressTextField.setText("");
        //fileReportPanel.setVisible(false);
        listEditPanel.setVisible(false);


        String firtsRowValue = firtsRowTextField.getText();
        String idColumValue = idColumTextField.getText();
        String nameColumValue = nameColumTextField.getText();
        String groupColumValue = groupColumTextField.getText();

        System.out.println("Group Column text: " + groupColumValue);


        CollectionData data = GetRWData(CollectionData.class, firtsRowValue, idColumValue,
                nameColumValue, groupColumValue, toogleSheetsGroups);

        data.sheetAsGroup = false;
        data.collectionType = CollectionType.AllSheets;
        //data.groupColumn = 3;

        /*System.out.println("start: " + data.dataStartRow);
        System.out.println("ids: " + data.IDsColumn);
        System.out.println("names: " + data.namesColumn);
        System.out.println("groups: " + data.groupColumn);*/

        //String path = "/Users/luisgomez/Desktop/Congreso/TestLi.xlsx";
        String path = dataBasePath;

        boolean exist = ExcelReader.ExistExcel(path);
        System.out.println("exist: " + exist);

        if(!exist)
        {
            errorLabel.setVisible(true);
            //Mensaje de que no existe el archivo
            //return;
        }
        else{
            AttendantsList atgroup = ExcelReader.GenerateAttendantsGroupFile(path, data);
            if(atgroup == null) // Si no se generó la lista, retorna null
            {
                System.out.println("Lista de asistentes no generada " );
                return;
            }

            System.out.println("Attendants: " + atgroup.Count());

            SessionManager.NewCongress(atgroup);

            resumePanel.setVisible(true);
        }

    }

    private <T extends FileData> T GetRWData(Class clazz, String firstRow, String IDCol, String nameCol, String groupCol, boolean sheetASGroup)
    {
        try
        {
            T data = (T)clazz.newInstance();

            data.dataStartRow = Integer.parseInt(firstRow);
            data.IDsColumn = Integer.parseInt(IDCol);
            data.namesColumn = Integer.parseInt(nameCol);
            data.groupColumn= Integer.parseInt(groupCol);
            data.sheetAsGroup = sheetASGroup;

            return data;
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void onHomeButtonClicked(MouseEvent event){
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(false);
        startPanel.setVisible(true);
        settingsPanel.setVisible(false);
        fileReportPanel.setVisible(false);
        listEditPanel.setVisible(false);
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