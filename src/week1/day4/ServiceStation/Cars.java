package oopsjava;
import java.util.ArrayList;

public class Cars extends Vehicle{
	public Cars(){
		this.setType("Car");
	}
	public Cars(String brand,String color,ArrayList<Service> service){
		super(brand,color,service);
		this.setType("Car");
	}
	public void printDetails(){
		System.out.println("Vehicle type\t: "+this.getType());
		super.printDetails();
	}
}
