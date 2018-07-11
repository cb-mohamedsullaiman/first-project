/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.CDL;
import org.json.JSONTokener;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class FormatTransformationUsingJSON {

    public static void createJSONFromCSV(Path sourcePath, Path jsonPath) throws IOException, ParseException {
        //Checking whether the csv file exists
        if (!sourcePath.toFile().exists()) {
            System.out.print("CSV file does not exists");
            throw new IOException();
        }
        JSONObject root = new JSONObject();
        BufferedReader bufferedReader = Files.newBufferedReader(sourcePath);
        CSVParser csvParser = CSVParser.parse(bufferedReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        JSONArray customerDetails = new JSONArray();
        //For each record
        for (CSVRecord csvRecord : csvParser) {
            JSONObject customerDetail = new JSONObject();
            String[] csvHeader = {"Customer ID", "Subscription Id", "Invoice Number", "Status", "Paid On", "Next Retry", "Recurring", "First Invoice", "Invoice Date", "Start Date", "Refunded Amount", "Amount", "Tax Total", "Customer First Name", "Customer Last Name", "Customer Email", "Customer Company"};
            String[] jsonHeader = {"Customer Id", "Subscription Id", "Invoice Id", "Status", "Paid On", "Next Retry", "Recurring", "First Invoice", "Invoice Date", "Start Date", "Refunded Amount", "Amount", "Tax Total", "First Name", "Last Name", "Email", "Company"};
            //For first 8 fields there is no alteration
            for (int i = 0; i < 8; i++) {
                customerDetail.put(jsonHeader[i], csvRecord.get(csvHeader[i]));
            }
            //We have to change the date format for next two fields
            SimpleDateFormat givenDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat desiredDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            customerDetail.put(jsonHeader[8], desiredDateFormat.format(givenDateFormat.parse(csvRecord.get(csvHeader[8]))));
            customerDetail.put(jsonHeader[9], desiredDateFormat.format(givenDateFormat.parse(csvRecord.get(csvHeader[9]))));
            //We have to change the value for the next three fields
            customerDetail.put(jsonHeader[10], Float.parseFloat(csvRecord.get(csvHeader[10])) / 10);
            customerDetail.put(jsonHeader[11], Float.parseFloat(csvRecord.get(csvHeader[11])) / 100);
            customerDetail.put(jsonHeader[12], Float.parseFloat(csvRecord.get(csvHeader[12])) / 100);
            //This is for the creating a new json object with ustomer personal details
            JSONObject customerPersonalDetails = new JSONObject();
            for (int i = 13; i < 16; i++) {
                customerPersonalDetails.put(jsonHeader[i], csvRecord.get(csvHeader[i]));
            }
            //This is for single customer detail
            customerDetail.put("Customer Detail", customerPersonalDetails);
            //This will add the single customer detail to customer details array
            customerDetails.put(customerDetail);
        }
        root.put("Customer Details", customerDetails);
        //This will create json file
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(jsonPath)) {
            root.write(bufferedWriter);
        }

    }

    public static void createCSVUsingJSON(Path jsonPath, Path destinationPath) throws IOException {
        //It will check whether the json file exists or not
        if (!jsonPath.toFile().exists()) {
            System.out.println("JSON file does not exists");
            throw new IOException();
        }
        JSONObject root = new JSONObject(new JSONTokener(Files.newBufferedReader(jsonPath)));
        JSONArray jsonCustomerDetails = root.getJSONArray("Customer Details");
        String headerArray[] = {"Customer Id", "Subscription Id", "Invoice Id", "Invoice Date", "Start Date", "Amount", "Status", "Paid On", "Next Retry", "Refunded Amount", "Recurring", "First Invoice", "Tax Total", "Customer Detail"};
        JSONArray header = new JSONArray(headerArray);
        //This is for adding the header to the first line of the csv file
        String csvFile = "";
        for (int i = 0; i < headerArray.length; i++) {
            csvFile += headerArray[i];
            if (i < headerArray.length - 1) {
                csvFile += ",";
            }
        }
        csvFile += "\n";
        //This is for adding the entire csv file without header
        csvFile = csvFile + CDL.toString(header, jsonCustomerDetails);
        //This is for writing the entire csv file into the destination path
        FileUtils.writeStringToFile(destinationPath.toFile(), csvFile, (String) null);

    }

    public static void main(String args[]) throws IOException, ParseException {
        Path sourcePath = Paths.get(System.getProperty("user.home") + "/Downloads/Input.csv");
        Path jsonPath = Paths.get(System.getProperty("user.home") + "/sample/config.json");
        createJSONFromCSV(sourcePath, jsonPath);
        
        Path destinationPath = Paths.get(System.getProperty("user.home") + "/sample/output.csv");
        createCSVUsingJSON(jsonPath, destinationPath);
    }
}
