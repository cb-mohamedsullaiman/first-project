package week1.day4.autoshop;
import java.util.Scanner;

public class MyOwnAutoShop{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the speed of sedan");
		int speed=scanner.nextInt();
		System.out.println("Enter the regular price");
		Double regularPrice=scanner.nextDouble();
		System.out.println("Enter the color for sedan");
		scanner.nextLine();
		String color=scanner.nextLine();
		System.out.println("Enter the length of the sedan");
		int length=scanner.nextInt();
		Sedan sedan=new Sedan(speed,regularPrice,color,length);	//Creating a sedan 
		System.out.println("Enter the speed of ford car 1");
		speed=scanner.nextInt();
		System.out.println("Enter the regular price");
		regularPrice=scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter the color of ford 1");
		color=scanner.nextLine();
		System.out.println("Enter the year");
		int year=scanner.nextInt();
		System.out.println("Enter the Manufacturer discount percentage");
		int manufacturerDiscount=scanner.nextInt();
		Ford ford1=new Ford(speed,regularPrice,color,year,manufacturerDiscount);	//Creating first ford
		System.out.println("Enter the speed of ford car 2");
		speed=scanner.nextInt();
		System.out.println("Enter the regular price");
		regularPrice=scanner.nextDouble();
		System.out.println("Enter the color of ford 2");
		scanner.nextLine();
		color=scanner.nextLine();
		System.out.println("Enter the year");
		year=scanner.nextInt();
		System.out.println("Enter the manufacturer discount percentage");
		manufacturerDiscount=scanner.nextInt();
		Ford ford2=new Ford(speed,regularPrice,color,year,manufacturerDiscount);	//Creating second ford
		System.out.println("Enter the speed of car");
		speed=scanner.nextInt();
		System.out.println("Enter the regular price");
		regularPrice=scanner.nextDouble();
		scanner.nextLine();
		System.out.println("Enter the color of car");
		color=scanner.nextLine();
		Car car=new Car(speed,regularPrice,color);				//Creating a car
		sedan.printCarDetails();
		System.out.println("Sale Price: "+sedan.getSalePrice());
		ford1.printCarDetails();
		System.out.println("Sale Price: "+ford1.getSalePrice());
		ford2.printCarDetails();
		System.out.println("Sale Price: "+ford2.getSalePrice());
		car.printCarDetails();
		System.out.println("Sale Price: "+car.getSalePrice());
	}
}




