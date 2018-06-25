package week1.day4.animalcreation;

public class Dog extends week1.day4.animalcreation.Mammal{
	// Why do I need to do this? isn't Parent class constructor get's called automatically?
	// Read and let me know
        @Override
	public void printAdditionalInfo(){
		super.printAdditionalInfo();
		System.out.println("It can bark");
	}
}