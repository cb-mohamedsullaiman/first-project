package oopsjava;

public class Ostrich extends oopsjava.Bird{
	public Ostrich(){
		super();
		this.setIsHerbivore(true);
	}
	public void getMovement(){
		System.out.println("It can run but can't fly");
	}
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is the largest bird");
	}
}
