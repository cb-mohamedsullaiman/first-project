package week2.day3_4;

import java.util.Scanner;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class FileWordSearcher {

    private String wordToBeSearched;
    private Map<Integer, String> map = new TreeMap<>();
    
    public void findOccurencesOfWord(File file) throws IOException {
        if (wordToBeSearched.length() == 0) {           //If the word to be searched is empty then there is nothing to find
            System.out.println("Search word cannot be empty");
            return;
        }
        if(!file.exists()){
            System.out.println("File does not exists");
            return;
        }
        LineIterator lineIterator = FileUtils.lineIterator(file);
        Integer lineNumber = 1;
        while (lineIterator.hasNext()) {
            String line = lineIterator.next();
            if (line.contains(wordToBeSearched)) {                       //If the line contains the word
                Integer index = line.indexOf(wordToBeSearched);          //Getting the index of the word
                StringBuilder listOfIndices = new StringBuilder();
                while (index >= 0) {                                     //This loop will execute until there is no occurances of the word in the line
                    listOfIndices.append(",").append(index);             //Adding the index to the list
                    index = line.indexOf(wordToBeSearched, index + wordToBeSearched.length());  //Next occurance of the word in the same line

                }
                map.put(lineNumber, listOfIndices.toString());           //line number with the list of occurances
            }
            lineNumber += 1;
        }

    }

    public void writeOccurencesToFile(File file) throws IOException{
        
            if (map.isEmpty()) {        //If there is no occurances of the word in the file 
                System.out.println("No words are matched");
                return;
            }
            Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry mapEntry = iterator.next();
                String lineNumberWithIndices = mapEntry.getKey() + "\t\t" + mapEntry.getValue() + "\n";
                System.out.println(lineNumberWithIndices);
                FileUtils.writeStringToFile(file, lineNumberWithIndices, (String) null, true);
            }
           
    }

    public static void main(String args[])throws IOException {
        
        FileWordSearcher wordSearcher = new FileWordSearcher();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the word you want to search:");
        wordSearcher.wordToBeSearched = scanner.next();
        
        File sourceFile = FileUtils.getFile("/Users/cb-mohamedsullaiman/sample/iostreams.txt");
        wordSearcher.findOccurencesOfWord(sourceFile);
        
        File targetFile = FileUtils.getFile("/Users/cb-mohamedsullaiman/sample/search_output.txt");
        wordSearcher.writeOccurencesToFile(targetFile);
        
    }
}
