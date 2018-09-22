package com.CongresoCEUAA.AttendaceSystem;

public class Event implements java.io.Serializable
{
    private String name;
    private Byte index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getIndex() {
        return index;
    }


    public Event(Byte index, String name)
    {
        this.index = index;
        this.name = name;
    }

    //Fechas y horas, etc
}
