package oopsjava;

public class Ostrich extends oopsjava.Bird{
	public Ostrich(){
		this.setHerbivorous(true);
		this.setRunnable(true);
		this.setFlyable(false);
	}
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is the largest bird which cannot fly");
	}
}
