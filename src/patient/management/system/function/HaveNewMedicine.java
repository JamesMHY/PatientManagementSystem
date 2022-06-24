/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import patient.management.system.function.MedicineRequest;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Null Object Pattern
 * @author James Man
 */
public class HaveNewMedicine extends AbstractNewMedicine {
        
    public HaveNewMedicine(String new_medicine_name) {
        this.new_medicine_name = new_medicine_name;
    }
    
    @Override
    public boolean isNil() {
        return false;
    }
   
    @Override
    public void createNewMedicine(MedicineRequest medicine_request) throws IOException {
        JOptionPane.showMessageDialog(null, new_medicine_name + " is already in the system.", "WARNING", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
