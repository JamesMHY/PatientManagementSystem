/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;


import patient.management.system.function.Account;
import com.google.gson.*; 
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.ArrayList;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 *
 * @author James Man
 */
public final class Login {
    
    private String username_input;
    private String password_input;
    private String name;
    private String user_id;
    private String letter;
    private Boolean username_checking;
    private Boolean password_checking;
    private String approval;
    
    public Login () throws IOException {
        //this.username_input = username_input;
        //this.password_input = password_input;
        
        /*try {
            String hash_password = toHexString(getSHA(password_input));
            
            Gson gson = new GsonBuilder().create();
            
            Reader reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
            Type listType = new TypeToken<ArrayList<Account>>(){}.getType();
            
            ArrayList<Account> accounts = gson.fromJson(reader, listType);  
            
            for(Account a : accounts) {
                if (username_input.equals(a.getUser_id())) {
                    this.setUsername_checking(true);
                    if (hash_password.equals(a.getPassword())) {
                        this.setPassword_checking(true);
                        if (a.getStatus().equals("Active")) {
                            this.setApproval("A");
                            this.setName(a.getGiven_name() + " " + a.getSurname());
                            this.setUser_id(a.getUser_id());
                            this.setLetter(a.getLetter());
                        } else if (a.getStatus().equals("Not Active")) {
                            this.setApproval("N");
                        } else if (a.getStatus().equals("Waiting For Delete")) {
                            this.setApproval("W");
                        }
                        break;
                        //return true;
                    } else {
                        this.setPassword_checking(false);
                    }
                    break;
                    //return false;
                } else {
                    this.setUsername_checking(false);
                    //return false;
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("error meassage!");
        }*/
        //return true;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public Boolean getUsername_checking() {
        return username_checking;
    }

    public void setUsername_checking(Boolean username_checking) {
        this.username_checking = username_checking;
    }

    public Boolean getPassword_checking() {
        return password_checking;
    }

    public void setPassword_checking(Boolean password_checking) {
        this.password_checking = password_checking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUser_id() {
        return user_id;
    }
    
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getUsername_input() {
        return username_input;
    }

    public void setUsername_input(String username_input) {
        this.username_input = username_input;
    }

    public void setPassword_input(String password_input) {
        this.password_input = password_input;
    }

    public String getPassword_input() {
        return password_input;
    }
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
        BigInteger number = new BigInteger(1, hash);  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
        
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  
    } 
}
