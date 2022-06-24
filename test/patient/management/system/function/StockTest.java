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
public class StockTest {
    
    private Stock stock;
    
    public StockTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stock = new Stock("DoxaZosin",10);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of give_patient method, of class Stock.
     */
    @Test
    public void testGive_patient() throws Exception {
        stock.give_patient();
    }

    /**
     * Test of order_new method, of class Stock.
     */
    @Test
    public void testOrder_new() throws Exception {
        stock.order_new();
    }
    
}
