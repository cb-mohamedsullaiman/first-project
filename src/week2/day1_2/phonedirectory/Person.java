package week2.day1_2.phonedirectory;
import java.util.HashSet;
import java.util.Iterator;

public class Person{
	private Name name;
	private String address;
	private HashSet<PhoneNumberDetails> numberDetails;
	public Person(){
		numberDetails=new HashSet<>();
        }
	public void setName(Name name){
		this.name=name;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public Boolean addPhoneDetails(PhoneNumberDetails numberDetails){
		return (this.numberDetails.add(numberDetails));
	}
	public Name getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
	public HashSet<PhoneNumberDetails> getPhoneNumbers(){
		return numberDetails;
	}
	public void printPerson(){
		System.out.println("Name\t:"+name.getName());
		System.out.println("Address\t:"+address);
		Iterator iterator=numberDetails.iterator();
		while(iterator.hasNext()){
			PhoneNumberDetails numberDetail=(PhoneNumberDetails)iterator.next();
			numberDetail.printPhoneNumber();
		}
		System.out.println("\n");
	}

}