package week1.day4.animalcreation;

public class Ostrich extends week1.day4.animalcreation.Bird{
	public Ostrich(){
		this.setHerbivorous(true);
		this.setRunnable(true);
		this.setFlyable(false);
	}
        @Override
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is the largest bird which cannot fly");
	}
}
