import java.io.*;
import java.util.*;
import java.time.*;
/**
 * Responsible for reading and writing data to files
 * 
 * @author Gurdev Singh 
 * @version 0.2
 */
public class FileUtility
{
    static boolean printHeader = true;
    /**
     * writes data to a file
     * 
     * @param fileName name of the file to store data to 
     * @param data the actual spot data to write to the file
     * @throws IOException
     */
    
    public static void writeToFile(String fileName, ArrayList<String> data) 
    {
        try
        {
        String separator = "";
        String toPrint = "";
        String newLine = "";
        PrintWriter pw = new PrintWriter(new FileWriter(fileName,true));
        for(String str : data)
        {
            toPrint += (separator + str);
            separator = ",";
            
        }
        if(printHeader)
        {
            pw.println("//Swinburne Sports Booking System");
            pw.println("//Booking Date,Duration(mins),Booked by,Booked for");
            printHeader = false;
        }
        pw.println(toPrint);
        pw.close();
        }
        catch(FileNotFoundException fnfe)
        {
            KeyBoard.println("Fatal: File "+fileName+" not found");
            try
            {
                Thread.sleep(2000);
            }
            catch(Exception e){}
            System.exit(0);
        }
        catch(IOException ioe)
        {
            KeyBoard.println("Error:"+ioe.getMessage());
        }
    }
    
    
    /**
     * reads data from the file specified
     * 
     * @param fileName name of the file to retreive data from 
     * @returns an arraylist of type sting containing the data retrieved from the file 
     */
    public static ArrayList<String> readFromFile(String fileName)
    {
        try
       {
        ArrayList<String> data = new ArrayList<String>();
        
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        
        String temp = in.readLine();
        temp = in.readLine();
        temp = in.readLine();
        while (temp != null)
        {
            data.add(temp);
            temp = in.readLine();
        }
        in.close();
      //  PrintWriter writer = new PrintWriter(fileName);
      //  writer.print("");
      //  writer.close();
        return data;
       }
    catch(FileNotFoundException fnfe)
        {
            KeyBoard.println("Fatal: File "+fileName+" not found");
            try
            {
                Thread.sleep(2000);
            }
            catch(Exception e){}
                
            System.exit(0);
        }
    catch(Exception e)
    {
        KeyBoard.println("Error:"+e.getMessage());
    }
    return new ArrayList<String>();
   }
   
   /**
    * Clears the contents of the file specified
    * @param fileName name of the file to be cleared
    */
   public static void clearFile(String fileName)
   {
       try
       {
           PrintWriter writer = new PrintWriter(fileName);
           writer.print("");
           writer.close();
       }
       catch(FileNotFoundException fnfe)
       {
           KeyBoard.println("Error:"+fnfe.getMessage());
       }
   }
   /**
    * Logs Errors to file
    * @param fileName name of the file to be cleared
    */
   
   public static void errorLogger(String errorMessage) throws IOException
   {
       
        String toPrint = LocalDateTime.now()+" : "+errorMessage;
        File file =new File("logs.txt");
        if(!file.exists()){
    	 	file.createNewFile();
    	  }
        PrintWriter pw = new PrintWriter(new FileWriter(file,true));
        pw.println(toPrint+"\n");
        pw.close();
    
   }
}
