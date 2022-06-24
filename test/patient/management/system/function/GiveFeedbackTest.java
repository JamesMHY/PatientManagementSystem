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
public class GiveFeedbackTest {
    
    private GiveFeedback giveFeedback;
    private AdminRating adminrating;
    
    public GiveFeedbackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        giveFeedback = new GiveFeedback();
        adminrating = new AdminRating("Raymond Cheung", new Date(),"nice");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of AdminGiveFeedback method, of class GiveFeedback.
     * give feedback to doctor by admin function whether success or fail
     */
    @Test
    public void testAdminGiveFeedback() throws Exception {
        boolean status = true;
        boolean run_result = giveFeedback.AdminGiveFeedback(adminrating);
        assertEquals(status, run_result);
    }
    
}
