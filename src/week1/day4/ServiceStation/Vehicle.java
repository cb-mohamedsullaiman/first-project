package oopsjava;
import java.util.ArrayList;

public class Vehicle{
	private String brand;
	private String color;
	private String type;
	ArrayList<Service> services;
	public Vehicle(){
		this.services=new ArrayList<Service>();
	}
	public Vehicle(String brand,String color,ArrayList<Service> service){
		this.brand=brand;
		this.color=color;
		this.services=services;
	}
	public void setBrand(String brand){
		this.brand=brand;
	}
	public void setColor(String color){
		this.color=color;
	}
	public void setType(String type){
		this.type=type;
	}
	public void addService(Service service){
		services.add(service);
	}
	public String getBrand(){
		return brand;
	}
	public String getColor(){
		return color;
	}
	public String getType(){
		return type;
	}
	public ArrayList<Service> getServices(){
		return services;
	}
	public void printDetails(){
		System.out.println("Brand \t\t\t: "+brand);
		System.out.println("Color \t\t\t: "+color);
		System.out.println("Service details:");
		for(int i=0;i<services.size();i++){
			services.get(i).printServiceDetails();
		}
	}
}
