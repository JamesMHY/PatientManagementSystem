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
public class MedicineRequest {
    
    private String medicine_name;
    private String doctor_name;

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public MedicineRequest(String medicine_name, String doctor_name) {
        this.medicine_name = medicine_name;
        this.doctor_name = doctor_name;
    }
    
}
