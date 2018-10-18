package com.CongresoCEUAA;

import com.CongresoCEUAA.AttendaceSystem.AttendantsList;
import com.CongresoCEUAA.AttendaceSystem.Event;

public class SessionManager
{
    //"Congreso" actual

    private static Congress currentSession;



    public static void NewCongress (AttendantsList Lista)
    {
        if(Lista == null)
        {
            System.out.println("Null attendantsList");
            return;
        }

        currentSession = new Congress (Lista);



        //Se agregan eventos (conferencias, talleres, etc.)
        Event event1  = currentSession.AddEvent("Conferencia 1", null, null);
        Event event2  = currentSession.AddEvent("Conferencia 2", null, null);


        //Se agrega asistencia por medio de ID y con el evento como parametro
        Lista.GetAttendant(239935).AddAttendance(event1);
        Lista.GetAttendant(234606).AddAttendance(event1);

        Lista.GetAttendant(239935).AddAttendance(event2);
        Lista.GetAttendant(234606).AddAttendance(event2);
    }

    public static Congress getCurrentSession() {
        return currentSession;
    }
}





    //Toda la logica del pase de lista va aqui



