package week2.day3_4;

import java.nio.file.Path;
import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.DirectoryStream;

public class FileMover {

    public static void moveFilesToDestination(Path sourcePath, Path destinationPath) throws IOException {
        
        if(!Files.exists(sourcePath)){
            System.out.println("Source path does not exists");
            return;
        }

        DirectoryStream<Path> paths = Files.newDirectoryStream(sourcePath);     //Creating a directory stream
        for (Path path : paths) {
            if (Files.isDirectory(path)) {                      //If it is a directory the function will be called reecursively
                moveFilesToDestination(path, destinationPath);
            } 
            else if (Files.isRegularFile(path)) {       //There may be some special files in a directory which our computer uses

                String fileName = path.getFileName().toString();        //getting the file name
                System.out.println(fileName);
                File parentDirectory = new File(destinationPath.toString());        //getting the destination path
                System.out.println(parentDirectory.getName());
                File newFile = new File(parentDirectory, fileName);         //Resolving filename with the destination directory
                System.out.println(newFile.getName());
                if (!newFile.exists()) {                            //If the new file name does not exists
                    System.out.println(destinationPath.toString());
                    Files.move(path, destinationPath.resolve(fileName));
                } 
                else {          // If the new file name exists we have to append the incremental number
                    Boolean isMoved = false;
                    Integer numberOfFiles = 1;
                    do {
                        Integer extensionIndex = fileName.lastIndexOf(".");
                        if (extensionIndex == -1) {
                            extensionIndex = fileName.length();
                        }
                        String fileNameWithoutExtension = fileName.substring(0, extensionIndex);
                        String fileExtension = fileName.substring(extensionIndex);
                        Integer fileCount = fileName.lastIndexOf("-");      //Getting the incremental number if exists
                        if (fileCount == -1) {                          //If the incremental number does not exists
                            fileName = fileNameWithoutExtension + '-' + numberOfFiles + fileExtension;
                        } 
                        else {
                            numberOfFiles = Integer.parseInt(fileNameWithoutExtension.substring(fileCount + 1)) + 1;
                            fileName = fileNameWithoutExtension.substring(0, fileCount) + '-' + numberOfFiles + fileExtension;
                        }
                        File newFile1 = new File(parentDirectory, fileName);
                        if (!newFile1.exists()) {
                            Files.move(path, destinationPath.resolve(fileName));
                            isMoved = true;
                        }
                    } while (!isMoved);
                }

            }
        }
    }
    
    
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the directory you want to move");
        String sourceDirectory = scanner.nextLine();
        
        if (sourceDirectory.length() == 0) {
            System.out.println("Source Directory cannot be empty");
            return;
        }
        
        
        System.out.println("Enter the destination directory");
        String destinationDirectory = scanner.nextLine();
        
        if (destinationDirectory.length() == 0) {
            System.out.println("Destination directory cannot be empty");
            return;
        }
        moveFilesToDestination(Paths.get("/Users/cb-mohamedsullaiman/" + sourceDirectory).toAbsolutePath(), Paths.get("/Users/cb-mohamedsullaiman/" + destinationDirectory).toAbsolutePath());

    }
}
