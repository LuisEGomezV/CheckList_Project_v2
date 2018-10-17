package com.CongresoCEUAA.AttendaceSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class Attendant implements java.io.Serializable
{
    private Integer id;
    private String name;


    private byte groupID;
    private List<Attendance> attendanceList;


    public byte getGroupID() {
        return groupID;
    }



    public boolean AddAttendance(Event event)
    {

        for(Attendance att: attendanceList)
        {
            //Se verifica el pase de lista, para no repetir y para comprobar una tolerancia de + - 15 mins
            if(att.getRelatedEvent() == event.getIndex())
                return false;
            if (event.getDate() != att.getDate())
                return false;
            if (att.getTime().isAfter(event.getTime().plusMinutes(15)) || att.getTime().isBefore(event.getTime().minusMinutes(15)))
                return false;
        }





        Attendance attendance = new Attendance(event.getIndex());

        attendanceList.add(attendance);
        return true;

    }

    public ListIterator<Attendance> GetAttendance()
    {
        return attendanceList.listIterator();
    }

    public int AttendanceCount()
    {
        return attendanceList != null? attendanceList.size() : 0;
    }

    public String ToString()
    {
        return id + ": " + name + ".Grupo " + groupID;
    }


    public Attendant(int id, String name, byte groupID)
    {
        attendanceList = new ArrayList();
        this.id = id;
        this.name = name;
        this.groupID = groupID;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
