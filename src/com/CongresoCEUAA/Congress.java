package com.CongresoCEUAA;

import com.CongresoCEUAA.AttendaceSystem.AttendantsList;
import com.CongresoCEUAA.AttendaceSystem.Event;
import org.apache.poi.ss.formula.functions.Even;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
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

   public Event AddEvent(String name, LocalDate date, LocalTime time)
   {

       byte newIndex = currentEventIndex;
       currentEventIndex++;

       Event event = new Event(newIndex, name, time, date);
       events.put(newIndex, event);


       return event;
   }

   public boolean RemoveEvent(String name)
   {
       for(Map.Entry<Byte, Event> eventEntry: EventsSet())
       {
            if(eventEntry.getValue().getName() == name)
            {
                events.remove(eventEntry.getKey());
                System.out.println("Deleted: " + name);
                return true;
            }
       }
       return false;
   }

   public Collection<Event> GetAllEvents()
   {
      return events.values();
   }

   public String[] GetAllEventNames()
   {
      Collection<Event> allEvents = GetAllEvents();
      String names[] = new String[allEvents.size()];

      int i=0;
      for (Event event: allEvents)
      {
         names[i++] = event.getName();
      }

      return names;
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
