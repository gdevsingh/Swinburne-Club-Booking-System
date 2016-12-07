import java.time.*;
import java.time.temporal.ChronoUnit;
/**
 * Represesnts Booking belonging to the Member and Court
 * 
 */
public class Booking implements Comparable<Booking>
{
    private LocalDateTime bookingDateTime;
    private int duration;
    private Member member;
    private Court bookedFor;
    
    public Booking(
                   LocalDateTime bookingDateTime, 
                   int duration,
                   Member bookedBy,
                   Court bookedFor)
    {
        
        this.bookingDateTime = bookingDateTime;
        this.duration = duration;
        this.member = bookedBy;
        this.bookedFor = bookedFor;
    }
    
    /**
     * Checks if accepted date overlaps the existing booking date
     * @param aDateTime the date time received for checking
     * @param duration to mark endtime of the booking date and time to be checked for oerlapping
     * @return true if overlaps, else false
     */
    public boolean overLap(LocalDateTime aDateTime, int duration)
    {
        if((aDateTime.isBefore(bookingDateTime) && aDateTime.plusMinutes(duration).isBefore(bookingDateTime)) || (aDateTime.isAfter(bookingDateTime.plusMinutes(this.duration)) && aDateTime.plusMinutes(duration).isAfter(bookingDateTime.plusMinutes(this.duration)))   )
            return false;
        else
            return true;
    }
    
    /**
     * Gives different in time between booking start time and end time
     * @returns difference in minutes
     */
    public int getDifference()
    {
        LocalDateTime tempDateTime = LocalDateTime.from( bookingDateTime );
        long minutes = tempDateTime.until( bookingDateTime.plusMinutes(duration), ChronoUnit.MINUTES);
        return (int)minutes;
    }
    
    /**
      * Gives court object the booking belongs to
      * @returns court object
      */
    public Court getCourt()
    {
        return bookedFor;
    }
    
    /**
     * Gives Member object the booking belongs to 
     * @returns member object
     */
    public Member getMember()
    {
        return member;
    }
    
    /**
     * Gives datetime for the booking
     * @returns bookingDateTime object
     */
    public LocalDateTime getBookingDateTime()
    {
        return bookingDateTime;
    }
    
    /**
     * returns duration of the booking
     * @returns duration of the booking
     */
    public int getDuration()
    {
        return duration;
    }
    
    /**
     * Used by sort() method from interface comparable
     * @returns -1,0 or 1 as per comparison
     */
    public int compareTo(Booking aBooking)
    {
        
        return this.bookingDateTime.toString().compareTo(aBooking.bookingDateTime.toString());
        
    }
    
    /**
     * Returns information about the booking object
     */
    public String toString()
    {
        return "Booking date:"+bookingDateTime.getDayOfMonth()+"-"+bookingDateTime.getMonth().name()+"-"+bookingDateTime.getYear()+",Start Time:"+bookingDateTime.getHour()+":"+bookingDateTime.getMinute()+",Duration:"+duration+"(mins),Member:"+member.getMemberId()+",Court:"+bookedFor.getCourtId()+"\n";
    }
}
