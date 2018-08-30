package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {
    //declaracion de los paneles
    @FXML private AnchorPane startPanel;
    @FXML private AnchorPane newEntryPanel;
    @FXML private AnchorPane resumePanel;
    @FXML private AnchorPane fileReportPanel;
    @FXML private AnchorPane listEditPanel;
    @FXML private AnchorPane settingsPanel;

    //metodo para cerrar la apicacion
    public void onExitButtonClicked(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }
    //metodo para el boton de nuevo
    public void onNewButtonCliked(MouseEvent event){
        startPanel.setVisible(false);
        newEntryPanel.setVisible(true);
        resumePanel.setVisible(false);
        settingsPanel.setVisible(false);
    }

    //metodo para el boton de resumir
    public void onResumeButtonCliked(MouseEvent event){
        startPanel.setVisible(false);
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(true);
        settingsPanel.setVisible(false);
    }

    //metodo para el boton de salvar y guardar
    public void onSaveContinueButtonClicked(MouseEvent event){
        startPanel.setVisible(true);
        newEntryPanel.setVisible(false);
        resumePanel.setVisible(true);
        settingsPanel.setVisible(false);
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
    }
}