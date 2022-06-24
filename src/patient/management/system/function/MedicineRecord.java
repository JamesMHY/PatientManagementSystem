/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.util.Date;

/**
 *
 * @author James Man
 */
public class MedicineRecord {
    
    private String medicine_name;
    private String patient_name;
    private String unique_id_no;
    private String doctor_name;
    private int quantity;
    private Date record_date;

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getUnique_id_no() {
        return unique_id_no;
    }

    public void setUnique_id_no(String unique_id_no) {
        this.unique_id_no = unique_id_no;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }
    
    public MedicineRecord(String medicine_name, String patient_name, String unique_id_no, String doctor_name, int quantity, Date record_date) {
        this.medicine_name = medicine_name;
        this.patient_name = patient_name;
        this.unique_id_no = unique_id_no;
        this.doctor_name = doctor_name;
        this.quantity = quantity;
        this.record_date = record_date;
    }
    
}
