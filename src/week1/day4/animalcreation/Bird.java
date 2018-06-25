package week1.day4.animalcreation;

public abstract class Bird extends Animal{
	public Bird(){
		super();
	}
        @Override
	public void printAdditionalInfo(){
		System.out.println("It has beak");
	}
}