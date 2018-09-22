package com.CongresoCEUAA.FileSystem.FilesData;

public class ReportData extends FileData
{

    public int titlesRow = 0;
    public int startAttendanceColumn = 4;
    public boolean skipWhenZeroAttendance = true;

    public ReportData()
    {
        sheetAsGroup = true;
    }
}
