package oopsjava;

public class Dog extends oopsjava.Mammal{
	public Dog(){
		super();
	}
	public void getMovement(){
		System.out.println("It can run");
	}
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It can bark");
	}
}