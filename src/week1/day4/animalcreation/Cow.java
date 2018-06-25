package week1.day4.animalcreation;

public class Cow extends week1.day4.animalcreation.Mammal{
	public Cow(){
		this.setHerbivorous(true);
		this.setRunnable(false);
	}
        @Override
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It is worshipped by many people in India");
	}
}