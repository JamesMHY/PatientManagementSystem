/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import patient.management.system.function.NoNewMedicine;
import patient.management.system.function.MedicineStock;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Null Object Pattern
 * @author James Man
 */
public class NewMedicine {

    public static AbstractNewMedicine updateMedicine(String new_medicine_name) throws IOException {

        Gson gson = new GsonBuilder().create();

        Reader stock_reader = Files.newBufferedReader(Paths.get("src//data//medicine_stock.json"));
        Type stock_listType = new TypeToken<ArrayList<MedicineStock>>() {
        }.getType();

        ArrayList<MedicineStock> stocks = gson.fromJson(stock_reader, stock_listType);

        for (MedicineStock s : stocks) {
            if (s.getMedicine_name().equals(new_medicine_name)) {
                return new HaveNewMedicine(new_medicine_name);
            }
        }
        return new NoNewMedicine();
    }

}
