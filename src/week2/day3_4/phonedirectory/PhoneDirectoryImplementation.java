/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4.phonedirectory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class PhoneDirectoryImplementation {
    public static void main(String args[]) throws IOException {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path of the file which consists the data of persons:");
        System.out.println("Eg: /sample/phone_directory.csv");
        String filePath = scanner.nextLine();
        
        Path sourcePath = Paths.get(System.getProperty("user.home")+filePath);
        if(!Files.exists(sourcePath)){
            System.out.println("\n*****File does not exists******");
            return;
        }
        
        Integer choice;       
        
        PhoneDirectory phoneDirectory = new PhoneDirectoryUsingCSV();
        if (FilenameUtils.getExtension(filePath).contains("json")) {
            phoneDirectory = new PhoneDirectoryUsingJSON();
        }
        phoneDirectory.read(sourcePath);
       
        do {
            System.out.println("1.Retrieve the person details based on name\n2.Retrieve the person details based on partial matching\n3.Retrieve the person details based on phone number\n4.Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the person name");
                    String nameOfThePersonToBeRetrieved = scanner.nextLine();
                    if(nameOfThePersonToBeRetrieved.length()==0){
                        System.out.println("Person Name cannot be empty.. Try again");
                        continue;
                    }
                    if (!phoneDirectory.retrievePersonByName(nameOfThePersonToBeRetrieved)) {
                        System.out.println("\n******No persons found with this name******");
                    }
                    break;
                case 2:
                    System.out.println("Enter the partial name of the person");
                    String partialName = scanner.nextLine();
                    if(partialName.length()==0){
                        System.out.println("We cannot search without name.... Try again");
                        continue;
                    }
                    if (!phoneDirectory.retrievePersonByPartialName(partialName)) {
                        System.out.println("\n*****No persons found related to this name*****");
                    }
                    break;
                case 3:
                    System.out.println("Enter the phone no of the person");
                    Long personPhoneNumber = scanner.nextLong();
                    scanner.nextLine();
                    if (!phoneDirectory.retrievePersonByPhoneNumber(personPhoneNumber)) {
                        System.out.println("\n******No persons found with that phone number*****");
                    }
                    break;
                case 4:
                    break;
                default:
                    System.out.println("\n******Invalid choice*****");
            }
        } while (choice != 4);
    }

    
}
