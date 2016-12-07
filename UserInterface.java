import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.logging.*;
/**
 * Responsible for Generating command based UI for SBS
 * 
 * @author Gurdev Singh 
 * @version 0.2
 */
public class UserInterface
{

    //Club instance for declaring the SBS club
    private Club swinClub;
   
    /**
     * Instantiates the SBSClub
     */
    public UserInterface(Club swinClub)
    {
        this.swinClub = swinClub; 
         KeyBoard.println  ("|------  ___________________  _________----------|"); 
            KeyBoard.println  ("|------ /   _____/\\______   \\/   _____/----------|");
            KeyBoard.println  ("|------ \\_____  \\  |    |  _/\\_____  \\ ----------|");
            KeyBoard.println  ("|------ /        \\ |    |   \\/        \\----------|");
            KeyBoard.println  ("|------/_______  / |______  /_______  /----------|");
            KeyBoard.println  ("|-----         \\/         \\/        \\/-----------|");
            KeyBoard.println  ("|------------------------------------------------|");
            
    }
  
    /**
     * Displays menu and accepts input for menu number to perform action
     */
  
    public void run()
    {
        while(true)
            switch (menu() ) 
            {
                    case 1:
                       showAvailableCourts();
                        break;
                    case 2:
                        makeBooking();
                        break;
                    case 3:
                        showMemberBookings();
                        break;
                     case 4:
                        showCourtBookings();
                        break;
                      case 5:
                        deleteBooking();
                        break;      
                     case 6:
                        KeyBoard.println("Bye :)");
                        swinClub.writeBookingsToFile();
                        
                        System.out.println("written to file");
                        System.exit(0);
                        break;   
                     default:
                        System.out.println ( "Invalid option" );
                        break;
            }
    }
    
    /**
     * Displays menu for SBS
     */
    private int menu()
    {   
            KeyBoard.println  ("|------------------------------------------------|");
            KeyBoard.println ("| 1 - Show Available Courts                      |");
            KeyBoard.println ("| 2 - Make Booking for Member                    |");
            KeyBoard.println ("| 3 - Show Member Bookings                       |");
            KeyBoard.println ("| 4 - Show Court Bookings                        |");
            KeyBoard.println ("| 5 - Delete Booking                             |");
            KeyBoard.println ("| 6 - Exit                                       |");
            KeyBoard.println("|------------------------------------------------|");
            return  KeyBoard.getInteger("Select Option: ");
     }
     
