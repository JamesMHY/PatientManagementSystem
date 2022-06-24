/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import patient.management.system.LoginScreen;

/**
 *
 * @author James Man
 */
public class LoginController {
    
    /*private LoginScreen view;
    private Login model;
    private char[] pass;
    private String password;
    
    public LoginController(Login model, LoginScreen view){
        this.model = model;
        this.view = view;
    }
    
    public void setUserID(String user_id) {
        model.setUsername_input(user_id);
    }
    
    public String getUserID() {
        return model.getUsername_input();
    }
    
    public void setPassword(String password) {
        model.setPassword_input(password);
    }
    
    public String getPassword() {
        return model.getPassword_input();
    }
    
    public void checkingLogin() throws IOException, NoSuchAlgorithmException {
        //pass = view.getjPasswordField1().getPassword();
        //password = new String(pass);

        model.setUsername_input(getUserID());
        model.setPassword_input(getPassword());
    }*/
    
    private LoginScreen view;
    private Login model;
    private char[] pass;
    private String password;
    
    public LoginController(Login model, LoginScreen view){
        this.model = model;
        this.view = view;
    }
    
    public void setUserID(String user_id) {
        model.setUsername_input(user_id);
    }
    
    public String getUserID() {
        return model.getUsername_input();
    }
    
    public void setPassword(String password) {
        model.setPassword_input(password);
    }
    
    public String getPassword() {
        return model.getPassword_input();
    }
    
    public void checkingLogin() throws IOException, NoSuchAlgorithmException { 
        //pass = view.getjPasswordField1().getPassword();
        //password = new String(pass);

        model.setUsername_input(getUserID());
        model.setPassword_input(getPassword());

        try {
            String hash_password = toHexString(getSHA(getPassword()));
            
            Gson gson = new GsonBuilder().create();
            
            Reader reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
            Type listType = new TypeToken<ArrayList<Account>>(){}.getType();
            
            ArrayList<Account> accounts = gson.fromJson(reader, listType);  
            
            for(Account a : accounts) {
                if (model.getUsername_input().equals(a.getUser_id())) {
                    model.setUsername_checking(true);
                    if (hash_password.equals(a.getPassword())) {
                        model.setPassword_checking(true);
                        if (a.getStatus().equals("Active")) {
                            //this.setRoleApproval("A");
                            //this.setRoleUserid(a.getUser_id());
                            //this.setRoleName(a.getGiven_name() + " " + a.getSurname());
                            //this.setRoleLetter(a.getLetter());
                            model.setApproval("A");
                            model.setName(a.getGiven_name() + " " + a.getSurname());
                            model.setUser_id(a.getUser_id());
                            model.setLetter(a.getLetter());
                        } else if (a.getStatus().equals("Not Active")) {
                            model.setApproval("N");
                        } else if (a.getStatus().equals("Waiting For Delete")) {
                            model.setApproval("W");
                        }
                        break;
                        //return true;
                    } else {
                        model.setPassword_checking(false);
                    }
                    break;
                    //return false;
                } else {
                    model.setUsername_checking(false);
                    //return false;
                }
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("error meassage!");
        }
    }
    
    public void setRoleApproval(String approval) {
        model.setApproval(approval);
    }
    
    public String getRoleApproval() {
        return model.getApproval();
    }
    
    public boolean getUsername_checking() {
        return model.getUsername_checking();
    }
    
    public boolean getPassword_checking() {
        return model.getPassword_checking();
    }
    
    public String getRoleName() {
        return model.getName();
    }
    
    public void setRoleName(String name)  {
        model.setName(name);
    }
    
    public String getRoleLetter() {
        return model.getLetter();
    }
    
    public void setRoleLetter(String letter)  {
        model.setLetter(letter);
    }

    public void setRoleUserid(String user_id)  {
        model.setUser_id(user_id);
    }
    
    public String getRoleUserid() {
        return model.getUser_id();
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
