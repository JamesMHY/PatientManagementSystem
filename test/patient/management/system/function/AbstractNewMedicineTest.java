/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.io.IOException;
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
public class AbstractNewMedicineTest {
    
    private MedicineRequest medicineRequest;
    private AbstractNewMedicine abstractNewMedicine;

    public AbstractNewMedicineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        medicineRequest = new MedicineRequest("cough syrup", "Jessie Chan");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isNil method, of class AbstractNewMedicine.
     */
    @Test
    public void testIsNil() {
    }

    /**
     * Test of createNewMedicine method, of class AbstractNewMedicine.
     */
    @Test
    public void testCreateNewMedicine() throws Exception {
        AbstractNewMedicine new_medicine = NewMedicine.updateMedicine("cough syrup");
        new_medicine.createNewMedicine(medicineRequest);
    }

    public class AbstractNewMedicineImpl extends AbstractNewMedicine {

        public boolean isNil() {
            return false;
        }

        public void createNewMedicine(MedicineRequest medicine_request) throws IOException {
        }
    }
    
}
