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
public class FinishAppointment {

    public boolean FinishAppointment(int appointment_no) throws NoSuchAlgorithmException, IOException {
        Gson gson = new GsonBuilder().create();

        Reader appointment_reader = Files.newBufferedReader(Paths.get("src//data//appointment_approve.json"));
        Type appointment_listType = new TypeToken<ArrayList<AppointmentApproved>>() {
        }.getType();

        ArrayList<AppointmentApproved> appointments = gson.fromJson(appointment_reader, appointment_listType);
        
        for (AppointmentApproved a: appointments) {
            if (a.getAppointment_no() == appointment_no) {
                a.setStatus(true);
            }
        }

        try ( Writer record_writer = new FileWriter("src//data//appointment_approve.json")) {
            gson.toJson(appointments, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
        
        return true;

    }

}
