/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James Man
 */
public class ProvideRatingTest {
    
    private ProvideRating provideRating;
    private Rating rating;
    
    
    public ProvideRatingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        provideRating = new ProvideRating();
        rating = new Rating("Serina James", "mandycheung", new Date(), 4, "very nice doctor");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of provideRating method, of class ProvideRating.
     */
    @Test
    public void testProvideRating() throws Exception {
        boolean status = true;
        boolean run_result = ProvideRating.provideRating(rating, 3);
        assertEquals(status, run_result);
    }
    
}
