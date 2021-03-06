
import java.util.*;
import java.io.*;
import java.time.*;
/**
 * Responsible for reading data from user interface and printing data back to user
 * 
 * @author Gurdev Singh 
 * @version 0.2
 */

public class KeyBoard
{
    private static Scanner in = new Scanner(System.in);
    
    /**
    * accepts string from user
    * @param string accepted form the user
    */
    public static String getString(String prompt)
    {
        System.out.print(prompt + " " );
        return in.nextLine();
    }
    
    /**
    * accepts double from user
    * @param double accepted form the user
    */
    public static Double getDouble(String prompt)
    {
        Double d = 0.00;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                d = Double.parseDouble(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Double");
            }
        }
        return d;
    }
    
    /**
    * accepts integer from user
    * @param integer accepted form the user
    */
    public static Integer getInteger(String prompt)
    {
        Integer i = 0;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Integer");
            }
        }
        return i;
    }
    
    /**
    * Displays the string specified
    * 
    */
    public static void println(String toPrint)
    {
        System.out.println(toPrint);
    }
    
    /**
    * accepts Integer from user
    * @param integer accepted form the user
    */
    public static Integer getInteger()
    {
        Integer i = 0;
        while (true)
        {
            try
            {
                System.out.print("Please enter an integer");
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid integer");
            }
        }
        return i;
    }
    
    /**
    * Displays the string specified
    * 
    */
    public static void print(String toPrint)
    {
        System.out.print(toPrint);
    }
    
    
}
