import java.util.ArrayList;
/**
 * Represesnts badminton sport object belonging to the club
 * 
 */
public class Badminton extends Sport
{
 //   int durationLimit ;
   
    public Badminton(String aName,
                double aUsageFee,
                double aInsuranceFee,
                double aAffiliationFee,
                ArrayList<Court> aCourts)
    {
        super(aName,aUsageFee,aInsuranceFee,aAffiliationFee,aCourts);
        durationLimit = 90;
    }
    
    /**
     * Displays information about the badminton object
     */
    public String toString()
    {
       return super.toString();
    }

}
