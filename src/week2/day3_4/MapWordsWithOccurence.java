package filesjava;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.*;

public class MapWordsWithOccurence extends filesjava.FileManipulation {
	public void readFromFile(Map<String,String> map,File file){
		Path sourcePath = file.toPath();
		try(Scanner scanner = new Scanner(Files.newBufferedReader(sourcePath));) {
			while(scanner.hasNext()){
				String word=scanner.next();
				addToMap(map,"["+word+"]");
			}
			printMap(map);
		}
		catch(IOException ioException){
			System.out.println("IO exception while reading");
		}
	}
	//@Override
	public static void addToMap(Map<String,String> map,String word){
		Integer numberOfOccurances=0;
		if(map.containsKey(word)){
			numberOfOccurances=Integer.parseInt(map.get(word).substring(1,1));
		}
		map.put(word,"["+(numberOfOccurances+1)+"]");
	}
	public static void printMap(Map<String,String> map){
		Iterator<Map.Entry<String,String>> iterator=map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry mapEntry =(Map.Entry) iterator.next();
			System.out.println(mapEntry.getKey()+"\t\t\t\t"+mapEntry.getValue());
		}
	}
	public static void main(String args[]){
		FileManipulation fileManipulation = new MapWordsWithOccurence();
		Map<String,String> map=new TreeMap<String,String>();
		File sourceFile = FileUtils.getFile("/Users/cb-mohamedsullaiman/sample/iostreams.txt");
		fileManipulation.readFromFile(map,sourceFile);
		File destinationFile = FileUtils.getFile("/Users/cb-mohamedsullaiman/sample/iostreams_output.txt");
		fileManipulation.writeToFile(map,destinationFile);
	}
}