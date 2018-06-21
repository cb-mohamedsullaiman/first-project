package oopsjava;

public class Dog extends oopsjava.Mammal{
	// Why do I need to do this? isn't Parent class constructor get's called automatically?
	// Read and let me know
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