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
public class Appointment {
    
    private int appointment_no;
    private String user_id;
    private String doctor;
    private Date appointment_date_from;
    private Date appointment_date_to;
    private Date record_date;
    private boolean status;

    public int getAppointment_no() {
        return appointment_no;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setAppointment_no(int appointment_no) {
        this.appointment_no = appointment_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Date getAppointment_date_from() {
        return appointment_date_from;
    }

    public void setAppointment_date_from(Date appointment_date_from) {
        this.appointment_date_from = appointment_date_from;
    }

    public Date getAppointment_date_to() {
        return appointment_date_to;
    }

    public void setAppointment_date_to(Date appointment_date_to) {
        this.appointment_date_to = appointment_date_to;
    }
    
    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }
    
    public Appointment(int appointment_no, String user_id, String doctor, Date appointment_date_from, Date appointment_date_to, Date record_date, boolean status) {
        this.user_id = user_id;
        this.appointment_date_from = appointment_date_from;
        this.appointment_date_to = appointment_date_to;
        this.record_date = record_date;
        this.doctor = doctor;
        this.appointment_no = appointment_no;
        this.status = status;
    }
}
