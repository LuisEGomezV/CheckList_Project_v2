package com.CongresoCEUAA;

import com.CongresoCEUAA.AttendaceSystem.AttendantsList;

public class SessionManager
{
    //"Congreso" actual

    Congress currentSession;

    public void NewCongress (AttendantsList Lista)
    {
        currentSession = new Congress (Lista);
    }
}





    //Toda la logica del pase de lista va aqui



