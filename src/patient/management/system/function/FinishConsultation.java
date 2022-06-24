/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author James Man
 */
public class FinishConsultation {

    public boolean AddHistory(ConsultationHistory history) throws NoSuchAlgorithmException, IOException {
        Gson gson = new GsonBuilder().create();

        Reader history_reader = Files.newBufferedReader(Paths.get("src//data//history.json"));
        Type history_listType = new TypeToken<ArrayList<ConsultationHistory>>() {
        }.getType();

        ArrayList<ConsultationHistory> histories = gson.fromJson(history_reader, history_listType);
        
        history.setHistory_no(histories.size() + 1);
        histories.add(history);

        try ( Writer record_writer = new FileWriter("src//data//history.json")) {
            gson.toJson(histories, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
        return true;
    }


}
