import java.util.*;
/**
 * Represesnts squash sport object belonging to the club
 * 
 */
public class Squash extends Sport
{
    
   
     public Squash(String aName,
                double aUsageFee,
                double aInsuranceFee,
                double aAffiliationFee,
                ArrayList<Court> aCourts)
    {
        super(aName,aUsageFee,aInsuranceFee,aAffiliationFee,aCourts);
        durationLimit = 60;
    }
    
    /**
     * Displays information about the squash object
     */
    public String toString()
    {
        return super.toString();
    }

}
