import java.util.ArrayList;
/**
 * Represesnts tennis sport object belonging to the club
 * 
 */
public class Tennis extends Sport
{
 //   int durationLimit ;
   
    public Tennis(String aName,
                double aUsageFee,
                double aInsuranceFee,
                double aAffiliationFee,
                ArrayList<Court> aCourts)
    {
        super(aName,aUsageFee,aInsuranceFee,aAffiliationFee,aCourts);
        durationLimit = 120;
    }
    
    /**
     * Displays information about the tennis object
     */
    public String toString()
    {
        return super.toString();
    }

}
