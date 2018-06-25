package week1.day4.autoshop;

public class Truck extends Car{
	private Integer weight;
	public Truck(){
		super(0,0.0,null);
		this.weight=0;
	}
        @Override
	public Double getSalePrice(){
		if(weight>2000){
			return 0.9*this.getRegularPrice();

		}		
		else{
			return 0.8*getRegularPrice();
		}
	}
        @Override
	public void printCarDetails(){
		System.out.println("Weight\t\t: "+weight);
		super.printCarDetails();
	}
}