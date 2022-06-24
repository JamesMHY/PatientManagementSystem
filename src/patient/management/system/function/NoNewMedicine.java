/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import patient.management.system.function.MedicineRequest;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Null Object Pattern
 * @author James Man
 */
public class NoNewMedicine extends AbstractNewMedicine {

    @Override
    public boolean isNil() {
        return true;
    }

    @Override
    public void createNewMedicine(MedicineRequest medicine_request) throws IOException {
        Gson gson = new GsonBuilder().create();

        Reader request_reader = Files.newBufferedReader(Paths.get("src//data//medicine_request.json"));
        Type request_listType = new TypeToken<ArrayList<MedicineRequest>>() {
        }.getType();

        ArrayList<MedicineRequest> requests = gson.fromJson(request_reader, request_listType);

        requests.add(medicine_request);

        try ( Writer request_writer = new FileWriter("src//data//medicine_request.json")) {
            gson.toJson(requests, request_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
    }


    
}
