package oopsjava;
import java.util.Scanner;
import java.util.ArrayList;

public class AnimalCreation{
	public static void printFly(ArrayList<oopsjava.Animal> animals){
		if(animals.size()==0){
			System.out.println("\n*****No animals to print*****");
		}
		else{
			ArrayList<oopsjava.Animal> animalFly=new ArrayList<oopsjava.Animal>();
			for(int i=0;i<animals.size();i++){
				oopsjava.Animal animal=animals.get(i);
				if(animal.getCanFly()){
					animalFly.add(animal);
				}
			}
			if(animalFly.size()>0){
				printAnimals(animalFly);
			}
			else{
				System.out.println("\n******No flying animals to print******");
			}
		}
	}
	public static void printHerbivore(ArrayList<oopsjava.Animal> animals){
		if(animals.size()>0){
			ArrayList<oopsjava.Animal> animalHerbivore=new ArrayList<oopsjava.Animal>();
			for(int i=0;i<animals.size();i++){
				Animal animal=animals.get(i);
				if(animal.getIsHerbivore()){
					animalHerbivore.add(animal);
				}
			}
			if(animalHerbivore.size()>0){
				printAnimals(animalHerbivore);
			}
			else{
				System.out.println("\n******No herbivores to print******");
			}
		}
		else{
			System.out.println("\n*****No animals to print*****");
		}
	}
	public static void printAnimals(ArrayList<oopsjava.Animal> animals){
		if(animals.size()==0){
			System.out.println("No animals to print");
		}
		else{
			for(int i=0;i<animals.size();i++){
				oopsjava.Animal animal=animals.get(i);
				System.out.println("Animal name \t"+animal.getName());
				System.out.println("Animal type\t"+animal.getType());
				System.out.println("Description: ");
				animal.getMovement();
				animal.hasHair();
				animal.printAdditionalInfo();
				System.out.println("\n");
			}
		}
	}			
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		ArrayList<oopsjava.Animal> animals=new ArrayList<oopsjava.Animal>();	//Creating the animal list
		System.out.println("1.Add an animal\n2.Print the added animals\n3.Print the animals which can fly\n4.Print the animals which are herbivores\n5.Exit\nEnter your choice:");
		Integer choice=scanner.nextInt();
		scanner.nextLine();
		do{
			switch(choice){
				case 1:
					System.out.println("1.Bat \n2.Dog\n3.Cow\n4.Ostrich\n5.Parrot\nPress the number to create that animal");
					int animalChoice=scanner.nextInt();
					scanner.nextLine();
					Animal animal;
					switch(animalChoice){	//Creating a choice of animal
						case 1:
							animal=new Bat();
							animal.setType("Bat");
							break;
						case 2:
							 animal=new Dog();
							animal.setType("Dog");
							break;
						case 3:
							animal=new Cow();
							animal.setType("Cow");
							break;
						case 4:
							animal=new Ostrich();
							animal.setType("Ostrich");
							break;
						case 5:
							animal=new Parrot();
							animal.setType("Parrot");
							break;
						default: 
							System.out.println("Wrong choice");
							continue;
					}
					System.out.println("Enter the name for your animal");
					animal.setName(scanner.nextLine());
					animals.add(animal);
					break;
				case 2:
					if(animals.size()>0){
						printAnimals(animals);
					}
					else{
						System.out.println("\n*****Animal List is empty*****");
					}
					break;
				case 3:
					printFly(animals);
					break;
				case 4:
					printHerbivore(animals);
					break;
				case 5:
					break;
				default:
					System.out.println("Wrong choice");
			}
			System.out.println("1.Add an animal\n2.Print the added animals\n3.Print the animals which can fly\n4.Print the animals which are herbivores\n5.Exit\nEnter your choice:");
			choice=scanner.nextInt();
			scanner.nextLine();
		}while(choice!=5);
	}
} 	