    /**
     * Displays available courts for a particular sport and date & time
     */ 
    public void showAvailableCourts()
    {   try
        {
            String sport = KeyBoard.getString("Enter Name of the Sport:").trim();
            if(swinClub.ifSportExists(sport))
            {

                LocalDateTime aDateTime = DateUtility.getLocalDateTime(KeyBoard.getString("Enter Date and time(yyyy-mm-dd hh:mm):").trim());
                if(aDateTime.isAfter(LocalDateTime.now()) && aDateTime.isBefore(LocalDateTime.now().plusDays(7)) && (aDateTime.getHour()>=8) && aDateTime.getHour()<(22))
                {
                    int duration = KeyBoard.getInteger("Enter Duration(mins):");
                    if(duration <= swinClub.findSport(sport).getDurationLimit())
                    {    
                   
                       ArrayList<Court> availableCourts = swinClub.getAvailableCourts(sport,aDateTime,duration);
                
                       String delimiter = "";
                       if(availableCourts.isEmpty())
                       {
                           KeyBoard.println("No Courts Available for "+sport);
                        }   
                        else
                        {
                            KeyBoard.print("List of available courts:");
                            for(Court aCourt : availableCourts)
                            {
                                KeyBoard.print(delimiter+aCourt.getCourtId());
                                delimiter = ",";
                            }
                            KeyBoard.print("\n");
                        }
                    }
                    else
                    {
                        KeyBoard.println("Total booking for a day for "+sport+" cannot be more than allowed "+swinClub.findSport(sport).getDurationLimit()+"mins");
                    }
                }
                else
                {
                    KeyBoard.println("Error:Bookings can only be made for within 7 days from now and between 8am and 11pm! ");
                    
                }
            }
            else
            {
                KeyBoard.println("No such sport Exists!");
            }
        }
        catch(DateTimeParseException dtpe)
        {
            KeyBoard.println("Error: Enter Date time in format yyyy-mm-dd hh:mm");
            
          
        }
        
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
                KeyBoard.println("No Courts Available for "+sport);
            }
            else
            {
                KeyBoard.print("List of available courts:");
                for(Court aCourt : availableCourts)
                {
                    KeyBoard.print(delimiter+aCourt.getCourtId());
                    delimiter = ",";
                }
            }
        return availableCourts;
    }
    
    
    
    /**
     * Books a court for a member, for a sport, for a particular date & time
     */ 
    private void makeBooking()
    {
        int tEDuration = 0;
        int aMemberId = KeyBoard.getInteger("Enter member id:");
        if(swinClub.findMember(aMemberId).getMemberId()==aMemberId )
        {
            if( swinClub.findMember(aMemberId).getFinancial())
             {
                 String sport = KeyBoard.getString("Enter Name of the Sport:");
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
                    
                        LocalDateTime aDateTime = DateUtility.getLocalDateTime(KeyBoard.getString("Enter date and time for booking(yyyy-mm-dd hh:mm):"));
                        if(aDateTime.isAfter(LocalDateTime.now()) && aDateTime.isBefore(LocalDateTime.now().plusDays(7)) && (aDateTime.getHour()>=8) && aDateTime.getHour()<(22))
                        {
                            
                            int aDuration = KeyBoard.getInteger("Enter Duration(mins):");
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
                                KeyBoard.print("Select a court:");
                                ArrayList<Court> availableCourts = showAvailableCourts(sport,aDateTime,aDuration);
                                int courtId = KeyBoard.getInteger("\nEnter Court Number:");
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
                                    //swinClub.addToAllReadBookings(new Booking(aDateTime,aDuration,swinClub.findMember(aMemberId),aSport.findCourt(courtId)));
                                    aSport.findCourt(courtId).addBooking(new Booking(aDateTime,aDuration,swinClub.findMember(aMemberId),aSport.findCourt(courtId)));
                                    
                                    
                                    KeyBoard.println("Success!");
                                   // KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s","Booking date","Start Time","Booked Till","Member","Court"));
                                   //  KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s",aBooking.getBookingDateTime().getDayOfMonth()+"-"+aBooking.getBookingDateTime().getMonth().name()+"-"+aBooking.getBookingDateTime().getYear(),aBooking.getBookingDateTime().getHour()+":"+aBooking.getBookingDateTime().getMinute(),aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getHour()+":"+aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getMinute(),aBooking.getMember().getMemberId(),aBooking.getCourt().getCourtId()));
                                }
                                else
                                {
                                    KeyBoard.println("Error: Wrong court number!");
                                }
                            }
                            else
                            {
                                KeyBoard.println("Error: Total booking for the day cannot be more than allowed "+swinClub.findSport(sport).getDurationLimit()+"mins");
                            }                 
                        }
                        else
                        {
                            KeyBoard.println("Error:Bookings can only be made for within 7 days from now and between 8am and 11pm! ");
                        }
                    }
                    else
                    {
                        KeyBoard.println("Error: you do not play this sport!");
                    }
                    
                 }  
                 else
                 {
                     KeyBoard.println("Error: You do not play any of the sports offered!");
                 }
            }
            else
            {
                KeyBoard.println("Error: you are not financial!");
            }
        }
        else
        {
            KeyBoard.println("Error: No such member found!");
        }
    } 
    
    /**
     * Accepts memberId and Displays their bookings 
     */
    public void showMemberBookings()
    {
        int memberId = KeyBoard.getInteger("Enter Member Id:");
        if(swinClub.findMember(memberId).getMemberId()==memberId )
        {
            Member aMember = swinClub.findMember(memberId);
            ArrayList<Booking> aBookings = aMember.getBookings();
            if(!aBookings.isEmpty())
            {
                KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s","Booking date","Start Time","Booked Till","Member","Court"));
                for(Booking aBooking : aBookings)
                {
                    KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s",aBooking.getBookingDateTime().getDayOfMonth()+"-"+aBooking.getBookingDateTime().getMonth().name()+"-"+aBooking.getBookingDateTime().getYear(),aBooking.getBookingDateTime().getHour()+":"+aBooking.getBookingDateTime().getMinute(),aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getHour()+":"+aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getMinute(),aBooking.getMember().getMemberId(),aBooking.getCourt().getCourtId()));
                }
            }
            else
            {
                KeyBoard.println("Error:No Bookings exist!");
            }
        }
        else
        {
            KeyBoard.println("Error:No such member exists!");
        }
    }
    
    /**
     * Accepts courtId and displays bookings made for that court
     */
    private void showCourtBookings()
    {
        int courtId = KeyBoard.getInteger("Enter Court Id:");
        if(swinClub.findCourt(courtId).getCourtId() == courtId )
        {
            Court aCourt= swinClub.findCourt(courtId);
            ArrayList<Booking> aBookings = aCourt.getBookings();
            if(!aBookings.isEmpty())
            {
                KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s","Booking date","Start Time","Booked Till","Member","Court"));
                for(Booking aBooking : aBookings)
                {
                    KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s",aBooking.getBookingDateTime().getDayOfMonth()+"-"+aBooking.getBookingDateTime().getMonth().name()+"-"+aBooking.getBookingDateTime().getYear(),aBooking.getBookingDateTime().getHour()+":"+aBooking.getBookingDateTime().getMinute(),aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getHour()+":"+aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getMinute(),aBooking.getMember().getMemberId(),aBooking.getCourt().getCourtId()));
                }
            }
            else
            {
                KeyBoard.println("Error:No Bookings exist!");
            }
        }
        else
        {
            KeyBoard.println("Error:No such court exists!");
        }
    }
      
      
    /**
     * accepts member Id and booking number to delete a particular booking
     */  
    private void deleteBooking()
    {
        int aMemberId = KeyBoard.getInteger("Enter Member Id:");
        if(swinClub.findMember(aMemberId).getMemberId()==aMemberId )
        {
            Member aMember = swinClub.findMember(aMemberId);
            ArrayList<Booking> aBookings = aMember.getBookings();
            int count =0;
            if(!aBookings.isEmpty())
            {
                KeyBoard.println(String.format("%-20s%-30s%-20s%-30s%-20s%-20s","#","Booking date","Start Time","Booked Till","Member","Court"));
                for(Booking aBooking : aBookings)
                {
                    KeyBoard.println(String.format("%-20s%-30s%-20s%-30s%-20s%-20s",count,aBooking.getBookingDateTime().getDayOfMonth()+"-"+aBooking.getBookingDateTime().getMonth().name()+"-"+aBooking.getBookingDateTime().getYear(),aBooking.getBookingDateTime().getHour()+":"+aBooking.getBookingDateTime().getMinute(),aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getHour()+":"+aBooking.getBookingDateTime().plusMinutes(aBooking.getDuration()).getMinute(),aBooking.getMember().getMemberId(),aBooking.getCourt().getCourtId()));
                    count++;
                }
                int bookingId = KeyBoard.getInteger("Enter the booking # to delete!");
                
                if( bookingId <= count && bookingId >= 0)
                {
                    KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s","Booking date","Start Time","Booked Till","Member","Court"));
                    KeyBoard.println(String.format("%-30s%-20s%-30s%-20s%-20s",aBookings.get(bookingId).getBookingDateTime().getDayOfMonth()+"-"+aBookings.get(bookingId).getBookingDateTime().getMonth().name()+"-"+aBookings.get(bookingId).getBookingDateTime().getYear(),aBookings.get(bookingId).getBookingDateTime().getHour()+":"+aBookings.get(bookingId).getBookingDateTime().getMinute(),aBookings.get(bookingId).getBookingDateTime().plusMinutes(aBookings.get(bookingId).getDuration()).getHour()+":"+aBookings.get(bookingId).getBookingDateTime().plusMinutes(aBookings.get(bookingId).getDuration()).getMinute(),aBookings.get(bookingId).getMember().getMemberId(),aBookings.get(bookingId).getCourt().getCourtId()));
                    
                    Booking removedBooking = aBookings.remove(bookingId);
                    String dateTime = removedBooking.getBookingDateTime()+"";
                    String memberId = removedBooking.getMember().getMemberId()+"";
                    String courtId = removedBooking.getCourt().getCourtId()+"";
                    ArrayList<Booking> tempBook = swinClub.findCourt(Integer.parseInt(courtId)).getBookings() ;
                   try
                   {
                   for(Booking aBooking : tempBook)
                    {
                        if(aBooking.getBookingDateTime().toString().equals(dateTime) && (aBooking.getMember().getMemberId()) == (Integer.parseInt(memberId)) && aBooking.getCourt().getCourtId() == Integer.parseInt(courtId) )
                        {
                            swinClub.findCourt(Integer.parseInt(courtId)).getBookings().remove(aBooking);
                        }
                    }
                }
                catch(Exception e){}
                    KeyBoard.print(removedBooking+"");
                    KeyBoard.println("Success!");
                }
                else
                {
                    KeyBoard.println("Error:Incorrect booking#!");
                }
            }
            else
            {
                KeyBoard.println("Error:No Bookings exist!");
            }
        }
        else
        {
            KeyBoard.println("Error:No such member exists!");
        }
        
        
    }
       
} 