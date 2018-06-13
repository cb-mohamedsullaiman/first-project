package oopsjava;

public class Parrot extends oopsjava.Bird{
	public Parrot(){
		super();
		this.setCanFly(true);
		this.setIsHerbivore(true);
	}
	public void getMovement(){
		System.out.println("It will fly");
	}
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is a pet");
	}
}
