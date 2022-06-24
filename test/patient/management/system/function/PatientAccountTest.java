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
public class PatientAccountTest {
    
    private PatientAccount patientAccount;
    private Account account_1;
    private AccountRequest account_request_1;
    private Account account_2;
    private AccountRequest account_request_2;
    
    public PatientAccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
    * account_1 is a account that is not in the system yet
    * account_2 is already in the system
    */
    @Before
    public void setUp() {
        patientAccount = new PatientAccount();
        account_1 = new Account("patienttest", "Abc123", "Chan", "John", "test address", "P", 35, "M", "P0", "Active");
        account_request_1 = new AccountRequest("patienttest", new Date());
        account_2 = new Account("mandycheung", "Abc123", "Mandy", "Cheung", "test", "P", 28, "F", "P4", "Active");
        account_request_2 = new AccountRequest("mandycheung", new Date());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of register method, of class AdministratorAccount.
     * check whether account_1 is not in the system
     */
    @Test
    public void testRegisterNotInSystem() throws Exception {
        boolean status = true;
        boolean run_result = patientAccount.register(account_1, account_request_1);
        assertEquals(status, run_result);
    }
    
    /**
     * Test of register method, of class AdministratorAccount.
     * check whether account_2 is in the system
     */
    @Test
    public void testRegisterInSystem() throws Exception {
        boolean status = false;
        boolean run_result = patientAccount.register(account_2, account_request_2);
        assertEquals(status, run_result);
    }
    
}
