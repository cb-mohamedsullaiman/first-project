package week2.day3_4;

import java.util.Set;
import java.util.LinkedHashSet;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVLineDuplicationRemover {

    Set<String> lineSet = new LinkedHashSet<>();        //This will contain the csvrecords
    String header;                                      //This will contain the header

    public void writeUniqueLinesToDestination(String sourcePathString, String destinationPathString) throws IOException {

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(sourcePathString)); BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(destinationPathString))) {
            header = bufferedReader.readLine();                  //Getting the header line
            CSVParser csvParser = new CSVParser(bufferedReader, CSVFormat.DEFAULT.withTrim());
            CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withHeader(header.split(",")));
            Integer headerLength = header.split(",").length;        //Calculating the length of the header

            for (CSVRecord csvRecord : csvParser) {
                if (csvRecord.size() != headerLength) {         //CSVrecord lengths should match with the header length
                    System.out.println("****This csv file contains record which does not match with the header length*****\nRecord number = " + csvRecord.getRecordNumber());
                    return;
                }
                StringBuilder line = new StringBuilder();       //This string builder is used for identification of line duplication
                for (int i = 0; i < headerLength; i++) {
                    line.append(csvRecord.get(i));          //This will be the line of csv records without commas
                }
                if (lineSet.add(line.toString())) {           //If the line is added successfully(unique) then the line will be written to csv file
                    csvPrinter.printRecord(csvRecord);
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        
        CSVLineDuplicationRemover lineDuplicationRemover = new CSVLineDuplicationRemover();
        
        String sourceFilePath = System.getProperty("user.home") + "/sample/personal_details.csv";
        
        String destinationFilePath = System.getProperty("user.home") + "/Users/cb-mohamedsullaiman/sample/personal_details_output.csv";
        
        lineDuplicationRemover.writeUniqueLinesToDestination(sourceFilePath, destinationFilePath);

    }
}
