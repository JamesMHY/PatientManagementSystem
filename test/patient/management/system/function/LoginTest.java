/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import patient.management.system.LoginScreen;

/**
 *
 * @author James Man
 */
public class LoginTest {
    
private Login model_1;
    private Login model_2;
    private Login model_3;
    private Login model_4;
    private Login model_5;
    
    private LoginScreen view;
    private LoginController loginController_1;
    private LoginController loginController_2;
    private LoginController loginController_3;
    private LoginController loginController_4;
    private LoginController loginController_5;
    
    public LoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException, NoSuchAlgorithmException {
        model_1 = new Login();
        model_2 = new Login();
        model_3 = new Login();
        model_4 = new Login();
        model_5 = new Login();
        
        view = new LoginScreen();
        loginController_1 = new LoginController(model_1, view);
        loginController_2 = new LoginController(model_2, view);
        loginController_3 = new LoginController(model_3, view);
        loginController_4 = new LoginController(model_4, view);
        loginController_5 = new LoginController(model_5, view);

        loginController_1.setUserID("mandycheung"); //correct and Active account
        loginController_1.setPassword("Abc123");
        loginController_1.checkingLogin();
        
        loginController_2.setUserID("1"); //wrong account
        loginController_2.setPassword("1");
        loginController_2.checkingLogin();
        
        loginController_3.setUserID("ansonlo"); // Not Active account
        loginController_3.setPassword("Abc123");
        loginController_3.checkingLogin();
        
        loginController_4.setUserID("mandycheung"); //correct username and wrong password
        loginController_4.setPassword("1");
        loginController_4.checkingLogin();
        
        loginController_5.setUserID("sallytam"); //waiting for delete account
        loginController_5.setPassword("Abc123");
        loginController_5.checkingLogin();
    }
    
    @After
    public void tearDown() {
    }

    public void testGetApproval() {
    }
    
    /**
     * Test of getApproval method, of class Login.
     * Active account
     */ 
    @Test
    public void testGetApprovalActive() {
        String approval_status = "A"; //Active
        String run_result = loginController_1.getRoleApproval();
        assertEquals(approval_status, run_result);
    }
    
    /**
     * Test of getApproval method, of class Login.
     * Not Active account
     */
    @Test
    public void testGetApprovalNotActive() {
        String approval_status = "N"; //Not Active
        String run_result = loginController_3.getRoleApproval();
        assertEquals(approval_status, run_result);
    }
    
    /**
     * Test of getApproval method, of class Login.
     * Not Active account
     */
    @Test
    public void testGetApprovalWaitingForDelete() {
        String approval_status = "W"; //Waiting For Delete
        String run_result = loginController_5.getRoleApproval();
        assertEquals(approval_status, run_result);
    }
    
    public void testSetApproval() {
    }

    /**
     * Test of getUsername_checking method, of class Login.
     * username correct
     */
    @Test
    public void testGetUsername_checkingSuccess() {
        boolean status = true;
        boolean run_result = loginController_1.getUsername_checking();
        assertEquals(status, run_result);
    }
    
    public void testGetUsername_checkingFail() {
        boolean status = false;
        boolean run_result = loginController_2.getUsername_checking();
        assertEquals(status, run_result);
    }

    public void testSetUsername_checking() {
    }

    /**
     * Test of getPassword_checking method, of class Login.
     * password correct
     */
    @Test
    public void testGetPassword_checkingSuccess() {
        boolean status = true;
        boolean run_result = loginController_1.getPassword_checking();
        assertEquals(status, run_result);
    }
    
    /**
     * Test of getPassword_checking method, of class Login.
     * password wrong
     */
    @Test
    public void testGetPassword_checkingFail() {
        boolean status = false;
        boolean run_result = loginController_4.getPassword_checking();
        assertEquals(status, run_result);
    }

    public void testSetPassword_checking() {
    }

    public void testGetName() {
    }

    public void testSetName() {
    }

    public void testGetUser_id() {
    }

    public void testSetUser_id() {
    }

    public void testGetLetter() {
    }

    public void testSetLetter() {
    }

    public void testGetSHA() throws Exception {
    }

    public void testToHexString() {

    }
    
    
}
