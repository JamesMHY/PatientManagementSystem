/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import patient.management.system.function.Rating;
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
 * @author james_man
 */
public class ProvideRating {
    
        public static boolean provideRating(Rating rating, int history_no) throws IOException, NoSuchAlgorithmException {
        Gson gson = new GsonBuilder().create();

        Reader rating_reader = Files.newBufferedReader(Paths.get("src//data//rating.json"));
        Type rating_listType = new TypeToken<ArrayList<Rating>>() {}.getType();

        ArrayList<Rating> ratings = gson.fromJson(rating_reader, rating_listType);
        ratings.add(rating);
        
        try ( Writer record_writer = new FileWriter("src//data//rating.json")) {
            gson.toJson(ratings, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
        
        Reader history_reader = Files.newBufferedReader(Paths.get("src//data//history.json"));
        Type history_listType = new TypeToken<ArrayList<ConsultationHistory>>() {}.getType();

        ArrayList<ConsultationHistory> histories = gson.fromJson(history_reader, history_listType);

        for (ConsultationHistory h: histories) {
            if (h.getHistory_no() == history_no) {
                h.setRating(true);
            }
        }
        
        try ( Writer record_writer = new FileWriter("src//data//history.json")) {
            gson.toJson(histories, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
        
        return true;
    }
}
