package oopsjava;

public abstract class Bird extends oopsjava.Animal{
	public Bird(){
		super();
	}
	public void hasHair(){
		System.out.println("It has feathers");
	}
	public void printAdditionalInfo(){
		System.out.println("It has beak");
	}
}