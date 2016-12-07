import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DateTimeException;
import java.util.logging.*;
import java.io.*;

/**
 * Provides date utility methods for converting dates from string to 
 * Java 8 date formats, and vice versa.
 * 
 * @author Gurdev Singh
 * @version 0.3
 */
public class DateUtility 
 {
    //static PrintWriter pw ;
    public static LocalDate convertDate(String aDate) 
    {
        return null;
    }
    
   
    public static LocalTime convertTime(String aTime) 
    {
        return null;
    }
    
    /**
    * Retrieves localdatetime from user
    * @param String localDateTime from user
    * @returns localdatetime object retrieved
    *  
    */
    public static LocalDateTime getLocalDateTime(String aDateTime)
    {
        try
        {
      // pw = new PrintWriter(new FileWriter("logs.txt",true));
    
        String str = aDateTime;//"1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
        }
        catch(DateTimeException dte)
        {
            KeyBoard.println("Error:Incorrect date and time");
            
          //  pw.println("asdasd");
        }
        catch(Exception e){}
        return LocalDateTime.now();
        
    }
    /**
     * Receives times a LocalTime object, converting to string 
     * 
     * @param       aTime   a time to convert
     * @return      time as formatted string
     */     
    public static String timeToString(LocalTime aTime) 
    {
        return "Not coded yet";
    }
        
}
