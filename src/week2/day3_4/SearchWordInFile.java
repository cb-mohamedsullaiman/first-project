package filesjava;

import java.util.Scanner;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class SearchWordInFile extends filesjava.FileManipulation{
	private String wordToBeSearched;
	public void addToMap(Map<String,String> map,String line,String lineNumber){
		if(line.contains(wordToBeSearched)){
			Integer index = line.indexOf(wordToBeSearched);
			StringBuilder listOfIndices = new StringBuilder(index.toString());
			while(index>=0){
				index = line.indexOf(wordToBeSearched,index+wordToBeSearched.length());
				if(index!=-1){
					listOfIndices.append(","+index);
				}
			}
			map.put(lineNumber,listOfIndices.toString());
		}
	}
	public void readFromFile(Map<String,String> map,File file){
		try(LineIterator lineIterator = FileUtils.lineIterator(file);){
			Integer lineNumber = 1;
			while(lineIterator.hasNext()){
				String line = lineIterator.next();
				addToMap(map,line,lineNumber.toString());
				lineNumber+=1;
			}
		}
		catch(IOException ioException){
			System.out.println("IO exception is thrown");
		}
	}
	public static void main(String args[]){
		SearchWordInFile searchWordInFile = new SearchWordInFile();
		System.out.println("Enter the word you want to search:");
		Scanner scanner = new Scanner(System.in);
		searchWordInFile.wordToBeSearched = scanner.next();
		File sourceFile = FileUtils.getFile("/Users/cb-mohamedsullaiman/sample/iostreams.txt");
		Map<String,String> map = new TreeMap<String,String>();
		searchWordInFile.readFromFile(map,sourceFile);
		File targetFile = FileUtils.getFile("/Users/cb-mohamedsullaiman/sample/search_output.txt");
		searchWordInFile.writeToFile(map,targetFile);
	}
}