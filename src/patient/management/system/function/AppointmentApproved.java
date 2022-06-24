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
public class AppointmentApproved {
    
    private int appointment_no;
    private String user_id;
    private String doctor;
    private Date date;
    private Date record_date;
    private boolean status;

    public int getAppointment_no() {
        return appointment_no;
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

    public Date getDate() {
        return date;
    }

    public void setAppointment_date_to(Date date) {
        this.date = date;
    }
    
    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public AppointmentApproved(int appointment_no, String user_id, String doctor, Date date, Date record_date, boolean status) {
        this.user_id = user_id;
        this.date = date;
        this.record_date = record_date;
        this.doctor = doctor;
        this.appointment_no = appointment_no;
        this.status = status;
    }
    
   
}
