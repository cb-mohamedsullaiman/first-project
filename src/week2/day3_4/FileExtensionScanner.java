package week2.day3_4;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class FileExtensionScanner {

    private Map<String, Integer> map = new HashMap<>();

    public void mapExtensionAgainstOccurence(String extension) {
        if (map.containsKey(extension)) {               //If the extension is already present then it will get the number of occurence
            Integer numberOfFiles = map.get(extension);
            map.put(extension, numberOfFiles + 1);
        } 
        else {              //If the extension is not present, then it will mapped with its occurence as 1
            map.put(extension, 1);
        }
    }

    public void scanAllExtensions(Path path) throws IOException {       //It will scan the directory for file extensions and map the extension against its number of occurence
        Stream<Path> paths = Files.list(path);              //Getting all the files and directories
        Iterator iterator = paths.iterator();             
        while (iterator.hasNext()) {
            path = (Path) iterator.next();                     //Getting each path
            if (Files.isDirectory(path)) {                      //IF the path is a directory then we have to call the method recursively
                scanAllExtensions(path);
            } else if (Files.isRegularFile(path) && path.toString().contains(".")) {    //If it is a regular file, then it will get the extension and map it
                int extensionIndex = path.toString().lastIndexOf('.');                  //Getting the last index of '.'
                String extension = path.toString().substring(extensionIndex + 1);       //Getting the extension
                
                mapExtensionAgainstOccurence(extension);                                //Map the extension
            }
        }
    }

    public void printAllExtensions(){
        map.forEach((k, v) -> System.out.println(k + "\t\t" + v));
    }
    
    public static void main(String... args) throws IOException {

        FileExtensionScanner extensionScanner = new FileExtensionScanner();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the directory you want to explore");
        String directory = scanner.nextLine();
        if(directory.length()==0)
        {
            System.out.println("Directory cannot be empty");
            return;
        }
        Path path = Paths.get(System.getProperty("user.home")).resolve(directory);
        if(!Files.exists(path)){
            System.out.println("Path does not exists");
            return;
        }
        extensionScanner.scanAllExtensions(path);
        extensionScanner.printAllExtensions();
    }
}
