package collectionsjava;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Person{
	private Name name;
	private String address;
	private HashSet<PhoneNumber> phoneNumbers;
	public Person(){
		phoneNumbers=new HashSet<PhoneNumber>();
	}
	public void setName(Name name){
		this.name=name;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public Boolean addPhoneNumber(PhoneNumber phoneNumber){
		return (this.phoneNumbers.add(phoneNumber));
	}
	public Name getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
	public HashSet<PhoneNumber> getPhoneNumbers(){
		return phoneNumbers;
	}
	public void printPerson(){
		System.out.println("Name\t:"+name.getName());
		System.out.println("Address\t:"+address);
		Iterator iterator=phoneNumbers.iterator();
		while(iterator.hasNext()){
			PhoneNumber phoneNumber=(PhoneNumber)iterator.next();
			phoneNumber.printPhoneNumber();
		}
		System.out.println("\n");
	}

}