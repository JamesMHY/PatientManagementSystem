/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author James Man
 */
public class Account {

    private String given_name;
    private String surname;
    private String address;
    private String unique_id_no;
    private String user_id;
    private String letter;
    private String password;
    private int age;
    private String sex;
    private String status;

    public String getGiven_name() {
        return given_name;
    }
    
    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnique_id_no() {
        return unique_id_no;
    }

    public void setUnique_id_no(String unique_id_no) {
        this.unique_id_no = unique_id_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account(String user_id, String password, String surname, String given_name, String address, String letter, int age, String sex, String unique_id_no, String status) {
        this.user_id = user_id;
        this.password = password;
        this.surname = surname;
        this.given_name = given_name;
        this.address = address;
        this.letter = letter;
        this.unique_id_no = unique_id_no;
        this.age = age;
        this.sex = sex;
        this.status = status;
    }
    
}
