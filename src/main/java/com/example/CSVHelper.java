package com.example;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CSVHelper {

    public static List<String[]> readCSV(String filePath) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> allData = reader.readAll();
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("d/M/yyyy");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");


            for (String[] row : allData) {
                try {
                    row[1] = outputFormat.format(dateFormat1.parse(row[1]));
                } catch (ParseException e1) {
                    try {
                        row[1] = outputFormat.format(dateFormat2.parse(row[1]));
                    } catch (ParseException e2) {
                        System.err.println("Failed to parse date for row");
                    }
                }
            }
            return allData;
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeCSV(String filePath, List<String[]> data) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(data);
            System.out.println("Data written " + filePath);
        }
    }
}
