package com.CongresoCEUAA.AttendaceSystem;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event implements java.io.Serializable
{
    private String name;
    private Byte index;
    private LocalDate date;
    private LocalTime time;
    // LISTA DE BYTES PARA GRUPOS
    public LocalDate getDate () { return date;}

    public LocalTime getTime () { return time;}

    public void setDate (LocalDate date) { this.date =date;}

    public void setTime (LocalTime time) { this.time =time;}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getIndex() {
        return index;
    }


    public Event(Byte index, String name, LocalTime time, LocalDate date)
    {
        this.index = index;
        this.name = name;
        this.time = time;
        this.date = date;
    }


}
