import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * GUI class for SBS
 * 
 * @author Gurdev Singh
 * @version 0.1
 */
public class GUIClub extends JFrame implements ActionListener,Observer
{
    // instance variables - replace the example below with your own
    private Club swinClub;
    private JPanel mainPanel;
    private JList jl;
    private JTextField toFind;  
    private JTextArea output;
    

    /**
     * Constructor for objects of class GUIClub
     * @param club swinburne club object
     */
    public GUIClub(Club club)
    {
        swinClub = club;
    
        // initialise instance variables
    }
    
    /**
     * Generates UI elemets 
     */
    public void run()
    {
        this.setTitle("Swinburne Sports Club");
        this.setSize(500,400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit();
            }
        });

        
        mainPanel = new JPanel(new BorderLayout());
       

        
        addHeader();
        addListBox();
        addButtonPanel();
         this.getContentPane().add(mainPanel);
        this.setVisible(true);
    
    }

    /**
     * replaces old list values with new list values
     */
    public void update()
    {
        addListBox();
    }
    
    /**
     * adds header label to jframe
     */
    public void addHeader()
    {
        JPanel plab = new JPanel();
        
        JLabel label = new JLabel("Swinburne Country Club Booking System");
        plab.add(label);
        mainPanel.add(plab,BorderLayout.NORTH);
        label.setText("Swinburne Country Club Booking System");
        label.setMinimumSize(new Dimension(80,20));
    }
    
    /**
     * adds list box to the UI
     */
    public void addListBox()
    {
        
        if (jl != null)
            mainPanel.remove(jl);
        // make a decision, what will you do if no parts
        // ignore
        // add message?
        // could descide not to start and throw back to start
        try
        {
            jl = new JList(swinClub.getAllReadBookings().toArray());
        }
        catch (Exception ex)
        {
            jl = new JList(new String[] {"No Bookings in List"});
        }
        mainPanel.add(jl);
        validate();
        repaint();
        
           
    }
    
    
    /**
     * displays court and member bookings
     */
    public void addListBox(ArrayList<Booking> bookings)
    {
        
        if (jl != null)
            mainPanel.remove(jl);
        // make a decision, what will you do if no parts
        // ignore
        // add message?
        // could descide not to start and throw back to start
        try
        {
            jl = new JList(bookings.toArray());
        }
        catch (Exception ex)
        {
            jl = new JList(new String[] {"No Bookings in List"});
        }
        mainPanel.add(jl);
        validate();
        repaint();
        
           
    }

    /**
     * adds all functional buttons to main UI
     */
    public void addButtonPanel()
    {
        // build Panel
        JPanel big = new JPanel();
        big.setLayout(new BoxLayout(big,BoxLayout.Y_AXIS));
        JPanel jp1 = new JPanel();
        jp1.setLayout(new BoxLayout(jp1,BoxLayout.X_AXIS));
        JPanel jp2 = new JPanel();
        jp2.setLayout(new BoxLayout(jp2,BoxLayout.X_AXIS));
        JPanel jp3 = new JPanel();
        jp3.setLayout(new BoxLayout(jp3,BoxLayout.X_AXIS));
        // build buttons and text field
        JButton showAvailableCourts = new JButton("Show_Available_Courts");
        JButton makeBooking = new JButton("Make_Booking");
        JButton showMemberBookings = new JButton("Show_Member_Bookings");
        JButton showCourtBookings = new JButton("Show_Court_Bookings");
        JButton deleteBooking = new JButton("Delete_Booking");
        JButton find = new JButton("Find");
        JButton showAll = new JButton("Show_All_Bookings");
        
        output = new JTextArea();
        toFind = new JTextField(5);
        // add to panel
        jp1.add(showAvailableCourts);
        jp1.add(makeBooking);
        jp2.add(showMemberBookings);
        jp2.add(showCourtBookings);
        jp3.add(deleteBooking);
        jp3.add(showAll);
        // add Listener
        showAvailableCourts.addActionListener(this);
        makeBooking.addActionListener(this);
        showMemberBookings.addActionListener(this);
        showCourtBookings.addActionListener(this);
        deleteBooking.addActionListener(this);
        showAll.addActionListener(this);
        
        // add panel to main
        big.add(jp1);
        big.add(jp2);
        big.add(jp3);
        big.add(output);
        mainPanel.add(big,BorderLayout.SOUTH);
        //mainPanel.add(jp,BorderLayout.SOUTH);
        
    }   
    
    /**
     * action listener method decides what to do when a button is pressed
     * @param evt action event generated on button press
     */
    public void actionPerformed(ActionEvent evt)
    {
        String command = evt.getActionCommand();
        System.out.println(command);
        if (command.equals("Show_Available_Courts"))
            showAvailableCourts();
        if (command.equals("Make_Booking"))
            makeBooking();
        if (command.equals("Show_Member_Bookings"))
            showMemberBookings();    
        if (command.equals("Show_Court_Bookings"))
            showCourtBookings();
        if (command.equals("Delete_Booking"))
            deleteBooking();
        if (command.equals("Show_All_Bookings"))
            showAll();
    }
    
    /**
     * generates new UI to accept data to view available courts
     */
     private void showAvailableCourts()
     {
         ShowAvailableCourtsGUI sac = new ShowAvailableCourtsGUI(swinClub);
         
     }
     
     /**
      *  generates another UI for accepting values to make booking
      */
     private void makeBooking()
     {
        
         MakeBookingGUI mb = new MakeBookingGUI(swinClub);
        //this.setVisible(false);
         
     }
     
     /**
      * displays member bookings
      */
     private void showMemberBookings()
     {
         int memberId = -1;
        while(true) 
        {
            try
            {
                memberId = Integer.parseInt(JOptionPane.showInputDialog("Enter Member Id(Integer only):"));

                break;
            }
            catch(Exception e)
            {
                break;
                //output.setText("");
            }
       
        }
        if(swinClub.findMember(memberId).getMemberId()==(memberId) )
        {
            Member aMember = swinClub.findMember((memberId));
            ArrayList<Booking> aBookings = aMember.getBookings();
            if(!aBookings.isEmpty())
            {
                addListBox(aBookings);
            }
            else
            {
                output.setText("Error:No Bookings exist!");
            }
        }
        else
        {
            output.setText("Error:No such member exists!");
        }
     }
     
     /**
      * displays court bookings
      */
     private void showCourtBookings()
    {
        int courtId = -1;
        while(true) 
        {
            try
            {
                courtId = Integer.parseInt(JOptionPane.showInputDialog("Enter Court Id(Integer only):"));
                break;
            }
            catch(Exception e)
            {
                break;
                //output.setText("");
            }
       
        }if(swinClub.findCourt(courtId).getCourtId() == courtId )
        {
            Court aCourt= swinClub.findCourt(courtId);
            ArrayList<Booking> aBookings = aCourt.getBookings();
            if(!aBookings.isEmpty())
            {
               addListBox(aBookings);
            }
            else
            {
                output.setText("Error:No Bookings exist!");
            }
        }
        else
        {
            output.setText("Error:No such court exists!");
        }
    }
    
    /**
     * deletes a booking 
     */
     private void deleteBooking()
     {
         Booking selected = (Booking) jl.getSelectedValue();
        if (selected == null)
        {
            // used for testing
            output.setText("Please select a Booking to delete");
            JOptionPane.showMessageDialog(this,"Please select a Booking");
        }
        else
        {
            // used for testing
            System.out.println("Selected Booking is " + selected);
            // could add a yes no option here
            JOptionPane.showMessageDialog(this,"Booking being deleted is " + "\n" + selected);
        
        
        // deleting the Booking
            try
            {
                selected.getCourt().getBookings().remove(selected);
                selected.getMember().getBookings().remove(selected);
                //swinClub.findCourt(selected.getCourt().getCourtId()).getBookings().remove(selected);
                //swinClub.getAllReadBookings().remove(selected);
                swinClub.removeFromAllReadBookings(selected);
                
                //swinClub.findMember(selected.getMember().getMemberId()).getBookings().remove(selected);
                //swinClub.findCourt(selected.getCourt().getCourtId()).getBookings().remove(selected);
            }
            catch (Exception ex)
            {
                //JOptionPane.showMessageDialog(this,"Error saving change to file "    + "\n" + ex.getMessage());
            }
        }
        // updating the JList
        addListBox();
     }
     
     /**
      * repopulates listbox with updated booking values
      */
     public void showAll()
     {
         addListBox(swinClub.getAllReadBookings());
     }
     
     /**
      * exits the system after writing bookings to bookings.txt
      */
     public void onExit() 
     {
         swinClub.writeBookingsToFile();
         System.exit(0);
     }    
    

   
}
