package collectionsjava;
import java.util.Comparator;

public class PhoneNumber implements Comparator{
	private Long phoneNumber;
	private String type;
	public int compare(PhoneNumber phoneNumber1,PhoneNumber phoneNumber2){
		return phoneNumber1.getPhoneNumber()-phoneNumber2.getPhoneNumber();
	}
	public boolean equals(PhoneNumber phoneNumber){
		return this.getPhoneNumber().equals(phoneNumber.getPhoneNumber());
	}
	public int hashCode(){
		return this.getPhoneNumber().hashCode();
	}
	public void setPhoneNumber(Long phoneNumber){
		this.phoneNumber=phoneNumber;	
	}
	public void setType(String type){
		this.type=type;
	}
	public Long getPhoneNumber(){
		return phoneNumber;
	}
	public String getType(){
		return type;
	}
	public void printPhoneNumber(){
		System.out.println(phoneNumber+"\t-"+type);
	}
}