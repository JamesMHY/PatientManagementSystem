/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import patient.management.system.function.Account;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author James Man
 */
public class AccountApproval {

    public boolean changeStatus(String user_id, String status) throws NoSuchAlgorithmException, IOException {
        Gson gson = new GsonBuilder().create();

        Reader account_reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        Type account_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();

        ArrayList<Account> accounts = gson.fromJson(account_reader, account_listType);
        
        if (status.equals("Delete")) {
            int index = 0;
            for (Account a : accounts) {
                if (a.getUser_id().equals(user_id)) {
                    accounts.remove(index);
                    break;
                }
                index++;
            }
        } else {
            for (Account a : accounts) {
                if (a.getUser_id().equals(user_id)) {
                    a.setStatus(status);
                    break;
                }
            }
        }

        try ( Writer record_writer = new FileWriter("src//data//account.json")) {
            gson.toJson(accounts, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
        
        return true;
    }
    
    public boolean activeAll() throws IOException, NoSuchAlgorithmException {
        Gson gson = new GsonBuilder().create();

        Reader account_reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        Type account_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();

        ArrayList<Account> accounts = gson.fromJson(account_reader, account_listType);

        for (Account a : accounts) {
            if (a.getStatus().equals("Not Active")) {
                a.setStatus("Active");
            }
        }

        try ( Writer record_writer = new FileWriter("src//data//account.json")) {
            gson.toJson(accounts, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
        
        return true;
    }

}
