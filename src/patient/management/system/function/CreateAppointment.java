/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author James Man
 */
public class CreateAppointment {

    public boolean CreateFormalAppointment(AppointmentApproved approved_appointment) throws NoSuchAlgorithmException, IOException {
        Gson gson = new GsonBuilder().create();

        Reader appointment_reader = Files.newBufferedReader(Paths.get("src//data//appointment_approve.json"));
        Type appointment_listType = new TypeToken<ArrayList<AppointmentApproved>>() {
        }.getType();

        ArrayList<AppointmentApproved> appointments = gson.fromJson(appointment_reader, appointment_listType);
        
        approved_appointment.setAppointment_no(appointments.size() + 1);
        appointments.add(approved_appointment);

        try ( Writer record_writer = new FileWriter("src//data//appointment_approve.json")) {
            gson.toJson(appointments, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
        
        return true;
    }
    
    public boolean DeleteOldAppointment(int Appointment_no) throws NoSuchAlgorithmException, IOException {
        Gson gson = new GsonBuilder().create();

        Reader appointment_reader = Files.newBufferedReader(Paths.get("src//data//appointment.json"));
        Type appointment_listType = new TypeToken<ArrayList<Appointment>>() {
        }.getType();

        ArrayList<Appointment> appointments = gson.fromJson(appointment_reader, appointment_listType);

        for (Appointment a: appointments) {
            if (a.getAppointment_no() == Appointment_no) {
                a.setStatus(true);
            }
        }

        try ( Writer record_writer = new FileWriter("src//data//appointment.json")) {
            gson.toJson(appointments, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }
        
        return true;
    }
}
