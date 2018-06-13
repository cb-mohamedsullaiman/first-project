package oopsjava;

public abstract class Mammal extends oopsjava.Animal{
	public Mammal(){
		super();
	}
	public void hasHair(){
		System.out.println("It has hair");
	}
	public void printAdditionalInfo(){
		System.out.println("It can produce milk");
	}
}