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
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author James Man
 */
public class NewAppointment {

    private int appointment_count;

    public void addNewAppointment(Appointment appointment) throws IOException {
        Gson gson = new GsonBuilder().create();

        Reader appointment_reader = Files.newBufferedReader(Paths.get("src//data//appointment.json"));
        Type appointment_listType = new TypeToken<ArrayList<Appointment>>() {
        }.getType();
        ArrayList<Appointment> appointments = gson.fromJson(appointment_reader, appointment_listType);

        boolean checking = false;

        for (Appointment a : appointments) {
            //System.out.println(a.appointment_date_to);
            //System.out.println(appointment.appointment_date_from.compareTo(a.appointment_date_to));
            //System.out.println(appointment.appointment_date_to.compareTo(a.appointment_date_from));
            if (appointment.getUser_id().equals(a.getUser_id()) && appointment.getDoctor().equals(a.getDoctor()) && appointment.getAppointment_date_from().compareTo(a.getAppointment_date_to()) <= 0 && appointment.getAppointment_date_to().compareTo(a.getAppointment_date_from()) <= 0) {
                checking = true;
                
                break;
            } else {
                checking = false;
            }
        }

        if (checking == true) {
            JOptionPane.showMessageDialog(null, "Same doctor can be only applied once within the overlapping range!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        } else {
            setAppointment_count(appointments.size() + 1);
            appointment.setAppointment_no(appointments.size() + 1);
            appointments.add(appointment);

            try ( Writer record_writer = new FileWriter("src//data//appointment.json")) {
                gson.toJson(appointments, record_writer);
            } catch (Exception e) {
                System.out.println("error meassage!");
            }
        }
    }

    public int getAppointment_count() {
        return appointment_count;
    }

    public void setAppointment_count(int appointment_count) {
        this.appointment_count = appointment_count;
    }

}
