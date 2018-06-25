/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4;
import week2.day1_2.phonedirectory.Person;
import week2.day1_2.phonedirectory.Name;
import week2.day1_2.phonedirectory.PhoneNumberDetails;

import java.util.ArrayList;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class PhoneDirectory {
    public static void readFromCSV(Map<String,ArrayList<Person>> map, Path sourcePath){
        
		try(BufferedReader bufferedReader = Files.newBufferedReader(sourcePath); CSVParser csvParser = CSVParser.parse(bufferedReader,CSVFormat.DEFAULT.withHeader().withSkipHeaderRecord().withIgnoreHeaderCase().withTrim());){
                    
			for(CSVRecord csvRecord : csvParser){
                            
				String name = csvRecord.get("Name");
				String address = csvRecord.get("Address");
                                Long phoneNumber1 = Long.parseLong(csvRecord.get("Phone Number1"));
				String type1 = csvRecord.get("Type1");
				Long phoneNumber2 = Long.parseLong(csvRecord.get("Phone Number2"));
				String type2 = csvRecord.get("Type2");
				Long phoneNumber3 = Long.parseLong(csvRecord.get("Phone Number3"));
				String type3 = csvRecord.get("Type3");
				
				Person person = new Person();
                                Name nameObject=new Name();
                                nameObject.setName(name);
                                person.setName(nameObject);
                                person.setAddress(address);
                                PhoneNumberDetails numberDetail1 = new PhoneNumberDetails();
                                numberDetail1.setPhoneNumber(phoneNumber1);
                                numberDetail1.setType(PhoneNumberDetails.Type.valueOf(type1.toUpperCase()));
                                person.addPhoneDetails(numberDetail1);
                                PhoneNumberDetails numberDetail2 = new PhoneNumberDetails();
                                numberDetail2.setPhoneNumber(phoneNumber2);
                                numberDetail2.setType(PhoneNumberDetails.Type.valueOf(type2.toUpperCase()));
                                person.addPhoneDetails(numberDetail2);
                                PhoneNumberDetails numberDetail3 = new PhoneNumberDetails();
                                numberDetail3.setPhoneNumber(phoneNumber3);
                                numberDetail3.setType(PhoneNumberDetails.Type.valueOf(type3.toUpperCase()));
                                person.addPhoneDetails(numberDetail3);
                                ArrayList<Person> persons = new ArrayList<>();
                                if(map.containsKey(name)){
                                    persons = map.get(name);
                                }
                                persons.add(person);
                                map.put(name,persons);
			}
//                        Iterator<Map.Entry> iterator = 
		}
                catch(IOException ioException){
                    System.out.println("IO exception while reading file");
                }
	}
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		Integer choice;
		Map<String,ArrayList<Person>> map=new TreeMap<>();
		Path sourcePath = Paths.get("/Users/cb-mohamedsullaiman/sample/phone_directory.csv");
		readFromCSV(map,sourcePath);
		do{
			System.out.println("1.Retrieve the person details based on name\n2.Retrieve the person details based on partial matching\n3.Retrieve the person details based on phone number\n4.Exit");
			choice=scanner.nextInt();
			scanner.nextLine();
			switch(choice){
				case 1:
					System.out.println("Enter the person name");
					String personName=scanner.nextLine();
					if(map.containsKey(personName)){
						for(int i=0;i<map.get(personName).size();i++){
							map.get(personName).get(i).printPerson();
						}
					}
					else{
						System.out.println("\n******No persons found with that name******");
					}
					break;
				case 2:
					System.out.println("Enter the partial name of the person");
					String partialName=scanner.nextLine();
					Integer flag=0;
					for(Map.Entry<String,ArrayList<Person>> mapEntry : map.entrySet()){
						String tempName[]=mapEntry.getKey().split(" ",3);
						if(Arrays.asList(tempName).contains(partialName)){
							for(int i=0;i<mapEntry.getValue().size();i++){
								mapEntry.getValue().get(i).printPerson();
							}
							flag=1;
						}
					}
					if(flag==0){
						System.out.println("\n*****No persons are related to this name*****");
					}
					break;
				case 3:
					flag=0;
					System.out.println("Enter the phone no of the person");
					Long personPhoneNumber=scanner.nextLong();
					scanner.nextLine();
					for(Map.Entry<String,ArrayList<Person>> mapEntry : map.entrySet()){
						for(int i=0;i<mapEntry.getValue().size();i++){
							Iterator iterator=mapEntry.getValue().get(i).getPhoneNumbers().iterator();
							while(iterator.hasNext()){
								PhoneNumberDetails numberDetail=(PhoneNumberDetails)iterator.next();
								if(numberDetail.getPhoneNumber().equals(personPhoneNumber)){
									mapEntry.getValue().get(i).printPerson();
									flag=1;
								}
							}
						}
					}
					if(flag==0){
						System.out.println("\n******No persons are found with this phone number*****");
					}
					break;
				case 4:
					break;

			}
		}while(choice!=4);
	}
}
