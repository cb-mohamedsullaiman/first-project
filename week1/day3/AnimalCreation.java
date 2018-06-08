package classbasics;
import java.util.ArrayList;
import java.util.Scanner;
class Animal{
	String name;
	int numOfLegs;
	boolean vegetarian;
	static int animalCount;
	public Animal(){			//Constructor with zero parameter
		name=null;
		numOfLegs=4;
		vegetarian=false;
		animalCount++;
	}
	public void addAnimal(String name,int numOfLegs,boolean veg){			//Method for setting parameters for animal
		this.name=name;
		this.numOfLegs=numOfLegs;
		vegetarian=veg;
	}
	public void printAnimal(){							//Method for printing animal details
		System.out.println("The name of the animal is"+name);
		System.out.println("It has "+numOfLegs+" legs");
		if(vegetarian==false){
			System.out.println("It is not a vegetarian");
		}
		else{
			System.out.println("It is a vegetarian");
		}
	}
	int getCount(){									//Method for getting count of animals
		return animalCount;
	}
}
class AnimalCreation{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		boolean choice;
		ArrayList<Animal> animals =new ArrayList<Animal>();			//Creating a list for animals
		do{
			System.out.println("Enter the animal name");
			String name=scanner.nextLine();					//Getting the animal name
			System.out.println("Enter the number of legs");
			int noOfLegs=scanner.nextInt();					//Getting the no of legs for animal
			System.out.println("Enter true if it is vegetarian and false if it not");
			boolean veg=scanner.nextBoolean();				//Getting the food manner of animal either vegetarian or not
			animals.add(new Animal());					//adding a new animal to list
			animals.get(animals.size()-1).addAnimal(name,noOfLegs,veg);	//setting the parameters for the currently added animal
			System.out.println("Number of animals added so far:"+animals.get(animals.size()-1).getCount());		//Printing the number of animals
			System.out.println("Do you want to add another animal? If so enter true and if not enter false");
			choice=scanner.nextBoolean();					//Getting the choice of user to add more animal
			scanner.nextLine();
		}while(choice==true);
	}
}

			
	
		
	
