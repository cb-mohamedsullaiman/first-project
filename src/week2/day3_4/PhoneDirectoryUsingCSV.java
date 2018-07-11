/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4;

import week2.day1_2.phonedirectory.Person;
import week2.day1_2.phonedirectory.PhoneNumberDetails;

import java.util.ArrayList;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class PhoneDirectoryUsingCSV {

    private Set<Long> phoneNumbers = new HashSet<>();
    private Map<String, ArrayList<Person>> personMap = new TreeMap<>();
    private Map<Long, Person> phoneNumberMap = new HashMap<>();

    public void readPersonDetailsFromCSV(Path sourcePath) throws IOException {

        BufferedReader bufferedReader = Files.newBufferedReader(sourcePath);
        CSVParser csvParser = CSVParser.parse(bufferedReader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord().withIgnoreHeaderCase().withTrim());

        for (CSVRecord csvRecord : csvParser) {

            String name = csvRecord.get("Name"); 
            
            if (name.length() == 0) {                       //Name should not be empty
                System.out.println("Name field cannot be empty ... check record number " + csvRecord.getRecordNumber());
                throw new IOException();
            }
            
            String address = csvRecord.get("Address");
            
            if (address.length() == 0) {                //Address cannot be empty
                System.out.println("Address cannot be empty .. check record number  " + csvRecord.getRecordNumber());
                throw new IOException();
            }
            
            Long phoneNumber1 = Long.parseLong(csvRecord.get("Phone Number1"));
            String type1 = csvRecord.get("Type1");
            Long phoneNumber2 = Long.parseLong(csvRecord.get("Phone Number2"));
            String type2 = csvRecord.get("Type2");
            Long phoneNumber3 = Long.parseLong(csvRecord.get("Phone Number3"));
            String type3 = csvRecord.get("Type3");
            
            //Atleast we should have one phone number
            if (phoneNumber1.toString().length() == 0 && phoneNumber2.toString().length() == 0 && phoneNumber3.toString().length() == 0) {
                System.out.println("You should have atleast one phone number in " + csvRecord.getRecordNumber());
                throw new IOException();
            }
            
            //Phone numbers must be unique
            if (!phoneNumbers.add(phoneNumber1) && !phoneNumbers.add(phoneNumber2) && !phoneNumbers.add(phoneNumber3)) {
                System.out.println("Phone Numbers should be unique : " + csvRecord.getRecordNumber());
                throw new IOException();
            }

            Person person = new Person();
            person.setName(name);
            person.setAddress(address);
            
            //phone numbers should be mapped into the phonenumbersmap if they are not 0
            if (phoneNumber1 != 0) {
                phoneNumberMap.put(phoneNumber1, person);
            }
            if (phoneNumber2 != 0) {
                phoneNumberMap.put(phoneNumber2, person);
            }
            if (phoneNumber3 != 0) {
                phoneNumberMap.put(phoneNumber3, person);
            }
            
            PhoneNumberDetails numberDetail1 = new PhoneNumberDetails();
            numberDetail1.setPhoneNumber(phoneNumber1);
            numberDetail1.setType(PhoneNumberDetails.TypeOfUsage.valueOf(type1.toUpperCase()));
            person.addPhoneDetails(numberDetail1);
            
            PhoneNumberDetails numberDetail2 = new PhoneNumberDetails();
            numberDetail2.setPhoneNumber(phoneNumber2);
            numberDetail2.setType(PhoneNumberDetails.TypeOfUsage.valueOf(type2.toUpperCase()));
            person.addPhoneDetails(numberDetail2);
            
            PhoneNumberDetails numberDetail3 = new PhoneNumberDetails();
            numberDetail3.setPhoneNumber(phoneNumber3);
            numberDetail3.setType(PhoneNumberDetails.TypeOfUsage.valueOf(type3.toUpperCase()));
            person.addPhoneDetails(numberDetail3);
            
            ArrayList<Person> persons = new ArrayList<>();
            if (personMap.containsKey(name)) {
                persons = personMap.get(name);
            }
            persons.add(person);
            personMap.put(name, persons);
        }

    }

    public Boolean retrievePersonByName(String personName) {
        if (personMap.isEmpty()) {
            return false;
        }
        Boolean isPersonRetrieved = false;
        Pattern pattern = Pattern.compile(personName, Pattern.CASE_INSENSITIVE);
        for (Map.Entry<String, ArrayList<Person>> mapEntry : personMap.entrySet()) {
            if (pattern.matcher(mapEntry.getKey()).matches()) {
                printPersonDetails(mapEntry.getValue());
                isPersonRetrieved = true;
            }
        }
        return isPersonRetrieved;
    }

    public Boolean retrievePersonByPartialName(String partialName) {
        if (personMap.isEmpty()) {
            return false;
        }
        Boolean isPersonRetrieved = false;
        Pattern pattern = Pattern.compile(partialName, Pattern.CASE_INSENSITIVE);
        for (Map.Entry<String, ArrayList<Person>> mapEntry : personMap.entrySet()) {
            Matcher matcher = pattern.matcher(mapEntry.getKey());
            if (matcher.find()) {
                printPersonDetails(mapEntry.getValue());
                isPersonRetrieved = true;
            }
        }
        return isPersonRetrieved;
    }

    public boolean retrievePersonByPhoneNumber(Long personPhoneNumber) {
        if (personMap.isEmpty()) {
            return false;
        }
        if (!(phoneNumberMap.containsKey(personPhoneNumber))) {
            return false;
        }
        printPersonDetails(phoneNumberMap.get(personPhoneNumber));
        return true;
    }

    public void printPersonDetails(ArrayList<Person> personList) {
        for (int i = 0; i < personList.size(); i++) {
            Person person = personList.get(i);
            System.out.println("\nName\t:" + person.getName());
            System.out.println("Address\t:" + person.getAddress());
            Iterator iterator = person.getPhoneNumbers().iterator();
            while (iterator.hasNext()) {
                PhoneNumberDetails numberDetail = (PhoneNumberDetails) iterator.next();
                System.out.println(numberDetail.getPhoneNumber() + "\t-" + numberDetail.getTypeOfUsage());
            }

        }
    }

    public void printPersonDetails(Person person) {
        System.out.println("Name\t:" + person.getName());
        System.out.println("Address\t:" + person.getAddress());
        Iterator iterator = person.getPhoneNumbers().iterator();
        while (iterator.hasNext()) {
            PhoneNumberDetails numberDetail = (PhoneNumberDetails) iterator.next();
            System.out.println(numberDetail.getPhoneNumber() + "\t-" + numberDetail.getTypeOfUsage());
        }
        System.out.println("\n");

    }

    public static void main(String args[]) throws IOException {
        PhoneDirectoryUsingCSV phoneDirectory = new PhoneDirectoryUsingCSV();
        Scanner scanner = new Scanner(System.in);
        Integer choice;
        Path sourcePath = Paths.get(System.getProperty("user.home") + "/sample/phone_directory.csv");
        phoneDirectory.readPersonDetailsFromCSV(sourcePath);
        do {
            System.out.println("\n1.Retrieve the person details based on name\n2.Retrieve the person details based on partial matching\n3.Retrieve the person details based on phone number\n4.Exit");
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

            }
        } while (choice != 4);
    }
}
