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
public class AccountApprovalTest {
    
    private AccountApproval accountApproval;
    
    public AccountApprovalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        accountApproval = new AccountApproval();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of changeStatus method, of class AccountApproval.
     * check the status whether is changed 
     */
    @Test
    public void testChangeStatus() throws Exception {
        boolean status = true;
        boolean run_result = accountApproval.changeStatus("mandycheung", "Active");
        assertEquals(status, run_result);
    }
    
    /**
     * Test of changeStatus method, of class AccountApproval.
     * check the account whether is deleted
     */
    @Test
    public void testChangeStatusDelete() throws Exception {
        boolean status = true;
        boolean run_result = accountApproval.changeStatus("ansonlo", "Delete");
        assertEquals(status, run_result);
    }

    /**
     * Test of activeAll method, of class AccountApproval.
     * check whether active all account
     */
    @Test
    public void testActiveAll() throws Exception {
        boolean status = true;
        boolean run_result = accountApproval.activeAll();
        assertEquals(status, run_result);
        
    }
    
}
