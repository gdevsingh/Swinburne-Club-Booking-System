import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Responsible for starting the SBS program
 * 
 * @author Gurdev Singh 
 * @version 0.2
 */

public class Start 
{
     static Club swinClub ;

    public static void main(String[] args) 
    {
        try
        {
            swinClub = new Club("Swinburne Country Club");
         
            swinClub.readMembers();
            swinClub.readSports();
            swinClub.readBookings();
            
           
            int choice = KeyBoard.getInteger("Choose any 1\n1)GUI\n2)CUI\nEnter Choice:");
            //while(true)
            {
                switch(choice)
                {
                    case 1: startGUI(swinClub);break;
                    case 2: startUI(swinClub);break;
                    default: swinClub.writeBookingsToFile();System.exit(0);
                }
            } 
        
        }
        catch(Exception e)
        {
            try
            {
                FileUtility.errorLogger("Error:"+e.getMessage());
            }
            catch(Exception files)
            {
        
            }
        System.out.println("Error:"+e.getMessage());
        }
    }
    
    public static void startUI(Club swinClub)
    {
        UserInterface consoleApp = new UserInterface(swinClub);
        consoleApp.run();
    }
    
    public static void startGUI(Club swinClub)
    {
       try
       {
           
           
          GUIClub gui = new GUIClub(swinClub);
           swinClub.addObserver(gui);
          // System.exit(0); 
           gui.run();
          
       }
       catch(Exception e)
       {
           System.out.println("Error:"+e.getMessage());
       }
    }

}
