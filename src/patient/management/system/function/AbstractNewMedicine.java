/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import patient.management.system.function.MedicineRequest;
import java.io.IOException;

/**
 *
 * @author James Man
 */
public abstract class AbstractNewMedicine {
    protected String new_medicine_name;
    public abstract boolean isNil();
    public abstract void createNewMedicine(MedicineRequest medicine_request) throws IOException;
}
