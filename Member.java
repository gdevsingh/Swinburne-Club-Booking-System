import java.util.*;
import java.time.LocalDateTime;
/**
 * Represents object for a member belonging to a club
 * 
 * @author Gurdev Singh 
 * @version 0.2
 */ 
public class Member
{
    private String name;
    private int memberId;
    private ArrayList<String> sportsPlayed;
    private boolean isFinancial;
    private ArrayList<Booking> bookings;
    
    
    public Member(String name)
    {
        name = "gd3v";
    }
    
    /**
     * defines member instance variables
     */
    public Member(int aMemberId, String aName, boolean aIsFinancial)
    {
        name = aName;
        memberId = aMemberId;
        isFinancial = aIsFinancial;
    }
    
    /**
     * defines member instance variables while reading members from file
     */
    public Member(int aMemberId, String aName, boolean aIsFinancial, ArrayList<String> aSportsPlayed)
    {
      //  if(aMemberId.equals("") || )
        memberId = aMemberId;
        name = aName;
        isFinancial = aIsFinancial;
        sportsPlayed = aSportsPlayed;
        bookings = new ArrayList<Booking>();
    }     

    /**
     * Gives sports played by the member
     * @returns name of sports played by member
     */
    public ArrayList<String> getSports()
    {
        return sportsPlayed;
    }
    
    /**
     * Gives memberid of the member
     * @returns memberId
     */
    public int getMemberId()
    {
        return memberId;
    }
    
    /**
     * Checks the financial status of the member
     * @returns true if member is financial
     */
    public boolean getFinancial()
    {
        return isFinancial;
    }
    
    /**
     * adds booking of a member
     * @param booking object to be added
     */
    public void addBooking(Booking aBooking)
    {
        bookings.add(aBooking);
        sortingBookings();
    }
    
    /**
     * Retrieves booking objects for a particular date 
     * @returns booking objects
     */
    public ArrayList<Booking> getBookingsForDate(LocalDateTime aDateTime)
    {
        ArrayList<Booking> sameDayBookings = new ArrayList<Booking>();
        for(Booking booking : bookings)
        {
            if(booking.getBookingDateTime().getDayOfMonth() == aDateTime.getDayOfMonth() && booking.getBookingDateTime().getMonthValue() == aDateTime.getMonthValue() && booking.getBookingDateTime().getYear() == aDateTime.getYear() )
            {
                sameDayBookings.add(booking);
            }
        }
        return sameDayBookings;
        
    }
    
    /**
     * Retrieves all the bookings belonging to a particular member
     * @returns booking objects
     */
    public ArrayList<Booking> getBookings()
    {
        return bookings;
    }
    
    /**
     * Sorts bookings for the member on the basis of date of booking in ascending order
     * 
     */
    public void sortingBookings()
    {
        Collections.sort(bookings);
    }

    /**
     * Retrieves information about the member object
     */
    public String toString()
    {
        return "I am member:"+name+"\nid:"+memberId+"\nisfinancial:"+isFinancial+"\nsportsPlayed:"+sportsPlayed.toString();
    }
    
}