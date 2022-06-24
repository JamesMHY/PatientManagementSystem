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
public class LoginContextTest {
    
    private LoginContext loginContext_doctor;
    private LoginContext loginContext_administrator;
    private LoginContext loginContext_secretary;
    private LoginContext loginContext_patient;
    
    public LoginContextTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        loginContext_doctor = new LoginContext(new DoctorLogin());
        loginContext_administrator = new LoginContext(new AdministratorLogin());
        loginContext_patient = new LoginContext(new PatientLogin());
        loginContext_secretary = new LoginContext(new SecretaryLogin());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Doctor Login
     */
    @Test
    public void testExecuteStrategyDoctor() {
        boolean status = true;
        boolean run_result = loginContext_doctor.executeStrategy("D");
        assertEquals(status, run_result);
    }
    
    /**
     * Administrator Login
     */
    @Test
    public void testExecuteStrategyAdministrator() {
        boolean status = true;
        boolean run_result = loginContext_administrator.executeStrategy("A");
        assertEquals(status, run_result);
    }
    
    /**
     * Administrator Login
     */
    @Test
    public void testExecuteStrategyPatient() {
        boolean status = true;
        boolean run_result = loginContext_patient.executeStrategy("P");
        assertEquals(status, run_result);
    }
    
    /**
     * Administrator Login
     */
    @Test
    public void testExecuteStrategySecretary() {
        boolean status = true;
        boolean run_result = loginContext_secretary.executeStrategy("S");
        assertEquals(status, run_result);
    }
    
}
