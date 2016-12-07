import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.io.IOException;



/**
 * Represents club object 
 *  
 * @author Gurdev Singh 
 * @version 0.2
 */

public class Club
 implements Observable
{
    private Observer imListening;
    private String name;
    private ArrayList<Member> allMembers;
    private ArrayList<Sport> allSports;
    private ArrayList<Booking> allRead;
   
   
     /**
     * notifies its observers of any changes made to this class
     */
    public void notifyObservers()
    {
        imListening.update();
    }
    
    /**
     * adding new observer
     */
    public void addObserver(Observer o)
    {
        imListening = o;
    }
    
    public Club(String name)
    {   
        this.name = name;
        allMembers = new ArrayList<Member>();
        allSports = new ArrayList<Sport>();
    }

    /**
     * Reads all the members from the members.txt file and creates their objects to store
     * @throws FileNotFoundException
     */
    public void readMembers() throws Exception
    {
        
        try{
            ArrayList<String> members = FileUtility.readFromFile("members.txt");
        for(String aMember : members)
        {
            String[] memberDetails = aMember.split(",");
            ArrayList<String> sports = new ArrayList<String>();
            if(memberDetails.length>3)
            {
                int count = 3;
                while(count!=memberDetails.length)
                {
                    sports.add(memberDetails[count]);
                    count++;
                }
            }
            //if(memberDetails)
            allMembers.add(new Member(Integer.parseInt(memberDetails[0]),memberDetails[1],Boolean.parseBoolean(memberDetails[2]),sports)); 
        }
    }
    catch(Exception e)
    {
        //KeyBoard.errorLogger()
        throw new FileReadException("Error:incorrect data in members.txt");
    }
      // KeyBoard.println(allMembers+"");
    }
    
    /**
     * Reads all the sports from the sports.txt file and creates their objects to store
     * @throws FileNotFoundException
     */
    public void readSports() throws Exception
    {
        ArrayList<String> sports = FileUtility.readFromFile("sports.txt");
        for(String aSport : sports)
        {
            String[] sportDetails = aSport.split(",");
            ArrayList<Court> courts = new ArrayList<Court>();
            if(sportDetails.length>4)
            {
                int count = 4;
                while(count!=sportDetails.length)
                {
                    if(isNumeric(sportDetails[count]))
                    {courts.add(new Court(Integer.parseInt(sportDetails[count])));
                    }
                    
                    count++;
                }
            }
            try
            {
            if(sportDetails[0].equalsIgnoreCase("tennis"))
            {
                 Sport tennis = new Tennis(sportDetails[0],Double.parseDouble(sportDetails[1]),Double.parseDouble(sportDetails[2]),Double.parseDouble(sportDetails[3]),courts); 
                 allSports.add(tennis);
            }    
            else if(sportDetails[0].equalsIgnoreCase("squash"))
            {
                 Sport squash = new Squash(sportDetails[0],Double.parseDouble(sportDetails[1]),Double.parseDouble(sportDetails[2]),Double.parseDouble(sportDetails[3]),courts);
                 allSports.add(squash);
            }   
            else if(sportDetails[0].equalsIgnoreCase("badminton"))
            {
                 Badminton badminton= new Badminton(sportDetails[0],Double.parseDouble(sportDetails[1]),Double.parseDouble(sportDetails[2]),Double.parseDouble(sportDetails[3]),courts);
                 allSports.add(badminton);
            }
            else if(sportDetails[0].equals(""))
            {
                //throw corrupt line exception
                throw new FileReadException("invalid sport in file sports.txt");
            }
            }
            catch(Exception e)
            {
                throw new FileReadException("Error:incorretc data in SPORTS.txt file!");
                
            }
        }
        //KeyBoard.println(allSports+"");
    }
    
    /**
     * Reads all the bookings from the Bookings.txt file and creates their objects to store
     * @throws FileNotFoundException
     */
    public void readBookings() throws Exception
    {
        try{
        ArrayList<String> sports = FileUtility.readFromFile("Bookings.txt");
        ArrayList<Booking> allBooks = new ArrayList<Booking>();
        for(String temp : sports)
        {
            String[] data = temp.split(","); 
            LocalDateTime bookingDateTime = LocalDateTime.parse(data[0]);
            int duration = Integer.parseInt(data[1]);
            Member member = findMember(Integer.parseInt(data[2]));
            Court court = findCourt(Integer.parseInt(data[3]));
            allBooks.add(new Booking(bookingDateTime,duration,member,court));
            
        }
        FileUtility.clearFile("Bookings.txt");
        //KeyBoard.println(allBooks+"");
        for(Booking aBooking : allBooks)
        {
            aBooking.getMember().addBooking(aBooking);
            aBooking.getCourt().addBooking(aBooking);
        }
        //System.out.println(allBooks+"");
        allRead = allBooks;
    }
    catch(Exception e)
    {
        throw new FileReadException("Error:Can't read bookings.txt");
    }
    
    }
    
    /**
     * fetches all bookings read from file
     * @returns bookings read from file
     */
    public ArrayList<Booking> getAllReadBookings()
    {
        return allRead;
    }
    
    /**
     * adds a booking to all read bookings and notifies its observers
     * 
     */
    
    public void addToAllReadBookings(Booking booking)
    {
        allRead.add(booking);
        notifyObservers();
    }
    
    /**
     * removes a booking from all bookings and notifies observers
     */
    public void removeFromAllReadBookings(Booking booking)
    {   
        allRead.remove(booking);
        notifyObservers();
    }
    
    /**
     * checks if a sport exists in the club
     * @param aSportName name of the sport to be checked
     * @returns true if exists
     */
    public boolean ifSportExists(String aSportName)
    {
        for(Sport aSport : allSports)
        {
            if(aSport.getName().equalsIgnoreCase(aSportName))
                return true;    
        }
        return false;
    }
   
    /**
     * Finds and retrieves a sport from name 
     * @param aName name of the sport
     * @returns sport which has the name specified
     */
    public Sport findSport(String aName)
    {
        for(Sport aSport : allSports)
        {
            if(aSport.getName().equalsIgnoreCase(aName))
                return aSport;    
        }
        Sport aSport = new Sport(); 
        return aSport;
    }
    
    /**
     * returns all available courts in the club for a sport at a particular date and time
     * @param sport name of the sport
     * @param aDateTime date and time to be checked for
     * @param duration duration of the booking to be made
     * @returns all the courts available
     */
    public ArrayList<Court> getAvailableCourts(String sport,LocalDateTime aDateTime, int aDuration )
    {
        ArrayList<Court> availableCourts = new ArrayList<Court>();
        boolean overlaps = false;
        Sport aSport = findSport(sport);
        ArrayList<Court> allCourts = aSport.getCourts();
        for(Court aCourt : allCourts)
        {
            ArrayList<Booking> allBookings = aCourt.getBookings();
            if(!allBookings.isEmpty())
            {
                for(Booking aBooking : allBookings)
                {
                    //check for overlap
                    if(aBooking.overLap(aDateTime, aDuration))
                    {
                        overlaps = true;
                        break;
                    }
                    //if not overlap, add to available courts
                }
                if(!overlaps)
                {
                    availableCourts.add(aCourt);
                }
                else
                {
                    overlaps = false;
                }
            }
            else
            {
                availableCourts.add(aCourt);
            }
        }
        return availableCourts;
        
    }
    
    /**
     * Finds a member in the club by their memberId
     * @param aMemberId the member Id for the member
     * @returns Member the member object
     */
    public Member findMember(int aMemberId)
    {
        for(Member aMember : allMembers)
        {
            if(aMember.getMemberId() == aMemberId)
                return aMember;
        }
        
        return new Member(-1,"",false);
    }
    
    /**
     * finds court in the club form courtId
     * @param aCourtId the courtId of the court to be found
     * @returns court object
     */
    public Court findCourt(int aCourtId)
    {
        for(Sport aSport : allSports)
        {
            ArrayList<Court> allCourts = aSport.getCourts();
            for(Court aCourt : allCourts)
            {
                if(aCourt.getCourtId()==aCourtId)
                {
                    return aCourt;
                }
            }
        }
        return new Court(-1);
    }
    
    /**
     * Writes all the bookings made in the system to the file Bookings.txt
     */
    public void writeBookingsToFile()
    {
        ArrayList<Booking> allBookings = new ArrayList<Booking>();
        ArrayList<String> tempStr = new ArrayList<String>(); 
        for(Member aMember : allMembers)
        {
            if(!aMember.getBookings().isEmpty())
            {
                ArrayList<Booking> memberBookings = aMember.getBookings();
                for(Booking aBooking : memberBookings)
                {
                    allBookings.add(aBooking);
                }
            }   
        }
        Collections.sort(allBookings);
        try
        {
        for(Booking aBooking : allBookings)
        {
            //.getDayOfMonth()+"-"+aBooking.getBookingDateTime().getMonth()+"-"+aBooking.getBookingDateTime().getYear())
            tempStr.add(aBooking.getBookingDateTime()+"");
            tempStr.add(aBooking.getDuration()+"");
            tempStr.add(aBooking.getMember().getMemberId()+"");
            tempStr.add(aBooking.getCourt().getCourtId()+"");
            FileUtility.writeToFile("Bookings.txt",tempStr);    
            tempStr.clear();
        }
        }
        catch(Exception ioe)
        {
            KeyBoard.println("Error:"+ioe.getMessage());
        }//KeyBoard.println(textToWrite+"");
        
    }
    
    /**
     * tells if a string is numeric or not
     * @returns true if numeric
     */
    public static boolean isNumeric(String str)  
    {  
        try  
        {  
            double d = Double.parseDouble(str);  
        }      
        catch(NumberFormatException nfe)  
        {  
            return false;  
        }  
        return true;  
    }
    /**
     * Gives information about the club object
     */
    public String toString()
    {
        return "Name:"+name+"\n Members:\n"+allMembers+"\nSports:\n"+allSports;
    }
   
    
}
