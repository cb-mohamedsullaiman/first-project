package oopsjava;

public class Cow extends oopsjava.Mammal{
	public Cow(){
		this.setHerbivorous(true);
		this.setRunnable(false);
	}
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is worshipped by many people in India");
	}
}