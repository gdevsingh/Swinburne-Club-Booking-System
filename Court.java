import java.util.*;
/**
 * Represents object for a Playing Court
 * 
 * @author Gurdev Singh 
 * @version 0.2
 */
public class Court
{
    private int courtId;
    private ArrayList<Booking> courtBookings;
   
    /**
     * Defines courtid for the Courtobject
     * 
     */
    public Court(int courtId)
    {
        this.courtId = courtId; 
        courtBookings = new ArrayList<Booking>();
    }
    
    /**
     * Retrieves courtId for a court object  
     * @returns courtId
     */
    public int getCourtId()
    {
        return courtId;
        
    }
    
    /**
     * Adds booking object to the court
     */
    public void addBooking(Booking aBooking)
    {
        courtBookings.add(aBooking);
        sortingBookings();
    }
    
    /**
     * retrieves all booking objects for a court
     * @returns collection of booking objects
     */
    public ArrayList<Booking> getBookings()
    {
        return courtBookings;
    }
    
    /**
     * sorts bookings in ascending order as per the date
     */
    public void sortingBookings()
    {
        Collections.sort(courtBookings);
    }

    /**
     * Returns courtId for the court
     * @returns court object
     */
    public String toString()
    {
        return courtId+"";
    }  

}
