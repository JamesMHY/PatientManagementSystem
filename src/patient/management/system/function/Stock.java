/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.system.function;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.*;
import java.util.ArrayList;

/**
 * Command Pattern
 *
 * @author James Man
 */
public class Stock {

    private String medicine_name;
    private int quantity;

    Gson gson = new GsonBuilder().create();
    Reader stock_reader;
    Reader request_reader;

    public void give_patient() throws IOException {
        stock_reader = Files.newBufferedReader(Paths.get("src//data//medicine_stock.json"));
        Type stock_listType = new TypeToken<ArrayList<MedicineStock>>() {
        }.getType();
        ArrayList<MedicineStock> stocks = gson.fromJson(stock_reader, stock_listType);

        for (MedicineStock s : stocks) {
            if (s.getMedicine_name().equals(medicine_name)) {
                //s.quantity -= quantity;
                //s.setQuantity(s.quantity);
                s.setQuantity(s.getQuantity() - quantity);
            }
        }

        try ( Writer record_writer = new FileWriter("src//data//medicine_stock.json")) {
            gson.toJson(stocks, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }

        System.out.println("[" + quantity + "] of  [" + medicine_name + "] is given to patient");

    }

    public void order_new() throws IOException {
        stock_reader = Files.newBufferedReader(Paths.get("src//data//medicine_stock.json"));
        Type stock_listType = new TypeToken<ArrayList<MedicineStock>>() {
        }.getType();
        ArrayList<MedicineStock> stocks = gson.fromJson(stock_reader, stock_listType);

        String[] medicine_detail = medicine_name.split(" - new");
        String medicine = medicine_detail[0];

        if (medicine_name.contains("new")) {
            stocks.add(new MedicineStock(medicine, quantity));
        } else {
            for (MedicineStock s : stocks) {
                if (s.getMedicine_name().equals(medicine_name)) {
                    //quantity = s.getQuantity() + quantity;
                    s.setQuantity(s.getQuantity() + quantity);
                }
            }
        }

        try ( Writer record_writer = new FileWriter("src//data//medicine_stock.json")) {
            gson.toJson(stocks, record_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }

        request_reader = Files.newBufferedReader(Paths.get("src//data//medicine_request.json"));
        Type request_listType = new TypeToken<ArrayList<MedicineRequest>>() {
        }.getType();
        ArrayList<MedicineRequest> requests = gson.fromJson(request_reader, request_listType);

        //for (int i = 0; i < requests.size(); i++) {

        //}

        int index = 0;
        for (MedicineRequest r : requests) {
            if (r.getMedicine_name().equals(medicine)) {
                requests.remove(index);
                break;
            }
            index++;
        }

        try ( Writer request_writer = new FileWriter("src//data//medicine_request.json")) {
            gson.toJson(requests, request_writer);
        } catch (Exception e) {
            System.out.println("error meassage!");
        }

        System.out.println("Order New [" + quantity + "] of  [" + medicine_name + "]");

    }

    public Stock(String medicine_name, int quantity) {
        this.medicine_name = medicine_name;
        this.quantity = quantity;
    }

}
