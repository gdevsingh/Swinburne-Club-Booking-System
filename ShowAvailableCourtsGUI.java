import java.awt.*;
import java.time.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.format.DateTimeParseException;
import java.io.*;
/**
 * This class is responsible for displaying separate window for accepting input for showing available courts.
 * 
 * @author Gurdev Singh 
 * @version 0.1
 */
public class ShowAvailableCourtsGUI extends JFrame
{
    // instance variables - replace the example below with your own
   // these need to be available to multiple methods
    private Club swinClub;
    private JPanel mainPanel;
    private JTextField tfNameOfSport;
    private JTextField tfTimeAndDate;
    private JTextField tfDuration;
    private JTextArea output;
   
    /**
     * initialises the whole ui for showing available courts    
     * @param club the swinclub object
     */
    public ShowAvailableCourtsGUI(Club club)
    {
        // initialise instance variables
      swinClub = club;
      this.setTitle("show available courts");
      this.setSize(400,300);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      mainPanel = new JPanel(new GridLayout(5,0,20,20));
      this.getContentPane().add(mainPanel);
      mainPanel.add(sportNumPanel());
      mainPanel.add(timeNumPanel());
      mainPanel.add(durationNumPanel());
      mainPanel.add(buttonPanel());
      mainPanel.add(outputPanel());
      
      this.setVisible(true);
    }

    /**
     * adds sport panel to UI
     * @returns JPanel to be added
     */
    public JPanel sportNumPanel()
    {
        JPanel jp = new JPanel(new GridLayout(1,2,5,5));
        JLabel jl = new JLabel("Name of Sport");
        jp.add(jl);
        tfNameOfSport = new JTextField(5);
        jp.add(tfNameOfSport);
        return jp;
    }
    
    /**
     * adds time panel to UI
     * @returns JPanel to be added
     */
    public JPanel timeNumPanel()
    {
        JPanel jp = new JPanel(new GridLayout(1,2,5,5));
        JLabel jl = new JLabel("Date & Time (yyyy-mm-dd hh:ss)");
        jp.add(jl);
        tfTimeAndDate= new JTextField(5);
        jp.add(tfTimeAndDate);
        return jp;
    }
    
    /**
     * adds duration panel to UI
     * @returns JPanel to be added
     */
    public JPanel durationNumPanel()
    {
        JPanel jp = new JPanel(new GridLayout(1,2,5,5));
        JLabel jl = new JLabel("Duration");
        jp.add(jl);
        tfDuration= new JTextField(5);
        jp.add(tfDuration);
        return jp;
    }
    
    /**
     * adds output panel to UI
     * @returns JPanel to be added
     */
    public JPanel outputPanel()
    {
        JPanel jp = new JPanel(new GridLayout(1,1,5,5));
        output = new JTextArea("");
        jp.add(output);
        
        return jp;
    }
    
    /**
     * adds button panel to UI
     * @returns JPanel to be added
     */
     public JPanel buttonPanel()
    {
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jb = new JButton("show available courts");
        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(showAvailableCourts())
                {
                    //jb.setText("add Booking");
                }
            }
        });
        
        jp.add(jb);
        JButton jc = new JButton("Cancel");
         jc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
               closeForm();
            }
        });
        jp.add(jc);
        
        return jp;
      }
   
      /**
       * displays available courts
       * @returns true if any available courts found
       */
       public boolean showAvailableCourts()
        {
        
            try
            {
                if (validateForm())
                {
                    try
                    {
                        String sport = tfNameOfSport.getText().trim();
                        if(swinClub.ifSportExists(tfNameOfSport.getText().trim()))
                        {

                            LocalDateTime aDateTime = DateUtility.getLocalDateTime(tfTimeAndDate.getText().trim());
                            if(aDateTime.isAfter(LocalDateTime.now()) && aDateTime.isBefore(LocalDateTime.now().plusDays(7)) && (aDateTime.getHour()>=8) && aDateTime.getHour()<(22))
                            {
                                int duration = Integer.parseInt(tfDuration.getText());
                                if(duration <= swinClub.findSport(sport).getDurationLimit())
                                {    
                   
                                    ArrayList<Court> availableCourts = swinClub.getAvailableCourts(sport,aDateTime,duration);
                
                                    String delimiter = "";
                                    if(availableCourts.isEmpty())
                                    {
                                        output.setText("No Courts Available for "+sport);
                                    }   
                                    else
                                    {
                                        output.setText("List of available courts:");
                                        for(Court aCourt : availableCourts)
                                        {
                                            output.append(delimiter+aCourt.getCourtId());
                                            delimiter = ",";
                                
                                        }
                                        output.append("\n");
                                        return true;
                                    }
                                }
                                else
                                {
                                    output.setText("Total booking for a day for "+sport+" cannot be more than allowed "+swinClub.findSport(sport).getDurationLimit()+"mins");
                                }
                            }
                            else
                            {
                                output.setText("Error:Bookings can only be made for within 7 days from now and between 8am and 11pm! ");
                    
                            }
                        }
                        else
                        {
                            output.setText("No such sport Exists!");
                        }
                    }
                    catch(DateTimeParseException dtpe)
                    {
                        output.setText("Error: Enter Date time in format yyyy-mm-dd hh:mm");
            
          
                    }
                   //JOptionPane.showMessageDialog(this,"Part added ok");
                  }
                
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(this,ex.getMessage());
                }
                return false;
            }
        
        /**
         * validates the form and checks if all fields filled by user
         * @returns true if all fields filled
         */
         private boolean validateForm() throws Exception
    {
        // check all fields not blank
        if (tfNameOfSport.getText().equals(""))
            throw new Exception("Name of Sport cannot be left blank");
        if (tfTimeAndDate.getText().equals(""))
            throw new Exception("time and date cannot be left blank");
        if (tfDuration.getText().equals(""))
            throw new Exception("Duration cannot be left blank");
        
      
        return true;
    
    }
    
    /**
     * closes the form 
     */
   private void closeForm()
      {
          this.setVisible(false);
      }
}
