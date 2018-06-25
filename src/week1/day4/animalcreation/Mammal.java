package week1.day4.animalcreation;

public abstract class Mammal extends Animal{
	public Mammal(){
		super();
	}
	@Override
	public void printAdditionalInfo(){
		System.out.println("It can produce milk");
	}
}