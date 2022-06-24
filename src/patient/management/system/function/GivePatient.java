/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.io.IOException;
import java.util.logging.*;

/**
 * Command Pattern
 *
 * @author James Man
 */
public class GivePatient implements Order {

    private Stock stock;

    public GivePatient(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void update() {
        try {
            stock.give_patient();
        } catch (IOException ex) {
            Logger.getLogger(GivePatient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
