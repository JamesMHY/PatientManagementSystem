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
public class Rating {

    private String doctor_name;
    private String user_id;
    private Date date;
    private int rating;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Rating(String doctor_name, String user_id, Date date, int rating, String feedback) {
        this.doctor_name = doctor_name;
        this.user_id = user_id;
        this.date = date;
        this.rating = rating;
        this.feedback = feedback;
    }

    public void rate(int history_no) throws IOException, NoSuchAlgorithmException {
        ProvideRating.provideRating(this, history_no);
    }

}
