import java.awt.*;
import java.time.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.time.format.DateTimeParseException;
import java.io.*;
/**
 * This class is responsible for genering a separate UI for asking user details for making a booking
 * 
 * @author Gurdev Singh
 * @version 0.1
 */
public class MakeBookingGUI extends JFrame
{
    // instance variables - replace the example below with your own
    private Club swinClub;
    private JPanel mainPanel;
    private JTextField tfMemberId;
    private JTextField tfNameOfSport;
    private JTextField tfTimeAndDate;
    private JTextField tfDuration;
    private JTextField tfCourt;
    private JTextArea output;

    /**
     * initialises UI
     * @param club swinburne club object
     */
    public MakeBookingGUI(Club club)
    {
        // initialise instance variables
        swinClub = club;
        this.setTitle("Make a Booking");
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainPanel = new JPanel(new GridLayout(7,0,20,20));
        this.getContentPane().add(mainPanel);
        mainPanel.add(memberNumPanel());
        mainPanel.add(sportNumPanel());
        mainPanel.add(timeNumPanel());
        mainPanel.add(durationNumPanel());
        mainPanel.add(courtNumPanel());
        mainPanel.add(buttonPanel());
        mainPanel.add(outputPanel());
      
        this.setVisible(true);
    }

    /**
     * adds member panel to UI
     * @returns JPanel to be added
     */
    public JPanel memberNumPanel()
    {
        JPanel jp = new JPanel(new GridLayout(1,2,5,5));
        JLabel jl = new JLabel("Member Id");
        jp.add(jl);
        tfMemberId = new JTextField(5);
        jp.add(tfMemberId);
        return jp;
    }
    
