package oopsjava;

public class Car{
	private Integer speed;
	private Double regularPrice;
	private String color;
	public Car(Integer speed,Double regularPrice,String color){
		this.speed=speed;
		this.regularPrice=regularPrice;
		this.color=color;
	}
	public Double getRegularPrice(){
		return regularPrice;
	}
	public Double getSalePrice(){
		return 0.95*this.getRegularPrice();
	}
	public void printCarDetails(){
		System.out.println("Speed \t\t: "+speed);
		System.out.println("Color\t\t: "+color);
		System.out.println("Regular Price\t: "+regularPrice);
	}
}