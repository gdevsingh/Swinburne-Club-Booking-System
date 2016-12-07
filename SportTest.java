

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SportTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SportTest
{
    /**
     * Default constructor for test class SportTest
     */
    Sport s1,s2,s3; 
    public SportTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        try{
       s1 = new Sport("squash",20.00,2.00,4.00);
       s2 = new Sport(null,20.00,2.00,4.00);
      s3 = new Sport("badminton",30.00,2.00,4.00);
    }
    catch(Exception e)
    {
    }
    }

    @Test
    public void testgetNames1()
    {
        assertEquals("squash",s1.getName());
    }
    @Test
    public void testgetNames2()
    {
        assertEquals(null,s2.getName());
    }
    @Test
    public void testgetNames3()
    {
        assertEquals("badminton",s3.getName());
    }
    
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
