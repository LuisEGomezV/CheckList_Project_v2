package com.CongresoCEUAA;

import com.CongresoCEUAA.AttendaceSystem.AttendantsList;
import com.CongresoCEUAA.AttendaceSystem.Event;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Congress implements java.io.Serializable
{

   private String congressName;

   private AttendantsList attendants;


   private TreeMap<Byte, Event> events;
   private byte currentEventIndex;

   public String getCongressName() {
      return congressName;
   }

   public void setCongressName(String congressName) {
      this.congressName = congressName;
   }


   public AttendantsList getAttendantsList() {
      return attendants;
   }

   public void setAttendants(AttendantsList attendants) {
      this.attendants = attendants;
   }

   public Event AddEvent(String name /*Parametros de los eventos*/)
   {
       byte newIndex = currentEventIndex;
       currentEventIndex++;

       Event event = new Event(newIndex, name);
       events.put(newIndex, event);

       return event;
   }

   public void RemoveEvent()
   {

   }

   public Set<Map.Entry<Byte,Event>> EventsSet()
   {
      return events.entrySet();
   }

   public int EventsNumber()
   {
      return events.size();
   }

   public Event GetEvent(Byte index)
   {
       return events.get(index);
   }

   public Congress()
   {
       events = new TreeMap();
   }

   public Congress(AttendantsList attendants)
   {
      this.attendants = attendants;
      events = new TreeMap();
   }
}
