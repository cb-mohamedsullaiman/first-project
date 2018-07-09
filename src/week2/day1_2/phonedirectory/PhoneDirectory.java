package week2.day1_2.phonedirectory;

import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneDirectory {

    private Set<Long> phoneNumbers = new HashSet<>();
    private Map<String, ArrayList<Person>> personMap = new TreeMap<>();
    private Map<Long, Person> phoneNumberMap = new HashMap<>();

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

    public Boolean checkLength(String field) {
        if (field.length() == 0) {
            System.out.println("\n******Field cannot be empty... You must enter something******");
            return false;
        }
        return true;
    }

    public Boolean checkLength(Long field, int length) {
        if (field.toString().length() < length) {
            System.out.println("Field cannot be less than " + length + " digits...");
            return false;
        }
        return true;
    }

    public Boolean checkExistenceOfPhoneNumber(Long phoneNumber) {
        if (phoneNumbers.add(phoneNumber)) {
            return false;
        }
        System.out.println("Phone Number already exists");
        return true;
    }

    public void mapPersonAgainstName(Person person) {
        ArrayList<Person> persons = new ArrayList<>();
        String name = person.getName();
        if (personMap.containsKey(name)) {
            persons = personMap.get(name);
        }
        persons.add(person);
        personMap.put(name, persons);
    }

    public void mapPersonAgainstPhoneNumber(Person person, Long phoneNumber) {
        phoneNumberMap.put(phoneNumber, person);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Integer choice;
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        do {
            System.out.println("1.Add a person detail into phone directory\n2.Retrieve the person details based on name\n3.Retrieve the person details based on partial matching\n4.Retrieve the person details based on phone number\n5.Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Person person = new Person();
                    String name;
                    do {
                        System.out.println("Enter the person name:");
                        name = scanner.nextLine();
                    } while (!(phoneDirectory.checkLength(name)));
                    person.setName(name);
                    String address;
                    do {
                        System.out.println("Enter the address:");
                        address = scanner.nextLine();
                    } while (!(phoneDirectory.checkLength(address)));
                    person.setAddress(address);
                    PhoneNumberDetails numberDetail;
                    do {
                        numberDetail = new PhoneNumberDetails();
                        System.out.println("Enter the phone number:");
                        Long phoneNumber = scanner.nextLong();
                        if (!((phoneDirectory.checkLength(phoneNumber, 10)) && !(phoneDirectory.checkExistenceOfPhoneNumber(phoneNumber)))) {
                            System.out.println("Type yes to continue");
                            continue;
                        }
                        numberDetail.setPhoneNumber(phoneNumber);
                        phoneDirectory.phoneNumberMap.put(phoneNumber, person);
                        System.out.println("Is it your\n\t1.mobile number\n\t2.home number\n\t3.work number");
                        Integer phoneChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (phoneChoice) {
                            case 1:
                                numberDetail.setType(PhoneNumberDetails.TypeOfUsage.MOBILE);
                                break;
                            case 2:
                                numberDetail.setType(PhoneNumberDetails.TypeOfUsage.HOME);
                                break;
                            case 3:
                                numberDetail.setType(PhoneNumberDetails.TypeOfUsage.WORK);
                                break;
                            default:
                                System.out.println("\n*********Invalid choice***********");
                                System.out.println("Type yes to continue");
                                phoneDirectory.phoneNumbers.remove(phoneNumber);
                                phoneDirectory.phoneNumberMap.remove(phoneNumber);
                                continue;
                        }
                        person.addPhoneDetails(numberDetail);
                        System.out.println("Do you want to add another number?\nType yes or no");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    phoneDirectory.mapPersonAgainstName(person);
                    break;
                case 2:
                    System.out.println("Enter the person name");
                    String nameOfThePersonToBeRetrieved = scanner.nextLine();
                    if (!phoneDirectory.retrievePersonByName(nameOfThePersonToBeRetrieved)) {
                        System.out.println("\n******No persons found with this name******");
                    }
                    break;
                case 3:
                    System.out.println("Enter the partial name of the person");
                    String partialName = scanner.nextLine();
                    if (!phoneDirectory.retrievePersonByPartialName(partialName)) {
                        System.out.println("\n*****No persons found related to this name*****");
                    }
                    break;
                case 4:
                    System.out.println("Enter the phone no of the person");
                    Long personPhoneNumber = scanner.nextLong();
                    scanner.nextLine();
                    if (!phoneDirectory.retrievePersonByPhoneNumber(personPhoneNumber)) {
                        System.out.println("\n******No persons found with that phone number*****");
                    }
                    break;
                case 5:
                    break;
            }
        } while (choice != 5);
    }
}
