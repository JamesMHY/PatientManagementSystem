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
public class ConsultationHistory {
    
    private Date date;
    private String user_id;
    private String doctor_name;
    private String disease;
    private String medicines;
    private String patient_name;
    private String quantity;
    private boolean rating;
    private String dosage;
    private String notes;
    private int history_no;

    public boolean isRating() {
        return rating;
    }

    public void setRating(boolean rating) {
        this.rating = rating;
    }

    public int getHistory_no() {
        return history_no;
    }

    public void setHistory_no(int history_no) {
        this.history_no = history_no;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }
    
    public ConsultationHistory(Date date, String disease, String doctor_name, String patient_name, String user_id, String medicines, String quantity, String dosage, String notes, int history_no, boolean rating) {
        this.date = date;
        this.disease = disease;
        this.doctor_name = doctor_name;
        this.user_id = user_id;
        this.medicines = medicines;
        this.patient_name = patient_name;
        this.quantity = quantity;
        this.dosage = dosage;
        this.notes = notes;
        this.history_no = history_no;
        this.rating = rating;
    }
    
}
