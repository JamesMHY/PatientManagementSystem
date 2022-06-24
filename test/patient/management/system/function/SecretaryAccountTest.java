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
public class SecretaryAccountTest {
    
    private SecretaryAccount secretaryAccount;
    private Account account_1;
    private AccountRequest account_request_1;
    private Account account_2;
    private AccountRequest account_request_2;
    
    public SecretaryAccountTest() {
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
        secretaryAccount = new SecretaryAccount();
        account_1 = new Account("secreatarytest", "Abc123", "Chan", "John", "test address", "S", 35, "M", "S0", "Active");
        account_request_1 = new AccountRequest("secreatarytest", new Date());
        account_2 = new Account("peterchan", "Aaa111", "Peter", "Chan", "", "S", 25, "M", "S1", "Active");
        account_request_2 = new AccountRequest("peterchan", new Date());
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
        boolean run_result = secretaryAccount.register(account_1, account_request_1);
        assertEquals(status, run_result);
    }
    
    /**
     * Test of register method, of class AdministratorAccount.
     * check whether account_2 is in the system
     */
    @Test
    public void testRegisterInSystem() throws Exception {
        boolean status = false;
        boolean run_result = secretaryAccount.register(account_2, account_request_2);
        assertEquals(status, run_result);
    }
    
}