    /**
     * adds court panel to UI
     * @returns JPanel to be added
     */
    public JPanel courtNumPanel()
    {
        JPanel jp = new JPanel(new GridLayout(1,2,5,5));
        JLabel jl = new JLabel("Court Id");
        jp.add(jl);
        tfCourt = new JTextField(5);
        jp.add(tfCourt);
        return jp;
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
                
                    if(tfCourt.getText().equals(""))
                    {
                        if(makePartialBooking())
                        {
                            jb.setText("add Booking");
                        }
                    }
                    else
                    {
                        makeBooking();
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
         * validates the form and checks if all fields filled by user
         * @returns true if all fields filled
         */
   private boolean validateForm() throws Exception
   {
        // check all fields not blank
        if (tfMemberId.getText().equals(""))
            throw new Exception("MemberId cannot be left blank");
        if (tfNameOfSport.getText().equals(""))
            throw new Exception("Name of Sport cannot be left blank");
        if (tfTimeAndDate.getText().equals(""))
            throw new Exception("Time and date cannot be left blank");
        if (tfDuration.getText().equals(""))
            throw new Exception("Duration cannot be left blank");
       // if (tfCourt.getText().equals(""))
         //   throw new Exception("Court cannot be left blank");
      
        return true;
    
   }
    
    /**
     * adds a new bookings as per user inputs
     * 
     */
    public void makeBooking()
    {
        int tEDuration = 0;
         
        int aMemberId = Integer.parseInt(tfMemberId.getText());
        if(swinClub.findMember(aMemberId).getMemberId()==aMemberId )
        {
            if( swinClub.findMember(aMemberId).getFinancial())
             {
                 String sport = tfNameOfSport.getText();
                 if(!swinClub.findMember(aMemberId).getSports().equals("no sport"))//members plays any sport or not
                 {
                     boolean memberPlaysIt = false;
                    for(String asport  : swinClub.findMember(aMemberId).getSports() )
                    {
                        if(asport.equalsIgnoreCase(sport))
                        {
                            memberPlaysIt = true;
                        }
                    }
                    if(memberPlaysIt)
                    {
                        Sport aSport = swinClub.findSport(sport);
                    
                        LocalDateTime aDateTime = DateUtility.getLocalDateTime(tfTimeAndDate.getText());
                        if(aDateTime.isAfter(LocalDateTime.now()) && aDateTime.isBefore(LocalDateTime.now().plusDays(7)) && (aDateTime.getHour()>=8) && aDateTime.getHour()<(22))
                        {
                            
                            int aDuration = Integer.parseInt(tfDuration.getText());
                            //if(aDateTime.plusMinutes(aDuration).isBefore(LocalDateTime.parse()))
                            //calculating existsing occupied durations for member x, sport y and day z
                            if(!swinClub.findMember(aMemberId).getBookingsForDate(aDateTime).isEmpty())
                            {
                                for(Booking aBooking : swinClub.findMember(aMemberId).getBookingsForDate(aDateTime))
                                {
                                    if(aSport.isCourtInSport(aBooking.getCourt().getCourtId()))
                                    {
                                        tEDuration +=   aBooking.getDifference();
                                    }
                                }
                        
                            }
                            if(tEDuration + aDuration <= swinClub.findSport(sport).getDurationLimit())
                            {
                                output.setText("Select a court:");
                                ArrayList<Court> availableCourts = showAvailableCourts(sport,aDateTime,aDuration);
                                int courtId = Integer.parseInt(tfCourt.getText());
                                boolean rightCourt = false;
                                for(Court aCourt : availableCourts)
                                {
                                    if(aCourt.getCourtId()==courtId)
                                    {
                                        rightCourt = true;
                                        break;
                                    }
                                }
                                if(rightCourt == true)
                                {
                                    swinClub.findMember(aMemberId).addBooking(new Booking(aDateTime,aDuration,swinClub.findMember(aMemberId),aSport.findCourt(courtId)));
                                    swinClub.addToAllReadBookings(new Booking(aDateTime,aDuration,swinClub.findMember(aMemberId),aSport.findCourt(courtId)));
                                    
                                    aSport.findCourt(courtId).addBooking(new Booking(aDateTime,aDuration,swinClub.findMember(aMemberId),aSport.findCourt(courtId)));
                                    
                                    
                                    output.setText("Success!");
                                    //JDialog
                                    //GUIClub gui = new GUIClub(swinClub);
                                    //gui.run();
                                    this.closeForm();
                                   // KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s","Booking date","Start Time","Booked Till","Member","Court"));
                                   //  KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s",aBooking.getBookingDateTime().getDayOfMonth()+"-"+aBooking.getBookingDateTime().getMonth().name()+"-"+aBooking.getBookingDateTime().getYear(),aBooking.getBookingDateTime().getHour()+":"+aBooking.getBookingDateTime().getMinute(),aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getHour()+":"+aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getMinute(),aBooking.getMember().getMemberId(),aBooking.getCourt().getCourtId()));
                                }
                                else
                                {
                                    output.setText("Error: Wrong court number!");
                                }
                            }
                            else
                            {
                                output.setText("Error: Total booking for the day cannot be more than allowed "+swinClub.findSport(sport).getDurationLimit()+"mins");
                            }                 
                        }
                        else
                        {
                            output.setText("Error:Bookings can only be made for within 7 days from now and between 8am and 11pm! ");
                        }
                    }
                    else
                    {
                        output.setText("Error: you do not play this sport!");
                    }
                    
                 }  
                 else
                 {
                     output.setText("Error: You do not play any of the sports offered!");
                 }
            }
            else
            {
                output.setText("Error: you are not financial!");
            }
        }
        else
        {
            output.setText("Error: No such member found!");
        }
    
   
    }

    /**
     * shows available courts to select from
     */
    public boolean makePartialBooking()
    {
        try
        {
            if(validateForm())
            {
                int tEDuration = 0;
                int aMemberId = Integer.parseInt(tfMemberId.getText());
                if(swinClub.findMember(aMemberId).getMemberId()==aMemberId )
                {
                    if( swinClub.findMember(aMemberId).getFinancial())
                    {
                        String sport = tfNameOfSport.getText();
                        if(!swinClub.findMember(aMemberId).getSports().equals("no sport"))//members plays any sport or not
                        {
                            boolean memberPlaysIt = false;
                            for(String asport  : swinClub.findMember(aMemberId).getSports() )
                            {
                                if(asport.equalsIgnoreCase(sport))
                                {
                                    memberPlaysIt = true;
                                }
                            }
                            if(memberPlaysIt)
                            {
                                Sport aSport = swinClub.findSport(sport);
                                
                                LocalDateTime aDateTime = DateUtility.getLocalDateTime(tfTimeAndDate.getText());
                                if(aDateTime.isAfter(LocalDateTime.now()) && aDateTime.isBefore(LocalDateTime.now().plusDays(7)) && (aDateTime.getHour()>=8) && aDateTime.getHour()<(22))
                                {
                                    
                                    int aDuration = Integer.parseInt(tfDuration.getText());
                                    //if(aDateTime.plusMinutes(aDuration).isBefore(LocalDateTime.parse()))
                                    //calculating existsing occupied durations for member x, sport y and day z
                                    if(!swinClub.findMember(aMemberId).getBookingsForDate(aDateTime).isEmpty())
                                    {
                                        for(Booking aBooking : swinClub.findMember(aMemberId).getBookingsForDate(aDateTime))
                                        {
                                            if(aSport.isCourtInSport(aBooking.getCourt().getCourtId()))
                                            {
                                                tEDuration +=   aBooking.getDifference();
                                            }
                                        }
                                        
                                    }
                                    if(tEDuration + aDuration <= swinClub.findSport(sport).getDurationLimit())
                                    {
                                        output.setText("Select a court:");
                                        ArrayList<Court> availableCourts = showAvailableCourts(sport,aDateTime,aDuration);
                                        return true;
                                    }
                                }}}}}}}
                                catch(Exception ex)
                                {
                                    JOptionPane.showMessageDialog(this,ex.getMessage());
                                }
                                return false;
    }
    
    /**
     * Displays available courts for a particular sport and date & time for use by makeBooking()
     */
    public ArrayList<Court> showAvailableCourts(String sport,LocalDateTime aDateTime,int duration)
    {
         ArrayList<Court> availableCourts = swinClub.getAvailableCourts(sport,aDateTime,duration);
            String delimiter = "";
            if(availableCourts.isEmpty())
            {
                output.append("No Courts Available for "+sport);
            }
            else
            {
                output.append("List of available courts:");
                for(Court aCourt : availableCourts)
                {
                    output.append(delimiter+aCourt.getCourtId());
                    delimiter = ",";
                }
            }
        return availableCourts;
    }
    
    /**
     * closes the jframe
     */
    private void closeForm()
    {
        this.setVisible(false);
    }
}
