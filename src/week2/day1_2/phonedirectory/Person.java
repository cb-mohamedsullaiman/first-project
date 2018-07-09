package week2.day1_2.phonedirectory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Person{
	private String name;
	private String address;
	private List<PhoneNumberDetails> numberDetails = new ArrayList<>();
	
	public void setName(String name){
		this.name=name;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public void addPhoneDetails(PhoneNumberDetails numberDetails){
		this.numberDetails.add(numberDetails);
	}
	public String getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
	public List<PhoneNumberDetails> getPhoneNumbers(){
		return numberDetails;
	}

}