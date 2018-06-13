package oopsjava;

public class Cow extends oopsjava.Mammal{
	public Cow(){
		super();
		this.setIsHerbivore(true);
	}
	public void getMovement(){
		System.out.println("It can walk");
	}
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is worshipped by many people in India");
	}
}