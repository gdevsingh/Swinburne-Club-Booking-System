import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GUI extends JFrame
{
    // instance variables - replace the example below with your own
    private Club swinClub;
     
    private JPanel jPButtons;
    private JPanel jPFields;
    private JPanel jPTable;
    
    private Container container;
    
    //buttons
    private JButton availableC;
    private JButton makeB;
    private JButton showMB; 
    private JButton showCB;
    private JButton deleteB;
    private JButton go;
    
    //fieldLabels
    private JLabel lmemberId;
    private JLabel ldated;
    private JLabel lsTime;
    private JLabel lduration;
    private JLabel lcourt;
    private JLabel lnameOfSport;
    
    //fields
    private JComboBox fnameOfSport;
    private JComboBox fsTime;
    private JComboBox fmemberId;
    private JComboBox fcourt;
    private JTextField fduration;
    private DateComboBox fdcb;
    
    //table
    private JTable myTable;
    
    //private JLabel memberId;
    /**
     * Constructor for objects of class GUI
     */
    public GUI(Club club)
    {
        swinClub = club;
        //set size
        //this.setSize(500,800);
        setTitle("DefaultLayout for JPanel is Flow Layout");
        //get content pane
        container = getContentPane();
        GridLayout layout = new GridLayout(1,3,10,10);
        container.setLayout(layout);
        
        //create JPanel
        jPButtons = new JPanel();
        jPButtons.setLayout(new GridLayout(5,1,20,20));
      //  jPButtons.setPreferredSize(new Dimension(40,40));
        jPButtons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
        
        jPFields = new JPanel();
        //jPFields.setBounds(100, 100, 100, 100); 
        jPFields.setLayout(new BoxLayout(jPFields, BoxLayout.Y_AXIS));
        jPFields.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

      //  jPButtons.setPreferredSize(new Dimension(40,40));
        jPFields.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        
        jPTable = new JPanel();
        jPTable.setLayout(new GridLayout(5,1,20,20));
      //  jPButtons.setPreferredSize(new Dimension(40,40));
        jPTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        
        container.add(jPButtons);
        container.add(jPFields);
        container.add(jPTable);
        
        addComponents();
        addFields();
        //addTable();
       // addListeners();
        pack();
       
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void run()
    {
        // put your code here
        
        this.setVisible(true);
        populateValues();
    }
    
    public void addComponents()
    {
        //-----buttons
       availableC = new JButton("Show Available Courts");
       //availableC.setPreferredSize(new Dimension(20,20));
       makeB = new JButton("Make Booking");
       showMB = new JButton("Show Member Bookings"); 
       showCB = new JButton("Show Court Bookings");
       deleteB = new JButton("Delete Booking");
       //go = new JButton("go!");
       
       //--adding buttons
       jPButtons.add(availableC);
       jPButtons.add(makeB);
       jPButtons.add(showMB);
       jPButtons.add(showCB);
       jPButtons.add(deleteB);
       //jp.add(go);
       
       //--labels
       lmemberId = new JLabel("Meber Id");
       ldated = new JLabel("Pick Date");
       lsTime = new JLabel("Start Time");
       lduration = new JLabel("Duration");
       lcourt = new JLabel("Court");
       lnameOfSport = new JLabel("Sport");
       
       //--fields
       fmemberId = new JComboBox();
       fdcb = new DateComboBox();
       fsTime = new JComboBox();
       fduration = new JTextField();
       fcourt = new JComboBox();
       fnameOfSport = new JComboBox();
       
       //--adding fields
       jPFields.add(lmemberId);
       jPFields.add(fmemberId);
       jPFields.add(ldated);
       jPFields.add(fdcb);
       jPFields.add(lsTime);
       jPFields.add(fsTime);
       jPFields.add(lduration);
       jPFields.add(fduration);
       jPFields.add(lcourt);
       jPFields.add(fcourt);
       jPFields.add(lnameOfSport);
       jPFields.add(fnameOfSport);
       
       //--table
       myTable = new JTable();
       
       //--adding table
       jPTable.add(myTable);
     
       
    }
    
    public static void populateValues()
    {
        
    }
    
    public void addFields()
    {
        
    }
}
