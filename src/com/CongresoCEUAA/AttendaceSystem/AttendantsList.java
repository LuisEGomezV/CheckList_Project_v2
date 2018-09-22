package com.CongresoCEUAA.AttendaceSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AttendantsList implements java.io.Serializable
{
    Map<Integer, Attendant> attendants;

    Map<Byte, String> groups;

    byte currentGroupID = 0;

    public void AddAttendant(int id, String name, String group)
    {
        try
        {
            if(attendants.containsKey(id))
            {
                System.out.println("Duplicated ID for:\n" + id +" " + name);
                return;
            }

            Byte groupId = AddGroup(group);

            attendants.put(id, new Attendant(id, name, groupId));

        }catch (Exception e)
        {

        }
    }

    public int Count()
    {
        return attendants.size();
    }

    public Attendant GetAttendant(Integer id)
    {
        return attendants.get(id);
    }

    Byte AddGroup(String groupName) //Optimizar
    {
        try
        {
            for(Map.Entry<Byte, String> group: groups.entrySet())
            {
                if(group.getValue() == groupName)
                    return group.getKey();
            }

            byte newID = currentGroupID;
            groups.put(newID, groupName);
            currentGroupID++;
            return newID;
        }
        catch (Exception e)
        {
            System.out.println("Error adding group \n");
            return null;
        }
    }

    public String GetGroup(byte index)
    {
        return groups.get(index);
    }

    public Set<Map.Entry<Integer,Attendant>> AttendantsSet()
    {
        return attendants.entrySet();
    }

    public AttendantsList(int initialCapacity)
    {
        attendants = new TreeMap();
        groups = new HashMap();
    }

}
