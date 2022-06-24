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
public class GiveFeedback {

    public boolean AdminGiveFeedback(AdminRating adminrating) throws NoSuchAlgorithmException, IOException {
        Gson gson = new GsonBuilder().create();

        Reader rating_reader = Files.newBufferedReader(Paths.get("src//data//admin_rating.json"));
        Type rating_listType = new TypeToken<ArrayList<AdminRating>>() {
        }.getType();

        ArrayList<AdminRating> rating = gson.fromJson(rating_reader, rating_listType);
        
        boolean checking = false;

        for (AdminRating ar : rating) {
            if (ar.getDoctor_name().equals(adminrating.getDoctor_name())) {
                ar.setFeedback(adminrating.getFeedback());
                ar.setDate(adminrating.getDate());
                checking = false;
                
                break;
            } else {
                checking = true;
            }
        }
        
        if (checking == true) {
            rating.add(adminrating);
        }

        try ( Writer record_writer = new FileWriter("src//data//admin_rating.json")) {
            gson.toJson(rating, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
        
        return true;
    }

}
