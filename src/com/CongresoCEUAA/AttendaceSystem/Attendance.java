package com.CongresoCEUAA.AttendaceSystem;


import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance implements java.io.Serializable {
    private byte relatedEvent;//Evento relacionado

    //Fecha y hora
    private LocalDate date;
    private LocalTime time;

    public LocalDate getDate () { return date;}

    public LocalTime getTime () { return time;}

    //Otros parametros

    //GRUPO

    public Attendance(byte eventIndex)
    {

        relatedEvent = eventIndex;
        //Se obtiene la fecha y hora actual
        date = java.time.LocalDate.now();
        time = java.time.LocalTime.now();

    }

    public byte getRelatedEvent() {
        return relatedEvent;
    }
}
