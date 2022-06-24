/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
public class NewAccountTest {
    
    private NewAccountFactory newAccountFactory;
    private Account account;
    private NewAccount newAccount;
    private AccountRequest accountRequest;
    
    public NewAccountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        account = new Account("testaccount","Test123","TT","test","test address", "P", 50, "M", "P0", "Not Active");
        newAccountFactory = new NewAccountFactory();
        newAccount = newAccountFactory.getRole("P");
        accountRequest = new AccountRequest("testaccount", new Date());
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of register method, of class NewAccount.
     */
    @Test
    public void testRegister() throws Exception {
        boolean status = true;
        boolean run_result = newAccount.register(account, accountRequest);
        assertEquals(status, run_result);
    }

    public class NewAccountImpl implements NewAccount {

        public boolean register(Account account, AccountRequest account_request) throws IOException, NoSuchAlgorithmException {
            return false;
        }
    }
    
}
