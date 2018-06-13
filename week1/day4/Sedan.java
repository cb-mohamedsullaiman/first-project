package oopsjava;

public class Sedan extends Car{
	private Integer length;
	public Sedan(Integer speed,Double regularPrice,String color,Integer length){
		super(speed,regularPrice,color);
		this.length=length;
	}
	public Double getSalePrice(){
		if(length>20){
			return 0.95*this.getRegularPrice();
		}
		else{
			return 0.9*this.getRegularPrice();
		}
	}
	public void printCarDetails(){
		System.out.println("Length\t\t: "+length);
		super.printCarDetails();
	}
}