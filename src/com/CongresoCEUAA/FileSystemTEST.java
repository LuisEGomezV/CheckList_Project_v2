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

    public static void mainTest()
    {

        CollectionData collectionData = new CollectionData();
        collectionData.collectionType = CollectionType.AllSheets;
        collectionData.IDsColumn = 0;
        collectionData.namesColumn = 1;
        collectionData.careerColumn= 3;
        collectionData.groupColumn= 3;
        collectionData.dataStartRow = 2;


        AttendantsList atgroup = ExcelReader.GenerateAttendantsGroupFile("/Users/luisgomez/Desktop/Congreso/TestList.ghkh", collectionData); //Se corrige la extension automaticamente


        System.out.println("Attendants: " + atgroup.Count());


        Congress congress = new Congress(atgroup);

        Event event1  = congress.AddEvent("Conferencia 1");
        Event event2  = congress.AddEvent("Conferencia 2");

        atgroup.GetAttendant(239935).AddAttendance(event1);
        atgroup.GetAttendant(234606).AddAttendance(event1);
        atgroup.GetAttendant(239935).AddAttendance(event2);
        atgroup.GetAttendant(234606).AddAttendance(event2);

        SessionFileSystem.SaveSession(congress,"/Users/LuisGomez/Desktop/Congreso2018.con");


        Congress loadedCongress = SessionFileSystem.LoadSession("/Users/LuisGomez/Desktop/Congreso2018.con");

        ReportData reportData = new ReportData();
        reportData.sheetAsGroup = false;
        reportData.IDsColumn = 0;
        reportData.namesColumn = 1;
        reportData.dataStartRow = 2;
        reportData.skipWhenZeroAttendance = true;


        ExcelWriter.GenerateReport("/Users/luisgomez/Desktop/Congreso/TestReport.xlsx",congress,reportData);







    }
}
