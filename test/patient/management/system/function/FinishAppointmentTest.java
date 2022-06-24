/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

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
public class FinishAppointmentTest {
    
    private FinishAppointment finishAppointment;
    
    public FinishAppointmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        finishAppointment = new FinishAppointment();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of FinishAppointment method, of class FinishAppointment.
     */
    @Test
    public void testFinishAppointment() throws Exception {
        boolean status = true;
        boolean run_result = finishAppointment.FinishAppointment(3);
        assertEquals(status, run_result);
    }
    
}
