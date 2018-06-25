package week1.day4.servicestation;
import java.util.ArrayList;

public class Bus extends Vehicle{
	public Bus(){
		this.setType("Bus");
	}
	public Bus(String brand,String color,ArrayList<Service> service){
		super(brand,color,service);
		this.setType("Bus");
	}
        @Override
	public void printDetails(){
		System.out.println("Vehicle type\t:"+this.getType());
		super.printDetails();
	}
}