package oopsjava;
import java.util.ArrayList;

public class Bike extends oopsjava.Vehicle{
	public Bike(){
		super();
		this.setType("Bike");
	}
	public Bike(String brand,String color,ArrayList<Service> service){
		super(brand,color,service);
		this.setType("Bike");
	}
	public void printDetails(){
		System.out.println("Vehicle type\t\t: "+this.getType());
		super.printDetails();
	}
}