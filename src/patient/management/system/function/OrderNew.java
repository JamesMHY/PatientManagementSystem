/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command Pattern
 *
 * @author James Man
 */
public class OrderNew implements Order {

    private Stock stock;

    public OrderNew(Stock stock) {
        this.stock = stock;
    }

    public void update() {
        try {
            stock.order_new();
        } catch (IOException ex) {
            Logger.getLogger(OrderNew.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
