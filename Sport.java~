import java.util.ArrayList;
/**
 * Represents Sports played in the club
 * 
 * @author Gurdev Singh
 * @version 0.3
 */
public class Sport
{
    private String name;
    private double usageFee;
    private double insuranceFee;
    private double affiliationFee;
    private ArrayList<Court> courts;
    protected int durationLimit;
    
    /**
     * initialises sport name
     */
    public Sport()
    {
        name = "no sport";
    }
    
    /**
     * initialises sport instance variables
     * 
     */
    public Sport(String aName,
                double aUsageFee,
                double aInsuranceFee,
                double aAffiliationFee,
                ArrayList<Court> aCourts)
    {
        name = aName;
        usageFee = aUsageFee;
        insuranceFee = aInsuranceFee;
        affiliationFee = aAffiliationFee;
        courts = aCourts;
    }
    
     public Sport(String aName,
                double aUsageFee,
                double aInsuranceFee,
                double aAffiliationFee)
     {
        name = aName;
        usageFee = aUsageFee;
        insuranceFee = aInsuranceFee;
        affiliationFee = aAffiliationFee;
        courts = new ArrayList<Court>();
     }           
    
    
    
   /**
    * Retrieves name of the sport
    * @returns name of the sport
    */ 
   public String getName()
   {
       return name;
   }
   
   /**
    * Retrieves courts belonging to the sport
    * @returns collection of courts belonging to the sport
    */ 
   public ArrayList<Court> getCourts()
   {
       return courts;
   }

   /**
    * Tells if a particular court belongs to a sport n
    * @param aCourtId teh courtid of the court to be checked if it belongs to a particular sport
    * @returns true if belongs to the sport, else false
    */ 
   public boolean isCourtInSport(int aCourtId)
   {
        for(Court aCourt : courts)
        {
            if(aCourt.getCourtId()==aCourtId)
                return true;    
        }
        return false;

   }
   
   /**
    * Retrieves a particular court belonging to the sport
    * @param courtId the court id for which the court object is retrieved
    * @returns court object 
    */ 
    public Court findCourt(int aCourtId)
    {
        for(Court aCourt:courts)
        {
            if(aCourt.getCourtId()==aCourtId)
            return aCourt;
        }
        return new Court(-1);
    }

    /**
    * Retrieves duration limit 
    * @returns duration limit for the sport
    */ 
   public int getDurationLimit()
   {
       return durationLimit;
   }
    
   /**
    * Returns information about the sport object
    * @returns string object containing information about sport
    */ 
    public String toString()
    {
        return "Name: "+name+"\nUsageFee: "+usageFee+"\nInsuranceFee: "+insuranceFee+"\n AffiliationFee: "+affiliationFee+"\nCourts:"+courts+"\nmaxDuration:"+durationLimit;
    }
}