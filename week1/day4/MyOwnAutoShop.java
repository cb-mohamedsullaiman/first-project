package oopsjava;
import java.util.Scanner;
class Car{
	int speed;
	double regularPrice;
	String color;
	public Car(int speed,double regularPrice,String color){
		this.speed=speed;
		this.regularPrice=regularPrice;
		this.color=color;
	}
	public double getSalePrice(){
		return 0.95*regularPrice;
	}
}
class Truck extends Car{
	int weight;
	public Truck(){
		super(0,0.0,null);
		this.weight=0;
	}
	public double getSalePrice(){
		if(weight>2000){
			return 0.9*regularPrice;

		}		
		else{
			return 0.8*regularPrice;
		}
	}
}
class Ford extends Car{
	int year;
	int manufacturerDiscount;
	public Ford(int speed,double regularPrice,String color,int year,int manufacturerDiscount){
		super(speed,regularPrice,color);
		this.year=year;
		this.manufacturerDiscount=manufacturerDiscount;
	}
	public double getSalePrice(){
		double salePrice=super.getSalePrice();
		return ((1-((double)manufacturerDiscount/100))*salePrice);
	}
}
class Sedan extends Car{
	int length;
	public Sedan(int speed,double regularPrice,String color,int length){
		super(speed,regularPrice,color);
		this.length=length;
	}
	public double getSalePrice(){
		if(length>20){
			return 0.95*regularPrice;
		}
		else{
			return 0.9*regularPrice;
		}
	}
}
class MyOwnAutoShop{
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
		System.out.println("Enter the Manufacturer Discount");
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
		System.out.println("Enter the manufacturer discount");
		manufacturerDiscount=scanner.nextInt();
		Ford ford2=new Ford(speed,regularPrice,color,year,manufacturerDiscount);	//Creating second ford
		System.out.println("Enter the speed of car");
		speed=scanner.nextInt();
		System.out.println("Enter the regular price");
		regularPrice=scanner.nextDouble();
		System.out.println("Enter the color of car");
		color=scanner.nextLine();
		Car car=new Car(speed,regularPrice,color);				//Creating a car
		System.out.println("The Sale price of sedan is "+sedan.getSalePrice());	//Printing the saleprices of all
		System.out.println("The Sale price of ford1 is "+ford1.getSalePrice());
		System.out.println("The Sale price of ford2 is "+ford2.getSalePrice());
		System.out.println("The Sale price of car is "+car.getSalePrice());
	}
}




