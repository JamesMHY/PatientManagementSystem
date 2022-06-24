/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class CreateAppointmentTest {
    
    private CreateAppointment createAppointment;
    private AppointmentApproved approved_appointment;
    
    public CreateAppointmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test of CreateFormalAppointment method, of class CreateAppointment.
     * set up a new approved appointment like the secretary created
     */
    @Before
    public void setUp() throws ParseException {
        createAppointment = new CreateAppointment();
        approved_appointment = new AppointmentApproved(0, "sallytam", "Serina James", new Date(), new SimpleDateFormat("d/M/y").parse("1/2/2021"), false);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of CreateFormalAppointment method, of class CreateAppointment.
     * Whether create a formal appointment successfully
     */
    @Test
    public void testCreateFormalAppointment() throws Exception {
        boolean status = true;
        boolean run_result = createAppointment.CreateFormalAppointment(approved_appointment);
        assertEquals(status, run_result);
    }

    /**
     * Test of DeleteOldAppointment method, of class CreateAppointment.
     * Whether delete old appointment successfully
     */
    @Test
    public void testDeleteOldAppointment() throws Exception {
        boolean status = true;
        boolean run_result = createAppointment.DeleteOldAppointment(1);
        assertEquals(status, run_result);
    }
    
    
}
