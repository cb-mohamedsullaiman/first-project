package week1.day4.animalcreation;

public class Bat extends week1.day4.animalcreation.Mammal{
	public Bat(){
		this.setFlyable(true);
		this.setHerbivorous(true);
		this.setRunnable(false);
		this.setWalkable(false);
	}
        @Override
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is the only mammal which can fly");
	}
}