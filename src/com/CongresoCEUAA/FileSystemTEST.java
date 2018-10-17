package com.CongresoCEUAA;

import com.CongresoCEUAA.AttendaceSystem.AttendantsList;
import com.CongresoCEUAA.AttendaceSystem.Event;
import com.CongresoCEUAA.FileSystem.CollectionType;
import com.CongresoCEUAA.FileSystem.ExcelReader;
import com.CongresoCEUAA.FileSystem.ExcelWriter;
import com.CongresoCEUAA.FileSystem.FilesData.CollectionData;
import com.CongresoCEUAA.FileSystem.FilesData.ReportData;
import com.CongresoCEUAA.FileSystem.SessionFileSystem;

import java.io.IOException;

public class FileSystemTEST
{
    public static Congress currentCongress;

    public static void mainTest()
    {
        //TUTORIAL

        //Ejemplo de Collection data para especificar como se deben leer los datos de excel

        CollectionData collectionData = new CollectionData();
        collectionData.collectionType = CollectionType.AllSheets;
        collectionData.IDsColumn = 0;
        collectionData.namesColumn = 1;
        collectionData.careerColumn= 3;
        collectionData.groupColumn= 3;
        collectionData.dataStartRow = 2;


        //Ejemplo de lectura de excel para llenar la lista de asistentes
        AttendantsList atgroup = ExcelReader.GenerateAttendantsGroupFile("C:/Users/Ana Karen Hernandez/Desktop/TestList.xlsx", collectionData); //Se corrige la extension automaticamente
        //AttendantsList atgroup = ExcelReader.GenerateAttendantsGroupFile("/Users/luisgomez/Desktop/Congreso/TestList.xlsx", collectionData); //Se corrige la extension automaticamente

        if(atgroup == null) // Si no se generó la lista, retorna null
        {
            System.out.println("Lista de asistentes no generada " );
            return;
        }


        System.out.println("Attendants: " + atgroup.Count());


        //Se crea un "congreso" con la lista de asistentes
        Congress congress = new Congress(atgroup);

        //Se agregan eventos (conferencias, talleres, etc.)
        Event event1  = congress.AddEvent("Conferencia 1");
        Event event2  = congress.AddEvent("Conferencia 2");


        currentCongress = congress;
        System.out.println("casdfsdf");

        //Se agrega asistencia por medio de ID y con el evento como parametro
        atgroup.GetAttendant(239935).AddAttendance(event1);
        atgroup.GetAttendant(234606).AddAttendance(event1);

        atgroup.GetAttendant(239935).AddAttendance(event2);
        atgroup.GetAttendant(234606).AddAttendance(event2);


        //Se guarda el archivo de congreso para reanudarlo posteriormente
        SessionFileSystem.SaveSession(congress,"C:/Users/Ana Karen Hernandez/Desktop/Congreso2018.con");
        //SessionFileSystem.SaveSession(congress,"/Users/luisgomez/Desktop/Congreso2018.con");


        //Se carga el archivo de congreso
        Congress loadedCongress = SessionFileSystem.LoadSession("C:/Users/Ana Karen Hernandez/Desktop/Congreso2018.con");
        //Congress loadedCongress = SessionFileSystem.LoadSession("/Users/luisgomez/Desktop/Congreso2018.con");


        //Ejemplo de ReportData para especificar como se debe hacer el reporte
        ReportData reportData = new ReportData();
        reportData.sheetAsGroup = false;
        reportData.IDsColumn = 0;
        reportData.namesColumn = 1;
        reportData.dataStartRow = 2;
        reportData.skipWhenZeroAttendance = true;

        //Se genera el reporte
        //ExcelWriter.GenerateReport("/Users/luisgomez/Desktop/Congreso/TestReport.xlsx",congress,reportData);
        ExcelWriter.GenerateReport("C:/Users/Ana Karen Hernandez/Desktop/TestReport.xlsx",congress,reportData);







    }

}
