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
public class FinishConsultationTest {
    
    private FinishConsultation finishConsultation;
    private ConsultationHistory consultationHistory;
    
    public FinishConsultationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        finishConsultation = new FinishConsultation();
        consultationHistory = new ConsultationHistory(new Date(), "flu", "Raymond Cheung", "Sally Tam", "sallytam", "cough ingots,rhinitis ingots", "12,24", "3 per day at least 8 hours between each dose,4 per day at least 6 hours between each dose", "", 1, false);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of AddHistory method, of class FinishConsultation.
     */
    @Test
    public void testAddHistory() throws Exception {
        boolean status = true;
        boolean run_result = finishConsultation.AddHistory(consultationHistory);
        assertEquals(status, run_result);
    }
    
}
