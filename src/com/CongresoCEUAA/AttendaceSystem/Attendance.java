package com.CongresoCEUAA.AttendaceSystem;

public class Attendance implements java.io.Serializable
{
    //Fecha

    private byte relatedEvent;//Evento relacionado
    //Otras cosas

    public Attendance(byte eventIndex)
    {
        relatedEvent = eventIndex;
    }

    public byte getRelatedEvent() {
        return relatedEvent;
    }
}
