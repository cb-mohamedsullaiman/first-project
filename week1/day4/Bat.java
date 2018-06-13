package oopsjava;

public class Bat extends oopsjava.Mammal{
	public Bat(){
		super();
		this.setCanFly(true);
	}
	public void getMovement(){
		System.out.println("It can fly");
	}
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is the only mammal which can fly");
	}
}