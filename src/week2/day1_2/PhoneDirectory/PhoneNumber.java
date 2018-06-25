package collectionsjava;

public class PhoneNumber{
	private Long phoneNumber;
	private String type;
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