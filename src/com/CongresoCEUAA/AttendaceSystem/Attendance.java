package com.CongresoCEUAA.AttendaceSystem;

public class Attendance implements java.io.Serializable
{
    private byte relatedEvent;//Evento relacionado

    //Fecha y hora

    //Otros parametros

    public Attendance(byte eventIndex)
    {
        relatedEvent = eventIndex;
    }

    public byte getRelatedEvent() {
        return relatedEvent;
    }
}
