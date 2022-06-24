/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 *
 * @author James Man
 */
public class AdminRating {
    
    private String doctor_name;
    private Date date;
    private String feedback;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public AdminRating(String doctor_name, Date date, String feedback) {
        this.doctor_name = doctor_name;
        this.date = date;
        this.feedback = feedback;
    }
 
}
