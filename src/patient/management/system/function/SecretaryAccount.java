/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import patient.management.system.function.NewAccount;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import static patient.management.system.function.Login.getSHA;
import static patient.management.system.function.Login.toHexString;

/**
 * Factory Pattern
 * @author James Man
 */
public class SecretaryAccount implements NewAccount {
    
    @Override
    public boolean register(Account account, AccountRequest account_request) throws IOException, NoSuchAlgorithmException {
        Gson gson = new GsonBuilder().create();

        Reader account_reader = Files.newBufferedReader(Paths.get("src//data//account.json"));
        Type account_listType = new TypeToken<ArrayList<Account>>() {
        }.getType();

        ArrayList<Account> accounts = gson.fromJson(account_reader, account_listType);

        Reader record_reader = Files.newBufferedReader(Paths.get("src//data//create_account_record.json"));
        Type record_listType = new TypeToken<ArrayList<AccountRequest>>() {
        }.getType();

        ArrayList<AccountRequest> records = gson.fromJson(record_reader, record_listType);

        boolean checking = false;

        for (Account a : accounts) {
            if (account.getUser_id().equals(a.getUser_id())) {
                checking = true;

                break;
            } else {
                checking = false;
            }
        }

        if (checking == true) {
            return false;
        } else {
            int no = 0;
            String unique_id_no = null;

            for (Account a : accounts) {
                if (a.getLetter().equals("S")) {
                    no++;
                    unique_id_no = a.getUnique_id_no();
                }
            }

            account.setUnique_id_no(account.getLetter() + (Integer.parseInt(unique_id_no.substring(1)) + 1));

            String hash_password = toHexString(getSHA(account.getPassword()));
            account.setPassword(hash_password);
            accounts.add(account);

            try ( Writer account_writer = new FileWriter("src//data//account.json")) {
                gson.toJson(accounts, account_writer);
            } catch (Exception e) {
                System.out.println("error meassage!");
            }

            records.add(account_request);

            try ( Writer record_writer = new FileWriter("src//data//create_account_record.json")) {
                gson.toJson(records, record_writer);
            } catch (Exception e) {
                System.out.println("error meassage!");
            }

            return true;
        }
    }
    
}
