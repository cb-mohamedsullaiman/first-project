package week1.day4.servicestation;
import java.util.ArrayList;

public class Bike extends week1.day4.servicestation.Vehicle{
	public Bike(){
		this.setType("Bike");
	}
	public Bike(String brand,String color,ArrayList<Service> service){
		super(brand,color,service);
		this.setType("Bike");
	}
        @Override
	public void printDetails(){
		System.out.println("Vehicle type\t\t: "+this.getType());
		super.printDetails();
	}
}