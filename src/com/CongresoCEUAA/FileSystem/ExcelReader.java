package com.CongresoCEUAA.FileSystem;

import com.CongresoCEUAA.AttendaceSystem.AttendantsList;

import com.CongresoCEUAA.FileSystem.FilesData.CollectionData;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader extends ExcelFileSystem
{
    public static AttendantsList GenerateAttendantsGroupFile(String path, CollectionData collectionData)
    {
        FileInputStream file = null;
        AttendantsList attendants = null;
        try
        {
            path = VerifyPath(path);

            file = new FileInputStream(path);
            XSSFWorkbook workBook = new XSSFWorkbook(file);

            Integer potentialEntries = GetPotentialEntries(workBook, collectionData);
            System.out.println("SheetsNumber: " + workBook.getNumberOfSheets());
            System.out.println("Potential entries: " + potentialEntries);

            attendants = new AttendantsList(potentialEntries);

            if(collectionData.dataStartRow < 0)
                collectionData.dataStartRow = 0;

            FillAttendanceGroup(attendants, workBook, collectionData);

            //Congress eventSession = new Congress(potentialEntries);

            /*HSSFSheet sheet = workBook.getSheetAt(0);


            System.out.println("Rows: " +  sheet.getPhysicalNumberOfRows());*/

        }
        catch (FileNotFoundException i)
        {
            System.out.println("Archivo no encontrado");
        }
        catch (IOException i)
        {
            i.printStackTrace();

        }
        catch (Exception i)
        {
            i.printStackTrace();

        }
        finally
        {
            if(file != null)
            {
                try{file.close();}
                catch(IOException i){i.printStackTrace();}
            }
        }


        return attendants;
    }

    static boolean AddStudentFromRow(XSSFRow row, AttendantsList attendants, CollectionData collectionData)
    {
        Integer id = ExtractNumber(row, collectionData.IDsColumn);
        String name = ExtractString(row, collectionData.namesColumn);
        String group = ExtractString(row, collectionData.groupColumn);

        if(name == null || name == "" || group == null || group == "" || id == null || id == 0)
            return false;

        attendants.AddAttendant(id, name, group);
        //System.out.println("ID: " + id + "  Name: " + name);
        return true;
    }

    static String ExtractString(XSSFRow row, int column)
    {
        try
        {

            return row.getCell(column).getStringCellValue();
        }
        catch (IllegalStateException e)
        {
            double val = row.getCell(column).getNumericCellValue();
            return Double.toString(val);
        }
    }

    static Integer ExtractNumber(XSSFRow row, int column)
    {
        try
        {
            return (int)row.getCell(column).getNumericCellValue();
        }
        catch (IllegalStateException e)
        {

        }

        try
        {
            String val = row.getCell(column).getStringCellValue();
            int numVal = (int)Double.parseDouble(val);
            return  numVal;
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error generating student: invalid ID value type: ");
            return null;
        }

    }

    static int GetPotentialEntries(XSSFWorkbook workBook, CollectionData collectionData)
    {
        int potentialEntries = 0;

        if(collectionData.collectionType == CollectionType.AllSheets)
        {
            for(int i = workBook.getNumberOfSheets()-1; i>=0; i--)
            {
                XSSFSheet sheet = workBook.getSheetAt(i);
                System.out.println("Sheet: " + i +" - Rows: " + sheet.getPhysicalNumberOfRows());
                potentialEntries += sheet.getPhysicalNumberOfRows();
            }
            return potentialEntries;
        }

        if(collectionData.collectionType == CollectionType.SelectedSheets)
        {
            for(Integer sheetIndex: collectionData.selectedSheets)
            {
                XSSFSheet sheet = workBook.getSheetAt(sheetIndex);
                potentialEntries += sheet.getPhysicalNumberOfRows();
            }

            return potentialEntries;
        }

        return -1;
    }

    static void FillAttendanceGroup(AttendantsList attendantsList, XSSFWorkbook workBook, CollectionData collectionData)
    {
        if(collectionData.collectionType == CollectionType.AllSheets)
        {
            for(int i = workBook.getNumberOfSheets()-1; i>=0; i--)
            {
                XSSFSheet sheet = workBook.getSheetAt(i);

                int rows = sheet.getPhysicalNumberOfRows();

                for(int r = collectionData.dataStartRow; r < rows; r++)
                {
                    try
                    {
                        AddStudentFromRow(sheet.getRow(r),attendantsList,collectionData);


                    }catch (NullPointerException np)
                    {
                        System.out.println("\nError adding attendant\nSheet: " + sheet.getSheetName() + " - Row: " + r + "\n");
                    }

                }

            }
            return;
        }

        if(collectionData.collectionType == CollectionType.SelectedSheets)
        {
            for(Integer sheetIndex: collectionData.selectedSheets)
            {
                XSSFSheet sheet = workBook.getSheetAt(sheetIndex);

                int rows = sheet.getPhysicalNumberOfRows();

                for(int r = collectionData.dataStartRow; r < rows; r++)
                {
                    AddStudentFromRow(sheet.getRow(r),attendantsList,collectionData);
                }

            }

            return;
        }
    }






}
