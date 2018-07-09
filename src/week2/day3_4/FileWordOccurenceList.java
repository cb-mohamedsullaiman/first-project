package week2.day3_4;

import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.apache.commons.io.*;

public class FileWordOccurenceList {

    private Map<String, String> map = new TreeMap<>();

    public void listAllWordOccurences(File file) throws IOException {
        if (!file.exists()) //If the file does not exists
        {
            System.out.println("File does not exists");
            return;
        }
        Path sourcePath = file.toPath();
        Scanner scanner = new Scanner(Files.newBufferedReader(sourcePath));   //Splitting the line into tokens  
        while (scanner.hasNext()) {               //For every word
            String word = scanner.next();
            mapWordAgainstFrequency("[" + word + "]");      //Map the word against its occurence
        }

    }

    public void mapWordAgainstFrequency(String word) {
        Integer numberOfOccurances = 0;
        if (map.containsKey(word)) {              //If the word already exists, then it will get the number of occurences
            numberOfOccurances = Integer.parseInt(map.get(word).substring(1, 2));
        }
        map.put(word, "[" + (numberOfOccurances + 1) + "]");       //Put the word with the updated number of occurences
    }

    public void writeAllOccurencesToFile(File file) throws IOException {

        if (map.isEmpty()) {        //If there is no occurances of the word in the file 
            System.out.println("There is no data content in the specified file");
            return;
        }
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapEntry = iterator.next();
            String lineNumberWithIndices = mapEntry.getKey() + " : " + mapEntry.getValue() + "\n";
            System.out.println(lineNumberWithIndices);
            FileUtils.writeStringToFile(file, lineNumberWithIndices, (String) null, true);
        }

    }

    public static void main(String args[]) throws IOException {

        FileWordOccurenceList wordOccurenceFinder = new FileWordOccurenceList();
        File sourceFile = FileUtils.getFile(System.getProperty("user.home") + "/sample/iostreams.txt");
        wordOccurenceFinder.listAllWordOccurences(sourceFile);
        File destinationFile = FileUtils.getFile(System.getProperty("user.home") + "/sample/iostreams_output.txt");
        wordOccurenceFinder.writeAllOccurencesToFile(destinationFile);
    }
}
