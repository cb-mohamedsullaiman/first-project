/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4;

import week2.day1_2.phonedirectory.PhoneNumberDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import week2.day1_2.phonedirectory.Person;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class PhoneDirectoryUsingJSON {

    private Set<Long> phoneNumbers = new HashSet<>();
    private Map<String, ArrayList<Person>> personMap = new TreeMap<>();
    private Map<Long, Person> phoneNumberMap = new HashMap<>();

        
    public void readPersonDetailsFromJSON(Path sourcePath) throws IOException {
        
            BufferedReader bufferedReader = Files.newBufferedReader(sourcePath);
            JSONObject root = new JSONObject(new JSONTokener(bufferedReader));

            //Getting all the persons
            JSONArray jsonPersons = root.getJSONArray("Persons");
            for (int i = 0; i < jsonPersons.length(); i++) {

                //Getting a single person
                JSONObject jsonPerson = jsonPersons.getJSONObject(i);
                Person person = new Person();

                String name = jsonPerson.getString("Name");
                if(name.length()==0){
                    System.out.println("Name must not be empty.. check the json file ..");
                    throw new IOException();
                }
                person.setName(name);

                String address = jsonPerson.getString("Address");
                if(address.length()==0){
                    System.out.println("Address cannot be empty.. check the json file ..");
                    throw new IOException();
                }
                person.setAddress(address);

                JSONArray jsonPhoneDetails = jsonPerson.getJSONArray("Phone Number Details");
                
                if(jsonPhoneDetails.length()==0){
                    System.out.println("A person should have atleast one phone number");
                    throw new IOException();
                }
                
                for (int j = 0; j < jsonPhoneDetails.length(); j++) {
                    JSONObject jsonPhoneDetail = jsonPhoneDetails.getJSONObject(j);
                    Long phoneNumber = jsonPhoneDetail.getLong("Phone Number");
                    
                    //Phone numbers must be unique
                    if(!phoneNumbers.add(phoneNumber)){
                        System.out.println("Phone numbers must be unique");
                        throw new IOException();
                    }
                    //Mapping the phone number against the person to easily retrieve person based on phonenumber
                    phoneNumberMap.put(phoneNumber,person);
                    //Setting the phone number details
                    PhoneNumberDetails phoneNumberDetail = new PhoneNumberDetails();
                    phoneNumberDetail.setPhoneNumber(phoneNumber);
                    phoneNumberDetail.setType(PhoneNumberDetails.TypeOfUsage.valueOf(jsonPhoneDetail.getString("Type").toUpperCase()));
                    person.addPhoneDetails(phoneNumberDetail);
                }

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
        System.out.println("\nName\t:" + person.getName());
        System.out.println("Address\t:" + person.getAddress());
        Iterator iterator = person.getPhoneNumbers().iterator();
        while (iterator.hasNext()) {
            PhoneNumberDetails numberDetail = (PhoneNumberDetails) iterator.next();
            System.out.println(numberDetail.getPhoneNumber() + "\t-" + numberDetail.getTypeOfUsage());
        }
        System.out.println("\n");

    }
    
    public static void main(String args[]) throws IOException {
        PhoneDirectoryUsingJSON phoneDirectory = new PhoneDirectoryUsingJSON();
        Scanner scanner = new Scanner(System.in);
        Integer choice;
        Path sourcePath = Paths.get(System.getProperty("user.home")+"/sample/phone_directory.json");
        phoneDirectory.readPersonDetailsFromJSON(sourcePath);
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

            }
        } while (choice != 4);
    }

}
