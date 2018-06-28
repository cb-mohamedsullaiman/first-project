/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4;

import week2.day1_2.phonedirectory.Name;
import week2.day1_2.phonedirectory.PhoneNumberDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import week2.day1_2.phonedirectory.Person;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class PhoneDirectoryWithJSON {

    public static void readFromJSON(Map<String, ArrayList<Person>> map, Path sourcePath) {
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(sourcePath);

            JSONObject root = new JSONObject(new JSONTokener(bufferedReader));

            JSONArray jsonPersons = root.getJSONArray("Persons");

            for (int i = 0; i < jsonPersons.length(); i++) {

                JSONObject jsonPerson = jsonPersons.getJSONObject(i);
                Person person = new Person();

                String name = jsonPerson.getString("Name");
                Name nameObject = new Name();
                nameObject.setName(name);

                person.setName(nameObject);

                String address = jsonPerson.getString("Address");
                person.setAddress(address);

                JSONArray jsonPhoneDetails = jsonPerson.getJSONArray("Phone Number Details");
                for (int j = 0; j < jsonPhoneDetails.length(); j++) {
                    JSONObject jsonPhoneDetail = jsonPhoneDetails.getJSONObject(j);
                    PhoneNumberDetails phoneNumberDetail = new PhoneNumberDetails();

                    phoneNumberDetail.setPhoneNumber(jsonPhoneDetail.getLong("Phone Number"));
                    phoneNumberDetail.setType(PhoneNumberDetails.Type.valueOf(jsonPhoneDetail.getString("Type").toUpperCase()));
                    person.addPhoneDetails(phoneNumberDetail);
                }

                ArrayList<Person> persons = new ArrayList<>();
                if (map.containsKey(name)) {
                    persons = map.get(name);
                }
                persons.add(person);
                map.put(name, persons);
            }
        } catch (IOException ioException) {
            System.out.println("IO exception");
        }

    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Integer choice;
        Map<String, ArrayList<Person>> map = new TreeMap<>();
        Path sourcePath = Paths.get("/Users/cb-mohamedsullaiman/sample/phone_directory.json");
        readFromJSON(map, sourcePath);
        do {
            System.out.println("1.Retrieve the person details based on name\n2.Retrieve the person details based on partial matching\n3.Retrieve the person details based on phone number\n4.Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the person name");
                    String personName = scanner.nextLine();
                    if (map.containsKey(personName)) {
                        for (int i = 0; i < map.get(personName).size(); i++) {
                            map.get(personName).get(i).printPerson();
                        }
                    } else {
                        System.out.println("\n******No persons found with that name******");
                    }
                    break;
                case 2:
                    System.out.println("Enter the partial name of the person");
                    String partialName = scanner.nextLine();
                    Integer flag = 0;
                    for (Map.Entry<String, ArrayList<Person>> mapEntry : map.entrySet()) {
                        String tempName[] = mapEntry.getKey().split(" ", 3);
                        if (Arrays.asList(tempName).contains(partialName)) {
                            for (int i = 0; i < mapEntry.getValue().size(); i++) {
                                mapEntry.getValue().get(i).printPerson();
                            }
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        System.out.println("\n*****No persons are related to this name*****");
                    }
                    break;
                case 3:
                    flag = 0;
                    System.out.println("Enter the phone no of the person");
                    Long personPhoneNumber = scanner.nextLong();
                    scanner.nextLine();
                    for (Map.Entry<String, ArrayList<Person>> mapEntry : map.entrySet()) {
                        for (int i = 0; i < mapEntry.getValue().size(); i++) {
                            Iterator iterator = mapEntry.getValue().get(i).getPhoneNumbers().iterator();
                            while (iterator.hasNext()) {
                                PhoneNumberDetails numberDetail = (PhoneNumberDetails) iterator.next();
                                if (numberDetail.getPhoneNumber().equals(personPhoneNumber)) {
                                    mapEntry.getValue().get(i).printPerson();
                                    flag = 1;
                                }
                            }
                        }
                    }
                    if (flag == 0) {
                        System.out.println("\n******No persons are found with this phone number*****");
                    }
                    break;
                case 4:
                    break;

            }
        } while (choice != 4);
    }

}
