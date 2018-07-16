/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week3.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import week2.day1_2.phonedirectory.PhoneNumberDetails.TypeOfUsage;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class PhoneDirectory {

    public void createDatabaseAndTables() throws SQLException {
        //Creating required database and required tables..
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null)) {
            
            Statement statement = connection.createStatement();
            String databaseCreationQuery = "create database phone_directory";
            statement.execute(databaseCreationQuery);
            String personTableCreationQuery = "create table phone_directory.person_details (id int primary key auto_increment,name varchar(30) not null,address varchar(50))";
            statement.execute(personTableCreationQuery);
            String phoneNumbersTableCreationQuery = "create table phone_directory.phone_number_details (phone_number bigint unique key not null, person_id int not null,phone_usage_type Enum('WORK','HOME','MOBILE'),foreign key(person_id) references phone_directory.person_details(id),primary key(person_id,phone_number))";
            statement.execute(phoneNumbersTableCreationQuery);
            
        }
    }

    public void populateDatabase(Path path) throws IOException, SQLException {
        //The file can be a csv or json.. I chose to support both type of files
        switch (FilenameUtils.getExtension(path.toString())) {
            case "csv":
                populateDatabaseUsingCSV(path);
                break;
            case "json":
                populateDatabaseUsingJson(path);
                break;
        }
    }

    public void populateDatabaseUsingCSV(Path path) throws IOException, SQLException {
        // Populating the database with data from csv
        BufferedReader bufferedReader = null;
        CSVParser csvParser = null;
        Connection connection = null;
        PreparedStatement personTableInsertionQuery = null;
        PreparedStatement phoneTableInsertionQuery = null;
        
        try {
            
            bufferedReader = Files.newBufferedReader(path);
            csvParser = CSVParser.parse(bufferedReader, CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord().withIgnoreHeaderCase().withTrim());
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personTableInsertionQuery = connection.prepareStatement("insert into phone_directory.person_details(name, address) values ( ? , ?) ;");
            phoneTableInsertionQuery = connection.prepareStatement("insert into phone_directory.phone_number_details values(?,?,?);");
            
            for (CSVRecord csvRecord : csvParser) {
                
                String name = csvRecord.get(0);                 //Name
                String address = csvRecord.get(1);              //Address

                personTableInsertionQuery.setString(1, name);
                personTableInsertionQuery.setString(2, address);
                personTableInsertionQuery.executeUpdate();

                for (int i = 2; i < csvRecord.size(); i += 2) {  //Getting all the phone number details( phone number, type of usage) -Starting from index 2(0-name,1-address)
                    
                    Long phoneNumber = Long.parseLong(csvRecord.get(i));
                    Integer personId = (int) csvRecord.getRecordNumber();       //We are casting it to int since it returns long.. we can also use long if we handling large number of persons
                    String typeOfUsage = csvRecord.get(i + 1);
                    
                    phoneTableInsertionQuery.setLong(1, phoneNumber);
                    phoneTableInsertionQuery.setInt(2, personId);                    
                    phoneTableInsertionQuery.setString(3, typeOfUsage);
                    phoneTableInsertionQuery.executeUpdate();
                }

            }
        } finally {
            if (csvParser != null) {
                csvParser.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (personTableInsertionQuery != null) {
                personTableInsertionQuery.close();
            }
            if (phoneTableInsertionQuery != null) {
                phoneTableInsertionQuery.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void populateDatabaseUsingJson(Path path) throws IOException, SQLException {
        //Populating database with data from json file
        BufferedReader bufferedReader = null;
        CSVParser csvParser = null;
        Connection connection = null;
        PreparedStatement personTableInsertionQuery = null;
        PreparedStatement phoneTableInsertionQuery = null;
        try {
            bufferedReader = Files.newBufferedReader(path);
            JSONObject root = new JSONObject(new JSONTokener(bufferedReader));
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personTableInsertionQuery = connection.prepareStatement("insert into phone_directory.person_details(name, address) values ( ? , ?) ;");
            phoneTableInsertionQuery = connection.prepareStatement("insert into phone_directory.phone_number_details values(?,?,?);");

            //Getting all the persons
            JSONArray jsonPersons = root.getJSONArray("Persons");
            for (int i = 0; i < jsonPersons.length(); i++) {

                //Getting a single person
                JSONObject jsonPerson = jsonPersons.getJSONObject(i);

                String name = jsonPerson.getString("Name");
                String address = jsonPerson.getString("Address");

                personTableInsertionQuery.setString(1, name);
                personTableInsertionQuery.setString(2, address);

                personTableInsertionQuery.executeUpdate();

                JSONArray jsonPhoneDetails = jsonPerson.getJSONArray("Phone Number Details");

                for (int j = 0; j < jsonPhoneDetails.length(); j++) {

                    JSONObject jsonPhoneDetail = jsonPhoneDetails.getJSONObject(j);
                    Long phoneNumber = jsonPhoneDetail.getLong("Phone Number");
                    String phoneUsageType = jsonPhoneDetail.getString("Type");

                    phoneTableInsertionQuery.setLong(1, phoneNumber);
                    phoneTableInsertionQuery.setInt(2, i + 1);                  // adding the person id 
                    phoneTableInsertionQuery.setString(3, phoneUsageType);

                    phoneTableInsertionQuery.executeUpdate();
                }
            }
        } finally {
            if (csvParser != null) {
                csvParser.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (personTableInsertionQuery != null) {
                personTableInsertionQuery.close();
            }
            if (phoneTableInsertionQuery != null) {
                phoneTableInsertionQuery.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Boolean addPerson(String name, String address, Map<Long, TypeOfUsage> phoneNumberMap) throws SQLException {    //Phone number map Pair - >(number,typeofUsage)
        //Adding the person to the database
        Connection connection = null;
        PreparedStatement personTableInsertionQuery = null;
        PreparedStatement phoneNumberTableInsertionQuery = null;
        ResultSet resultSet = null;
        Integer numberOfRowsChangedInPersonsTable;
        Integer numberOfRowsChangedInPhoneNumbersTable = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personTableInsertionQuery = connection.prepareStatement("insert into phone_directory.person_details(name,address) values(?,?);", Statement.RETURN_GENERATED_KEYS);
            phoneNumberTableInsertionQuery = connection.prepareStatement("insert into phone_directory.phone_number_details values(?,?,?);");

            personTableInsertionQuery.setString(1, name);
            personTableInsertionQuery.setString(2, address);
            numberOfRowsChangedInPersonsTable = personTableInsertionQuery.executeUpdate();
            System.out.println("*******" + numberOfRowsChangedInPersonsTable + " row(s) changed in table person_details*********");
            resultSet = personTableInsertionQuery.getGeneratedKeys();
            Integer lastInsertedId = null;
            if (resultSet.next()) {
                lastInsertedId = resultSet.getInt(1);
            }

            for (Map.Entry mapEntry : phoneNumberMap.entrySet()) {
                phoneNumberTableInsertionQuery.setLong(1, (Long) mapEntry.getKey());
                phoneNumberTableInsertionQuery.setInt(2, lastInsertedId);
                phoneNumberTableInsertionQuery.setString(3, mapEntry.getValue().toString());
                numberOfRowsChangedInPhoneNumbersTable += phoneNumberTableInsertionQuery.executeUpdate();
            }
            System.out.println("*********" + numberOfRowsChangedInPhoneNumbersTable + " row(s) changed in table phone_number_details*********");
        } finally {
            if (personTableInsertionQuery != null) {
                personTableInsertionQuery.close();
            }
            if (phoneNumberTableInsertionQuery != null) {
                phoneNumberTableInsertionQuery.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return !(numberOfRowsChangedInPersonsTable.equals(0) || numberOfRowsChangedInPhoneNumbersTable.equals(0));
    }

    public Boolean addPhoneNumberToPerson(Integer personId, Long phoneNumber, String phoneUsageType) throws SQLException {
        Connection connection = null;
        PreparedStatement personTableSelectionQuery = null;
        PreparedStatement phoneNumberTableInsertionQuery = null;
        ResultSet resultSet = null;
        Integer numberOfRowsChangedInPhoneNumbersTable = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personTableSelectionQuery = connection.prepareStatement("select * from phone_directory.person_details where id = ?;");
            phoneNumberTableInsertionQuery = connection.prepareStatement("insert into phone_directory.phone_number_details values(?,?,?);");

            personTableSelectionQuery.setInt(1, personId);
            resultSet = personTableSelectionQuery.executeQuery();
            if (!resultSet.next()) {
                return false;
            }

            phoneNumberTableInsertionQuery.setLong(1, phoneNumber);
            phoneNumberTableInsertionQuery.setInt(2, personId);
            phoneNumberTableInsertionQuery.setString(3, phoneUsageType);
            numberOfRowsChangedInPhoneNumbersTable += phoneNumberTableInsertionQuery.executeUpdate();
            System.out.println("\n******" + numberOfRowsChangedInPhoneNumbersTable + " row(s) changed in table phone_number_details*********");
        } finally {
            if (personTableSelectionQuery != null) {
                personTableSelectionQuery.close();
            }
            if (phoneNumberTableInsertionQuery != null) {
                phoneNumberTableInsertionQuery.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return !numberOfRowsChangedInPhoneNumbersTable.equals(0);
    }

    public Boolean changePersonNameById(Integer id, String name) throws SQLException {
        Connection connection = null;
        PreparedStatement personTableUpdateQuery = null;
        Integer numberOfRowsChangedInPersonsTable;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personTableUpdateQuery = connection.prepareStatement("update phone_directory.person_details set name = ? where id = ?");

            personTableUpdateQuery.setString(1, name);
            personTableUpdateQuery.setInt(2, id);
            numberOfRowsChangedInPersonsTable = personTableUpdateQuery.executeUpdate();
            System.out.println("\n*********" + numberOfRowsChangedInPersonsTable + " row(s) changed in table person_details**********");
        } finally {
            if (personTableUpdateQuery != null) {
                personTableUpdateQuery.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return !numberOfRowsChangedInPersonsTable.equals(0);
    }

    public Boolean changePersonAddressById(Integer id, String address) throws SQLException {
        Connection connection = null;
        PreparedStatement personTableUpdateQuery = null;
        Integer numberOfRowsChangedInPersonsTable;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personTableUpdateQuery = connection.prepareStatement("update phone_directory.person_details set address = ? where id = ?");

            personTableUpdateQuery.setString(1, address);
            personTableUpdateQuery.setInt(2, id);
            numberOfRowsChangedInPersonsTable = personTableUpdateQuery.executeUpdate();
            System.out.println("\n********" + numberOfRowsChangedInPersonsTable + " row(s) changed in table person_details********");
        } finally {
            if (personTableUpdateQuery != null) {
                personTableUpdateQuery.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return !numberOfRowsChangedInPersonsTable.equals(0);
    }

    public Boolean changePhoneNumberByPersonId(Integer personId, Long oldPhoneNumber, Long newPhoneNumber) throws SQLException {
        Connection connection = null;
        PreparedStatement phoneNumbersTableUpdateQuery = null;
        Integer numberOfRowsChangedInPhoneNumbersTable;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            phoneNumbersTableUpdateQuery = connection.prepareStatement("update phone_directory.phone_number_details set phone_number = ? where person_id = ? and phone_number = ?");

            phoneNumbersTableUpdateQuery.setLong(1, newPhoneNumber);
            phoneNumbersTableUpdateQuery.setInt(2, personId);
            phoneNumbersTableUpdateQuery.setLong(3, oldPhoneNumber);
            numberOfRowsChangedInPhoneNumbersTable = phoneNumbersTableUpdateQuery.executeUpdate();
            System.out.println("\n******" + numberOfRowsChangedInPhoneNumbersTable + " row(s) changed in table phone_number_details***********");
        } finally {
            if (phoneNumbersTableUpdateQuery != null) {
                phoneNumbersTableUpdateQuery.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return !numberOfRowsChangedInPhoneNumbersTable.equals(0);
    }

    public Boolean changePhoneUsageTypeByPersonId(Integer personId, Long phoneNumber, String typeOfUsage) throws SQLException {
        Connection connection = null;
        PreparedStatement phoneNumbersTableUpdateQuery = null;
        Integer numberOfRowsChangedInPhoneNumbersTable;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            phoneNumbersTableUpdateQuery = connection.prepareStatement("update phone_directory.phone_number_details set phone_usage_type = ? where person_id = ? and phone_number = ?");

            phoneNumbersTableUpdateQuery.setString(1, typeOfUsage);
            phoneNumbersTableUpdateQuery.setInt(2, personId);
            phoneNumbersTableUpdateQuery.setLong(3, phoneNumber);
            numberOfRowsChangedInPhoneNumbersTable = phoneNumbersTableUpdateQuery.executeUpdate();
            System.out.println("\n*********" + numberOfRowsChangedInPhoneNumbersTable + " row(s) changed in table phone_number_details**********");
        } finally {
            if (phoneNumbersTableUpdateQuery != null) {
                phoneNumbersTableUpdateQuery.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return !numberOfRowsChangedInPhoneNumbersTable.equals(0);
    }

    public Boolean retrievePersonDetailsByName(String name) throws SQLException {
        Connection connection = null;
        PreparedStatement personDetailsRetrievalQuery = null;
        ResultSet personDetailsResultSet = null;
        Boolean isRetrieved = false;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personDetailsRetrievalQuery = connection.prepareStatement("select t1.id, t1.name, t1.address, t2.phone_number, t2.phone_usage_type from phone_directory.person_details t1 left join phone_directory.phone_number_details t2 on t1.id = t2.person_id where t1.name = ?");

            personDetailsRetrievalQuery.setString(1, name);
            personDetailsResultSet = personDetailsRetrievalQuery.executeQuery();
            printPersonDetails(personDetailsResultSet);
            if (personDetailsResultSet.next()) {
                isRetrieved = true;
            }

        } finally {
            if (personDetailsRetrievalQuery != null) {
                personDetailsRetrievalQuery.close();
            }
            if (personDetailsResultSet != null) {
                personDetailsResultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isRetrieved;
    }

    public Boolean retrievePersonDetailsByPartialName(String partialName) throws SQLException {
        Connection connection = null;
        PreparedStatement personDetailsRetrievalQuery = null;
        ResultSet personDetailsResultSet = null;
        Boolean isRetrieved = false;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personDetailsRetrievalQuery = connection.prepareStatement("select t1.id, t1.name, t1.address, t2.phone_number, t2.phone_usage_type from phone_directory.person_details t1 left join phone_directory.phone_number_details t2 on t1.id = t2.person_id where t1.name like ? ;");

            personDetailsRetrievalQuery.setString(1, "%" + partialName + "%");
            personDetailsResultSet = personDetailsRetrievalQuery.executeQuery();
            printPersonDetails(personDetailsResultSet);
            if (personDetailsResultSet.next()) {
                isRetrieved = true;
            }

        } finally {
            if (personDetailsRetrievalQuery != null) {
                personDetailsRetrievalQuery.close();
            }
            if (personDetailsResultSet != null) {
                personDetailsResultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isRetrieved;
    }

    public Boolean retrievePersonDetailsByPhoneNumber(Long phoneNumber) throws SQLException {
        Connection connection = null;
        PreparedStatement personDetailsRetrievalQuery = null;
        ResultSet personDetailsResultSet = null;
        Boolean isRetrieved = false;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personDetailsRetrievalQuery = connection.prepareStatement("select t1.id, t1.name, t1.address, t2.phone_number, t2.phone_usage_type from phone_directory.person_details t1 left join phone_directory.phone_number_details t2 on t1.id = t2.person_id where t1.id = (select person_id from phone_directory.phone_number_details where phone_number = ? );");

            personDetailsRetrievalQuery.setLong(1, phoneNumber);
            personDetailsResultSet = personDetailsRetrievalQuery.executeQuery();
            printPersonDetails(personDetailsResultSet);
            if (personDetailsResultSet.next()) {
                isRetrieved = true;
            }

        } finally {
            if (personDetailsRetrievalQuery != null) {
                personDetailsRetrievalQuery.close();
            }
            if (personDetailsResultSet != null) {
                personDetailsResultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return isRetrieved;
    }

    public Boolean deletePhoneNumberByPersonId(Integer personId, Long phoneNumber) throws SQLException {
        Connection connection = null;
        PreparedStatement phoneNumberDeletionQuery = null;
        Integer numberOfRowsAffected;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            phoneNumberDeletionQuery = connection.prepareStatement("delete from phone_directory.phone_number_details where person_id = ? and phone_number = ?");

            phoneNumberDeletionQuery.setInt(1, personId);
            phoneNumberDeletionQuery.setLong(2, phoneNumber);
            numberOfRowsAffected = phoneNumberDeletionQuery.executeUpdate();
            System.out.println("\n******" + numberOfRowsAffected + " rows(s) affected in table phone_number_details******");
        } finally {
            if (phoneNumberDeletionQuery != null) {
                phoneNumberDeletionQuery.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return !numberOfRowsAffected.equals(0);
    }

    public Boolean deletePersonByPersonId(Integer personId) throws SQLException {
        Connection connection = null;
        PreparedStatement personDeletionQuery = null;
        PreparedStatement phoneNumberDeletionQuery = null;
        Integer numberOfRowsAffectedInPersonsTable;
        Integer numberOfRowsAffectedInPhoneNumbersTable;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
            personDeletionQuery = connection.prepareStatement("delete from phone_directory.person_details where id = ?");
            phoneNumberDeletionQuery = connection.prepareStatement("delete from phone_directory.phone_number_details where person_id = ?");

            phoneNumberDeletionQuery.setInt(1, personId);
            numberOfRowsAffectedInPhoneNumbersTable = phoneNumberDeletionQuery.executeUpdate();
            System.out.println("*****" + numberOfRowsAffectedInPhoneNumbersTable + " rows(s) affected in table phone_number_details*****");

            personDeletionQuery.setInt(1, personId);
            numberOfRowsAffectedInPersonsTable = personDeletionQuery.executeUpdate();
            System.out.println("******" + numberOfRowsAffectedInPersonsTable + "rows(s) affected in table person_details*****");

        } finally {
            if (personDeletionQuery != null) {
                personDeletionQuery.close();
            }
            if (phoneNumberDeletionQuery != null) {
                phoneNumberDeletionQuery.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return !(numberOfRowsAffectedInPersonsTable.equals(0) && numberOfRowsAffectedInPhoneNumbersTable.equals(0));
    }
    
    public void printPersonDetails(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return;
        }
        System.out.println("_________________________________________________________________________________________________________________________________________");
        System.out.println("\nId\t\tName\t\tAddress\t\tPhone Number\t\tPhone Number Usage Type");
        System.out.println("_________________________________________________________________________________________________________________________________________");
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1) + "\t\t" + resultSet.getString(2) + "\t\t" + resultSet.getString(3) + "\t\t" + resultSet.getLong(4) + "\t\t" + resultSet.getString(5));
        }
    }
    
    public void deleteDatabase() throws SQLException{
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null)) {
            Statement statement = connection.createStatement();
            String databaseDeletionQuery = "drop database if exists phone_directory";
            statement.execute(databaseDeletionQuery);            
        }
    }
    public static void main(String args[]) throws SQLException, IOException {

        PhoneDirectory phoneDirectory = new PhoneDirectory();
        
        Scanner scanner = new Scanner(System.in);
        
        Path sourcePath =Paths.get(System.getProperty("user.home")+ scanner.nextLine());
        
        if(Files.exists(sourcePath)){
            System.out.println("\n*****Sorry.. this path does not exists*****");
        }
        
        phoneDirectory.createDatabaseAndTables();       //Creating required databases
        
        phoneDirectory.populateDatabase(sourcePath);    //Populating the database with the data from the given csv file or json file

        Integer choice;
        do {
            System.out.println("\n1. Add a new person into phone directory\n2.Add a phone number to a person based on person id\n3.Change a person name based on person id\n4.Change a person address based on person id\n5.Change person phone number based on person id\n6.Change the usage type of phone number\n7.Retrieve the person details based on name\n8.Retrieve the person details based on partial matching\n9.Retrieve the person details based on phone number\n10.Delete a phone number based on person id\n11.Delete a person from phone directory\n12.Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    String name;
                    do {
                        System.out.println("Enter the person name:");
                        name = scanner.nextLine();
                    } while (name.length() == 0);
                    
                    String address;
                    do {
                        System.out.println("Enter the address:");
                        address = scanner.nextLine();
                    } while (address.length() == 0);
                    
                    Map<Long, TypeOfUsage> phoneMap = new HashMap<>();
                    do {
                        System.out.println("Enter the phone number:");
                        Long phoneNumber = scanner.nextLong();
                        System.out.println("Is it their\n\t1.mobile number\n\t2.home number\n\t3.work number");
                        Integer phoneChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (phoneChoice) {
                            case 1:
                                phoneMap.put(phoneNumber, TypeOfUsage.MOBILE);
                                break;
                            case 2:
                                phoneMap.put(phoneNumber, TypeOfUsage.HOME);
                                break;
                            case 3:
                                phoneMap.put(phoneNumber, TypeOfUsage.WORK);
                                break;
                            default:
                                System.out.println("\n*********Invalid choice***********");
                        }
                        System.out.println("Do you want to add another number?\nType yes or no");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    
                    if (phoneDirectory.addPerson(name, address, phoneMap)) {
                        System.out.println("\n*********Person added successfully to phone directory********");
                    } else {
                        System.out.println("\n*******Sorry.. There is a problem in adding this person with the phone directory completely*******");
                    }
                    break;
                case 2:
                    System.out.println("Enter the person id for whom you want to add phone number:");
                    Integer personId = scanner.nextInt();
                    System.out.println("Enter the phone number of the person");
                    Long phoneNumber = scanner.nextLong();
                    System.out.println("Enter the type of usage\n\t1.Mobile\n\t2.Work\n\t3.Home\n");
                    choice = scanner.nextInt();
                    Boolean isAdded = false;
                    switch (choice) {
                        case 1:
                            isAdded = phoneDirectory.addPhoneNumberToPerson(personId, phoneNumber, "Mobile");
                            break;
                        case 2:
                            isAdded = phoneDirectory.addPhoneNumberToPerson(personId, phoneNumber, "Work");
                            break;
                        case 3:
                            isAdded = phoneDirectory.addPhoneNumberToPerson(personId, phoneNumber, "Home");
                            break;
                    }
                    if (isAdded) {
                        System.out.println("\n******Phone number is successfully added to the person*****");
                    } else {
                        System.out.println("\n******Sorry.. There is a problem in adding phone number to this person******");
                    }
                    break;
                case 3:
                    System.out.println("Enter the person id for whom you want to change name:");
                    Integer id = scanner.nextInt();
                    scanner.nextLine();
                    do {
                        System.out.println("Enter the new name");
                        name = scanner.nextLine();
                    } while (name.length() == 0);
                    if (phoneDirectory.changePersonNameById(id, name)) {
                        System.out.println("\n******Successfully changed the person name***********");
                    } else {
                        System.out.println("\n******Sorry... There is a problem in changing the name of the person********");
                    }
                    break;
                case 4:
                    System.out.println("Enter the person id for whom you want to change address:");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    do {
                        System.out.println("Enter the new address");
                        address = scanner.nextLine();
                    } while (address.length() == 0);
                    if (phoneDirectory.changePersonAddressById(id, address)) {
                        System.out.println("\n******Successfully changed the person address***********");
                    } else {
                        System.out.println("\n******Sorry... There is a problem in changing the address of the person********");
                    }
                    break;
                case 5:
                    //We cannot change the phone number just by only knowing the person id.. we must have the old phone number
                    System.out.println("Enter the person id for whom you want to change phone number:");
                    personId = scanner.nextInt();
                    System.out.println("Enter the old phone number of the person");
                    Long oldPhoneNumber = scanner.nextLong();
                    System.out.println("Enter the new phone number");
                    Long newPhoneNumber = scanner.nextLong();
                    if (phoneDirectory.changePhoneNumberByPersonId(personId, oldPhoneNumber, newPhoneNumber)) {
                        System.out.println("\n******Successfully changed the person phone number***********");
                    } else {
                        System.out.println("\n******Sorry... There is a problem in changing the phone number of the person********");
                    }
                    break;
                case 6:
                    //We can change the phone number without getting person id but it is not a good idea
                    System.out.println("Enter the person id for whom you want to change usage type of phone :");
                    personId = scanner.nextInt();
                    System.out.println("Enter the  phone number of the person");
                    phoneNumber = scanner.nextLong();
                    System.out.println("Enter the new usage type\n\t1.Mobile\n\t2.Work\n\t3.Home");
                    choice = scanner.nextInt();
                    isAdded = false;                        
                    switch (choice) {
                        case 1:
                            isAdded = phoneDirectory.changePhoneUsageTypeByPersonId(personId, phoneNumber, "Mobile");
                            break;
                        case 2:
                            isAdded = phoneDirectory.changePhoneUsageTypeByPersonId(personId, phoneNumber, "Work");
                            break;
                        case 3:
                            isAdded = phoneDirectory.changePhoneUsageTypeByPersonId(personId, phoneNumber, "Home");
                            break;
                    }
                    if (isAdded) {
                        System.out.println("\n******Phone usage type is successfully added to the person*****");
                    } else {
                        System.out.println("\n******Sorry.. There is a problem in adding phone usage type to this person******");
                    }
                    break;
                case 7:
                    String nameOfThePersonToBeRetrieved;
                    do {
                        System.out.println("Enter the person name");
                        nameOfThePersonToBeRetrieved = scanner.nextLine();
                    } while (nameOfThePersonToBeRetrieved == null);
                    if (!phoneDirectory.retrievePersonDetailsByName(nameOfThePersonToBeRetrieved)) {
                        System.out.println("\n******No persons found with this name******");
                    }
                    break;
                case 8:
                    String partialName;
                    do {
                        System.out.println("Enter the partial name of the person");
                        partialName = scanner.nextLine();
                    } while (partialName == null);
                    if (!phoneDirectory.retrievePersonDetailsByPartialName(partialName)) {
                        System.out.println("\n*****No persons found related to this name*****");
                    }
                    break;
                case 9:
                    System.out.println("Enter the phone no of the person");
                    Long personPhoneNumber = scanner.nextLong();
                    scanner.nextLine();
                    if (!phoneDirectory.retrievePersonDetailsByPhoneNumber(personPhoneNumber)) {
                        System.out.println("\n******No persons found with that phone number*****");
                    }
                    break;
                case 10:
                    System.out.println("Enter the person id for whom you want to delete a phone number");
                    personId = scanner.nextInt();
                    System.out.println("Enter the phone number you want to delete");
                    phoneNumber = scanner.nextLong();
                    if (phoneDirectory.deletePhoneNumberByPersonId(personId, phoneNumber)) {
                        System.out.println("\n*********Phone Number deleted successfully******");
                    } else {
                        System.out.println("\n********Sorry.. There is a problem in deleting this phone number*******");
                    }
                    break;
                case 11:
                    System.out.println("Enter the id of the person you want to delete from the phone directory");
                    personId = scanner.nextInt();
                    if (phoneDirectory.deletePersonByPersonId(personId)) {
                        System.out.println("\n******Successfully deleted the person from phone directory********");
                    } else {
                        System.out.println("\n******Sorry... There is problem in deleting this person successfully");
                    }
                    break;
                case 12:
                    break;
                default:
                    System.out.println("\n*********Invalid choice*******");

            }
        } while (choice != 12);
        phoneDirectory.deleteDatabase();
    }
}
