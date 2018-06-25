package collectionsjava;
import java.util.Scanner;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

public class PhoneDirectory{
	public static void main(String args[]){
		PhoneDirectory phoneDirectory=new PhoneDirectory();
		Scanner scanner=new Scanner(System.in);
		Integer choice;
		Map<String,ArrayList<Person>> map=new TreeMap<String,ArrayList<Person>>();
		do{
			System.out.println("1.Enter a person detail into phone directory\n2.Retrieve the person details based on name\n3.Retrieve the person details based on partial matching\n4.Retrieve the person details based on phone number\n5.Exit");
			choice=scanner.nextInt();
			scanner.nextLine();
			switch(choice){
				case 1:
					Person person=new Person();
					Name name=new Name();
					System.out.println("Enter the person name:");
					name.setName(scanner.nextLine());
					person.setName(name);
					System.out.println("Enter the address:");
					person.setAddress(scanner.nextLine());
					PhoneNumber phoneNumber;
					do{
						phoneNumber=new PhoneNumber();
						System.out.println("Enter the phone number:");
						Long personPhoneNumber=scanner.nextLong();
						for(Map.Entry<String,ArrayList<Person>> mapEntry : map.entrySet()){
							for(int i=0;i<mapEntry.getValue().size();i++){
								Iterator iterator=mapEntry.getValue().get(i).getPhoneNumbers().iterator();
								while(iterator.hasNext()){
									phoneNumber=(PhoneNumber)iterator.next();
									if(phoneNumber.getPhoneNumber()==personPhoneNumber){
										System.out.println("Phone number already exists");
										continue;
									}
								}
							}	
						}
						phoneNumber.setPhoneNumber(personPhoneNumber);
						System.out.println("Is it your\n\t1.mobile number\n\t2.home number\n\t3.work number");
						Integer phoneChoice=scanner.nextInt();
						scanner.nextLine();
						switch(phoneChoice){
							case 1: 
								phoneNumber.setType("Mobile");
								if(!person.addPhoneNumber(phoneNumber)){
									System.out.println("Phone Number exists");
								}
								break;
							case 2:
								phoneNumber.setType("Home");
								if(!person.addPhoneNumber(phoneNumber)){
									System.out.println("Phone Number already exists");
								}
								break;
							case 3:
								phoneNumber.setType("Work");
								if(!person.addPhoneNumber(phoneNumber)){
									System.out.println("Phone Number already exists");
								}
								break;
							default:
								System.out.println("Invalid choice");
						}
						System.out.println("Do you want to add another number\nIf yes type true and if not type false");
						if(scanner.nextBoolean()==false){
							break;
						}
					}while(true);
					ArrayList<Person> persons=new ArrayList<Person>();
					if(map.containsKey(name.getName())){
						persons=map.get(name.getName());
					}
					persons.add(person);
					map.put(name.getName(),persons);
					break;
				case 2:
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
				case 3:
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
				case 4:
					flag=0;
					System.out.println("Enter the phone no of the person");
					Long personPhoneNumber=scanner.nextLong();
					scanner.nextLine();
					for(Map.Entry<String,ArrayList<Person>> mapEntry : map.entrySet()){
						for(int i=0;i<mapEntry.getValue().size();i++){
							Iterator iterator=mapEntry.getValue().get(i).getPhoneNumbers().iterator();
							while(iterator.hasNext()){
								phoneNumber=(PhoneNumber)iterator.next();
								if(phoneNumber.getPhoneNumber()==personPhoneNumber){
									mapEntry.getValue().get(i).printPerson();
									flag=1;
									continue;
								}
							}
						}
					}
					if(flag==0){
						System.out.println("\n******No persons are found with this phone number*****");
					}
					break;
				case 5:
					break;

			}
		}while(choice!=5);
	}
}