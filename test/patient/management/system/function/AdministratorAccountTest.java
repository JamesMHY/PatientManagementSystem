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
public class AdministratorAccountTest {
    
    private AdministratorAccount administratorAccount;
    private Account account_1;
    private AccountRequest account_request_1;
    private Account account_2;
    private AccountRequest account_request_2;
    
    public AdministratorAccountTest() {
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
        administratorAccount = new AdministratorAccount();
        account_1 = new Account("admintest", "Abc123", "Chan", "John", "test address", "A", 35, "M", "A0", "Active");
        account_request_1 = new AccountRequest("admintest", new Date());
        account_2 = new Account("administrator01", "Abc123", "James", "Man", "", "A", 27, "M", "A1", "Active");
        account_request_2 = new AccountRequest("administrator01", new Date());
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
        boolean run_result = administratorAccount.register(account_1, account_request_1);
        assertEquals(status, run_result);
    }
    
    /**
     * Test of register method, of class AdministratorAccount.
     * check whether account_2 is in the system
     */
    @Test
    public void testRegisterInSystem() throws Exception {
        boolean status = false;
        boolean run_result = administratorAccount.register(account_2, account_request_2);
        assertEquals(status, run_result);
    }

}
