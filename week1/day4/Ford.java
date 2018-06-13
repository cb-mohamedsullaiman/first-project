package oopsjava;

public class Ford extends Car{
	private Integer year;
	private Integer manufacturerDiscount;
	public Ford(Integer speed,Double regularPrice,String color,Integer year,Integer manufacturerDiscount){
		super(speed,regularPrice,color);
		this.year=year;
		this.manufacturerDiscount=manufacturerDiscount;
	}
	public Double getSalePrice(){
		Double salePrice=super.getSalePrice();
		return ((1-((double)manufacturerDiscount/100))*salePrice);
	}
	public void printCarDetails(){
		System.out.println("Year\t\t: "+year);
		System.out.println("Manufacturer discount: "+manufacturerDiscount);
		super.printCarDetails();
	}
}