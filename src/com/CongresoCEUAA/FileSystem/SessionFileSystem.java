package com.CongresoCEUAA.FileSystem;

import com.CongresoCEUAA.Congress;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class SessionFileSystem
{

    public static void SaveSession(Congress session, String path)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(session);
            out.close();
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    public static Congress LoadSession(String path)
    {
        try
        {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Congress session;
            session = (Congress) in.readObject();
            in.close();
            return  session;
        }
        catch (IOException i)
        {
            System.out.println("Attendant file not found");
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c)
        {
            System.out.println("Attendant not found");
            c.printStackTrace();
            return null;
        }

    }



}
