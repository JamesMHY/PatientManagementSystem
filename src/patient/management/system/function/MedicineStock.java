/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

/**
 *
 * @author James Man
 */
public class MedicineStock {

    private String medicine_name;
    private int quantity;

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MedicineStock(String medicine_name, int quantity) {
        this.medicine_name = medicine_name;
        this.quantity = quantity;
    }

}
