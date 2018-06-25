package week1.day4.animalcreation;

public class Parrot extends week1.day4.animalcreation.Bird{
	public Parrot(){
		this.setFlyable(true);
		this.setHerbivorous(true);
	}
        @Override
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is a pet");
	}
}
