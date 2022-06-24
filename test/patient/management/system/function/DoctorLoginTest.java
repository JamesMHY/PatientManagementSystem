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
public class DoctorLoginTest {
    
    private LoginContext loginContext;
    
    public DoctorLoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        loginContext = new LoginContext(new DoctorLogin());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class DoctorLogin.
     */
    @Test
    public void testLogin() {
        boolean status = true;
        boolean run_result = loginContext.executeStrategy("D");
        assertEquals(status, run_result);
    }
    
}
