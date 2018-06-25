package oopsjava;

public class Parrot extends oopsjava.Bird{
	public Parrot(){
		this.setFlyable(true);
		this.setHerbivorous(true);
	}
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is a pet");
	}
}
