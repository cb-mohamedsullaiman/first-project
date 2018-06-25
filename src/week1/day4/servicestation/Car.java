package week1.day4.servicestation;
import java.util.ArrayList;

public class Car extends Vehicle{
	public Car(){
		this.setType("Car");
	}
	public Car(String brand,String color,ArrayList<Service> service){
		super(brand,color,service);
		this.setType("Car");
	}
        @Override
	public void printDetails(){
		System.out.println("Vehicle type\t: "+this.getType());
		super.printDetails();
	}
}
