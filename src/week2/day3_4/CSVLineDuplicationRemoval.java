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
import java.util.Iterator;

public class CSVLineDuplicationRemoval{
	public static void readCSV(Set<String> set,String sourcePathString){
		try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(sourcePathString)); CSVParser csvParser = new CSVParser(bufferedReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());){
			for(CSVRecord csvRecord : csvParser){
				String name = csvRecord.get("Name");
				String mailId = csvRecord.get("Mail id");
				String address = csvRecord.get("Address");
				String phoneNumber = csvRecord.get("Phone number");
				set.add(name+","+mailId+","+address+","+phoneNumber);
			}
		}
		catch(IOException ioException){
			System.out.println("IO Exception while reading file");
		}
	}
	public static void writeCSV(Set<String> set,String destinationPathString){
		try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(destinationPathString)); CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter,CSVFormat.DEFAULT.withHeader("Name","Mail id","Address","Phone Number"))){
			Iterator<String> iterator = set.iterator();
			while(iterator.hasNext()){
				String line = iterator.next();
				CSVParser csvParser = CSVParser.parse(line,CSVFormat.DEFAULT);
				for(CSVRecord csvRecord : csvParser){
					csvPrinter.printRecord(csvRecord);
				}
			} 
		}
		catch(IOException ioException){
			System.out.println("IO exception while writing file");
		}
	}
	public static void main(String args[]){
		Set<String> set = new LinkedHashSet<>();
		String sourceFilePath = "/Users/cb-mohamedsullaiman/sample/personal_details.csv";
		readCSV(set,sourceFilePath);
		String destinationFilePath = "/Users/cb-mohamedsullaiman/sample/personal_details_output.csv";
		writeCSV(set,destinationFilePath);
	}
}