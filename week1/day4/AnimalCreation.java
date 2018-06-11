package oopsjava;
import java.util.Scanner;
import java.util.ArrayList;
interface AnimalCharacter{
	public void movement();
	public void isHerbivorous();
	public void canFly();
}
abstract class Animal implements AnimalCharacter{
	String name;
	boolean isHerbivore;
	String type;
	boolean canFly;
	public Animal(){
		name=null;
		isHerbivore=false;
		type=null;
		canFly=false;
	}
	public void isHerbivorous(){
		if(isHerbivore==true){
			System.out.println("It is herbivorous");
		}
		else{
			System.out.println("It is not herbivorous");
		}
	}
	public void canFly(){
		if(canFly==true){
			System.out.println("It can fly");
		}
		else{
			System.out.println("It cannot fly");
		}
	}
	abstract public void hasHair();
	abstract public void additionalInfo();
	
}
abstract class Mammal extends Animal{
	public Mammal(){
		super();
	}
	public void hasHair(){
		System.out.println("It has hair");
	}
	public void additionalInfo(){
		System.out.println("It can produce milk");
	}
}
abstract class Bird extends Animal{
	public Bird(){
		super();
	}
	public void hasHair(){
		System.out.println("It has feathers");
	}
	public void additionalInfo(){
		System.out.println("It has wings");
	}
}
class Bat extends Mammal{
	public Bat(){
		super();
		canFly=true;
	}
	public void movement(){
		System.out.println("It can fly");
	}
	public void additionalInfo(){
		super.additionalInfo();
		System.out.println("It is the only mammal which can fly");
	}
}
class Dog extends Mammal{
	public Dog(){
		super();
	}
	public void movement(){
		System.out.println("It can run");
	}
	public void additionalInfo(){
		super.additionalInfo();
		System.out.println("It can bark");
	}
}
class Ostrich extends Bird{
	public Ostrich(){
		super();
		isHerbivore=true;
	}
	public void movement(){
		System.out.println("It can run but can't fly");
	}
	public void additionalInfo(){
		super.additionalInfo();
		System.out.println("It is the largest bird");
	}
}
class Cow extends Mammal{
	public Cow(){
		super();
		isHerbivore=true;
	}
	public void movement(){
		System.out.println("It can walk");
	}
	public void additionalInfo(){
		super.additionalInfo();
		System.out.println("It is worshipped by many people in India");
	}
}
class Parrot extends Bird{
	public Parrot(){
		super();
		canFly=true;
		isHerbivore=true;
	}
	public void movement(){
		System.out.println("It will fly");
	}
	public void additionalInfo(){
		super.additionalInfo();
		System.out.println("It is a pet");
	}
}
class AnimalCreation{
	public static void printFly(ArrayList<Animal> animals){
		if(animals.size()==0){
			System.out.println("No animals to print");
		}
		else{
			ArrayList<Animal> animalFly=new ArrayList<Animal>();
			for(int i=0;i<animals.size();i++){
				Animal animal=animals.get(i);
				if(animal.canFly==true){
					animalFly.add(animal);
				}
			}
			printAnimals(animalFly);
		}
	}
	public static void printHerbi(ArrayList<Animal> animals){
		ArrayList<Animal> animalHerbi=new ArrayList<Animal>();
		for(int i=0;i<animals.size();i++){
			Animal animal=animals.get(i);
			if(animal.isHerbivore==true){
				animalHerbi.add(animal);
			}
		}
		printAnimals(animalHerbi);
	}
	public static void printAnimals(ArrayList<Animal> animals){
		if(animals.size()==0){
			System.out.println("No animals to print");
		}
		else{
			for(int i=0;i<animals.size();i++){
				Animal animal=animals.get(i);
				System.out.println("Animal name \t"+animal.name);
				System.out.println("Animal type\t"+animal.type);
				System.out.println("Description");
				animal.movement();
				animal.hasHair();
				animal.additionalInfo();
				System.out.println("\n");
			}
		}
	}			
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		boolean choice;
		ArrayList<Animal> animals=new ArrayList<Animal>();	//Creating the animal list
		do{
			System.out.println("1.Bat \n2.Dog\n3.Cow\n4.Ostrich\n5.Parrot\nPress the number to create that animal");
			int animalChoice=scanner.nextInt();
			Animal animal;
			switch(animalChoice){	//Creating a choice of animal
				case 1:{ animal=new Bat();
					animal.type="Bat";
					break;
				}
				case 2:{ animal=new Dog();
					animal.type="Dog";
					break;
				}
				case 3:{ animal=new Cow();
					animal.type="Cow";
					break;
				}
				case 4:{ animal=new Ostrich();
					animal.type="Ostrich";
					break;
				}
				case 5:{ animal=new Parrot();
					animal.type="Parrot";
					break;
				}
				default: System.out.println("Wrong choice");
					choice=true;
					continue;
			}
			scanner.nextLine();
			System.out.println("Enter the name for your animal");
			animal.name=scanner.nextLine();
			animals.add(animal);
			System.out.println("Do you want to print the animals so far created? If so type true if not type false ");
			boolean printChoice=scanner.nextBoolean();//If we want to add print animals so far created
			if(printChoice==true){
				printAnimals(animals);
			}
			System.out.println("Do you further want to add animals? If so type true and if not type false");
			choice=scanner.nextBoolean();	//If we want to further add animals
		}while(choice==true);
		System.out.println("Do you want to list the animals which can fly");
		choice=scanner.nextBoolean();	//If you want to list the flying animals
		if(choice==true){
			printFly(animals);
		}
		System.out.println("Do you want to list the animals which are herbivores");
		choice=scanner.nextBoolean();	//If we want to list the herbivorous animals
		if(choice==true){
			printHerbi(animals);
		}
	}
} 	
