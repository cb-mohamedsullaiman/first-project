package week1.day3.animalcreation;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalCreation{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		Boolean choice;
		ArrayList<week1.day3.animalcreation.Animal> animals =new ArrayList<week1.day3.animalcreation.Animal>();			
		do{
			System.out.println("Enter the animal name");
			String name=scanner.nextLine();					
			System.out.println("Enter the number of legs");
			Integer noOfLegs=new Integer(scanner.nextInt());					
			System.out.println("Enter true if it is vegetarian and false if it not");				
			Boolean vegetarian=new Boolean(scanner.nextBoolean());				
			week1.day3.animalcreation.Animal animal=new week1.day3.animalcreation.Animal(name,noOfLegs,vegetarian);
			animals.add(animal);
			System.out.println("Animal added");
			System.out.println("Animals added so far: "+animals.size());
			System.out.println("Do you want to add more?\nIf yes press true and if not press false");
			choice=new Boolean(scanner.nextBoolean());
			scanner.nextLine();
		}while(choice==true);

	}
}

			
	
		
	
