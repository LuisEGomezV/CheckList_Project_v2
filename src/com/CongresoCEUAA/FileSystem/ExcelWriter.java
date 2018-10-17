package com.CongresoCEUAA.FileSystem;

import com.CongresoCEUAA.AttendaceSystem.Attendance;
import com.CongresoCEUAA.AttendaceSystem.Attendant;
import com.CongresoCEUAA.AttendaceSystem.AttendantsList;
import com.CongresoCEUAA.AttendaceSystem.Event;
import com.CongresoCEUAA.Congress;
import com.CongresoCEUAA.FileSystem.FilesData.ReportData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

public class ExcelWriter extends ExcelFileSystem
{

    public static void GenerateReport(String path, Congress congress, ReportData reportData)
    {

        FileOutputStream file = null;

        try
        {
            path = VerifyPath(path);
            file = new FileOutputStream(path);

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = null;

            HashMap<Byte, Integer> eventsColumns = GenerateEventsColumns(congress, reportData);

            if(!reportData.sheetAsGroup)
                sheet = CreateSheet(workbook,"Asistencia",congress,eventsColumns,reportData);//workbook.createSheet("Asistencia");


            for(Map.Entry<Integer, Attendant> attendant: congress.getAttendantsList().AttendantsSet())
            {
                if(reportData.skipWhenZeroAttendance && attendant.getValue().AttendanceCount() <= 0)
                    continue;

                if(reportData.sheetAsGroup)
                {
                    sheet = GetDesiredSheet(workbook, attendant.getValue(), congress, sheet, eventsColumns, reportData);
                }


                XSSFRow row = sheet.createRow(sheet.getLastRowNum()+1);
                WriteAttendance(row, attendant.getValue(), eventsColumns, reportData,congress.getAttendantsList());

            }


            for(int i = workbook.getNumberOfSheets()-1; i>=0; i--)
            {
                XSSFSheet cSheet = workbook.getSheetAt(i);

                cSheet.autoSizeColumn(reportData.IDsColumn);
                cSheet.autoSizeColumn(reportData.namesColumn);
                if(!reportData.sheetAsGroup)
                    cSheet.autoSizeColumn(reportData.groupColumn);

            }

            workbook.write(file);

        }
        catch (IOException i)
        {
            i.printStackTrace();
        }

    }

    static XSSFSheet GetDesiredSheet(XSSFWorkbook workbook, Attendant attendant, Congress congress, XSSFSheet currentSheet, HashMap<Byte, Integer> eventsColumns, ReportData reportData)
    {
        try
        {
            String group = congress.getAttendantsList().GetGroup(attendant.getGroupID());
            if(group == null)
            {
                System.out.println("Null group"); // Guardar en una hoja extra
                return  null;
            }


            if(currentSheet != null && currentSheet.getSheetName() == group)
                return currentSheet;


            XSSFSheet sheet = workbook.getSheet(group);

            if(sheet != null)
                return sheet;


            return CreateSheet(workbook, group, congress,eventsColumns, reportData);


        }catch (Exception e)
        {

        }
        return null;
    }

    static XSSFSheet CreateSheet(XSSFWorkbook workbook, String name, Congress congress ,HashMap<Byte, Integer> eventsColumns, ReportData reportData)
    {


        try
        {
            XSSFSheet sheet = workbook.createSheet(name);

            XSSFRow row = sheet.createRow(reportData.titlesRow);

            row.getCell(reportData.IDsColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("ID");
            row.getCell(reportData.namesColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("Nombre");
            if(!reportData.sheetAsGroup)
                row.getCell(reportData.groupColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue("Grupo");

            for(Map.Entry<Byte, Integer> eventEntry: eventsColumns.entrySet())
            {
                Event event = congress.GetEvent(eventEntry.getKey());
                row.getCell(eventEntry.getValue(), Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(event.getName());
            }

            return sheet;
        }
        catch (Exception e)
        {

        }

        return null;
    }

    static void WriteAttendance(XSSFRow row, Attendant attendant, HashMap<Byte, Integer> eventsColumns, ReportData reportData, AttendantsList attendantsList)
    {
        try
        {
            row.getCell(reportData.IDsColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(attendant.getId());
            row.getCell(reportData.namesColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(attendant.getName());

            if(!reportData.sheetAsGroup)
            {
                row.getCell(reportData.groupColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(attendantsList.GetGroup(attendant.getGroupID()));
            }

            ListIterator<Attendance> allAttendance = attendant.GetAttendance();

            while(allAttendance.hasNext())
            {
                Attendance att = allAttendance.next();
                int pos = eventsColumns.get(att.getRelatedEvent());

                XSSFCell cell = row.getCell(pos, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellValue("O");

            }

        }catch (Exception e)
        {
            System.out.println("Error writing attendance for:\n" + attendant.ToString());
        }
    }

    static HashMap<Byte, Integer> GenerateEventsColumns(Congress congress, ReportData reportData)
    {
        HashMap<Byte, Integer> eventsColumns =  new HashMap<>(congress.EventsNumber());

        int i = reportData.startAttendanceColumn;

        for(Map.Entry<Byte, Event> eventEntry: congress.EventsSet())
            eventsColumns.put(eventEntry.getKey(), i++);

        return eventsColumns;
    }

}
