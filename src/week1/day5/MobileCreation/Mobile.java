package week1.day5.MobileCreation;

public class Mobile{
	private String name;
	private Integer remainingCharge=100;
	public Mobile(String name){
		this.name=name;
	}
	public void remainingCharge(){
		System.out.println("Remaining charge:\t"+remainingCharge);
	}
	public void name(){
	System.out.println("Name\t\t: "+name);
	}
}