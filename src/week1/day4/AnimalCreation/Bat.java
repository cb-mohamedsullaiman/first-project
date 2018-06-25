package oopsjava;

public class Bat extends oopsjava.Mammal{
	public Bat(){
		this.setFlyable(true);
		this.setHerbivorous(true);
		this.setRunnable(false);
		this.setWalkable(false);
	}
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is the only mammal which can fly");
	}
}