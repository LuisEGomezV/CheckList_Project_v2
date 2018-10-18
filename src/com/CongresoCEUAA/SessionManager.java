package com.CongresoCEUAA;

import com.CongresoCEUAA.AttendaceSystem.AttendantsList;

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
    }

    public static Congress getCurrentSession() {
        return currentSession;
    }
}





    //Toda la logica del pase de lista va aqui



