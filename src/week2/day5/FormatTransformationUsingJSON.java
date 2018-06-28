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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.CDL;
import org.json.JSONException;
import org.json.JSONTokener;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class FormatTransformationUsingJSON {
    public static void createJSONFromCSV(Path sourcePath){
        JSONObject root = new JSONObject();
        try(BufferedReader bufferedReader = Files.newBufferedReader(sourcePath); CSVParser csvParser = CSVParser.parse(bufferedReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())){
            JSONArray customerDetails = new JSONArray();
            for(CSVRecord csvRecord : csvParser){
                JSONObject customerDetail = new JSONObject();
                customerDetail.put("Customer Id", csvRecord.get("Customer Id"));
                customerDetail.put("Subscription Id", csvRecord.get("Subscription Id"));
                customerDetail.put("Invoice Id",csvRecord.get("Invoice Number"));
                SimpleDateFormat givenDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat desiredDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                customerDetail.put("Invoice Date", desiredDateFormat.format(givenDateFormat.parse(csvRecord.get("Invoice Date"))));
                customerDetail.put("Start Date",desiredDateFormat.format(givenDateFormat.parse(csvRecord.get("Start Date"))));
                customerDetail.put("Amount",Float.parseFloat(csvRecord.get("Amount"))/100);
                customerDetail.put("Status", csvRecord.get("Status"));
                customerDetail.put("Paid On", csvRecord.get("Paid On"));
                customerDetail.put("Next Retry", csvRecord.get("Next Retry"));
                customerDetail.put("Refunded Amount",Float.parseFloat(csvRecord.get("Refunded Amount"))/10);
                customerDetail.put("Recurring", csvRecord.get("Recurring"));
                customerDetail.put("First Invoice", csvRecord.get("First Invoice"));
                customerDetail.put("Tax Total", Float.parseFloat(csvRecord.get("Tax Total"))/100);
                JSONObject customerPersonalDetails = new JSONObject();
                customerPersonalDetails.put("First Name", csvRecord.get("Customer First Name"));
                customerPersonalDetails.put("Last Name",csvRecord.get("Customer Last Name"));
                customerPersonalDetails.put("Email",csvRecord.get("Customer Email"));
                customerPersonalDetails.put("Company",csvRecord.get("Customer Company"));
                customerDetail.put("Customer Detail", customerPersonalDetails.toString());
                customerDetails.put(customerDetail);
            }
            root.put("Customer Details", customerDetails);
            BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("/Users/cb-mohamedsullaiman/sample/config.json"));
            root.write(bufferedWriter);
            bufferedWriter.flush(); 
        }
        catch(IOException ioException){
            System.out.println("IO Exception");
        }
        catch(ParseException parseException){
            parseException.printStackTrace();
            System.out.println("Parse exception found");
        }
    }
    public static void createCSVFromJSON(Path jsonPath, Path destinationPath){
        try{
            JSONObject root = new JSONObject(new JSONTokener(Files.newBufferedReader(jsonPath)));
            JSONArray jsonCustomerDetails = root.getJSONArray("Customer Details");
            String nameArray[] = {"Customer Id","Subscription Id","Invoice Id","Invoice Date","Start Date","Amount","Status","Paid On","Next Retry","Refunded Amount","Recurring","First Invoice","Tax Total","Customer Detail"};
            JSONArray names = new JSONArray(nameArray);
            String csvFile="";
            for(int i=0;i<nameArray.length;i++){
                csvFile+=nameArray[i];
                if(i<nameArray.length-1){
                    csvFile+=",";
                }
            }
            csvFile+="\n";
            // String header = "Customer Id,Subscription Id,Invoice Id,Invoice Date,Start Date,Amount,\"Status\",\"Paid On\",\"Next Retry\",\"Refunded Amount\",\"Recurring\",\"First Invoice\",\"Tax Total\",\"Customer Details\""
            csvFile = csvFile + CDL.toString(names,jsonCustomerDetails);
            FileUtils.writeStringToFile(destinationPath.toFile(), csvFile, (String)null);
            throw new IOException();
        }
        catch(IOException ioException){
            
        }
    }
    public static void main(String args[]){
        Path sourcePath = Paths.get("/Users/cb-mohamedsullaiman/Downloads/Input.csv");
        createJSONFromCSV(sourcePath);
        //System.out.println(root.toString());
        Path destinationPath = Paths.get("/Users/cb-mohamedsullaiman/sample/output.csv");
        Path jsonPath =  Paths.get("/Users/cb-mohamedsullaiman/sample/config.json");
        createCSVFromJSON(jsonPath,destinationPath);
    }
}
