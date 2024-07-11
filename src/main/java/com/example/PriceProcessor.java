package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PriceProcessor {

    private static final Random RANDOM = new Random();

    public static List<String[]> sampleData(List<String[]> allData) {
        int maxStartIndex = allData.size() - 30;
        if (maxStartIndex <= 0) {
            throw new IllegalArgumentException("Not enought data points");
        }

        int startIndex = RANDOM.nextInt(maxStartIndex);

        return allData.subList(startIndex, startIndex + 30);
    }

    public static List<String[]> detectOutliers(List<String[]> sampledData) {
        // Mean
        double sum = 0;
        for (String[] data : sampledData) {
            sum += Double.parseDouble(data[2]);
        }
        double mean = sum / sampledData.size();

        // Standard Deviation
        double sumSquaredDeviations = 0;
        for (String[] data : sampledData) {
            double value = Double.parseDouble(data[2]);
            sumSquaredDeviations += Math.pow(value - mean, 2);
        }
        double variance = sumSquaredDeviations / (sampledData.size() - 1);
        double std = Math.sqrt(variance);
        double threshold = 2 * std;

        // Outliers
        List<String[]> outliers = new ArrayList<>();
        for (String[] data : sampledData) {
            double price = Double.parseDouble(data[2]);
            double deviation = price - mean;
            if (Math.abs(deviation) > threshold) {
                double percentDeviation = (Math.abs(deviation) - threshold) / threshold * 100;
                String formattedMean = String.format("%.2f", mean);
                String formattedDeviation = String.format("%.2f", deviation);
                String formattedPercentDeviation = String.format("%.2f", percentDeviation);
                outliers.add(new String[]{data[0], data[1], data[2], formattedMean, formattedDeviation, formattedPercentDeviation});
            }
        }

        return outliers;
    }

    public static void processFiles(String inputDir, String outputDir, int numFiles) throws IOException {

        List<File> files = getAllCSVFiles(new File(inputDir));

        if (files.isEmpty()) {
            throw new FileNotFoundException("No files have been found in the directory");
        }

        int filesToProcess = Math.min(files.size(), numFiles);
        for (int i = 0; i < filesToProcess; i++) {
            try {
                List<String[]> allData = CSVHelper.readCSV(files.get(i).getPath());
                List<String[]> sampleData = sampleData(allData);
                List<String[]> outlierData = detectOutliers(sampleData);

                String outputFile = outputDir + "/outliers_" + files.get(i).getName();
                CSVHelper.writeCSV(outputFile, outlierData);
            } catch (Exception e) {
                System.err.println("Error processing file " + files.get(i).getName() + ": " + e.getMessage());
            }
        }
    }

    public static List<File> getAllCSVFiles(File directory) {
        List<File> csvFiles = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    csvFiles.addAll(getAllCSVFiles(file));
                } else if (file.getName().endsWith(".csv")) {
                    csvFiles.add(file);
                }
            }
        }
        return csvFiles;
    }

    public static void main(String[] args) {
        String inputDirectory = "./data";
        String outputDirectory = "./output";

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of files to process: ");
        int numFiles = scanner.nextInt();
        scanner.close();

        try {
            Path path = Paths.get(outputDirectory);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }

            processFiles(inputDirectory, outputDirectory, numFiles);
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
