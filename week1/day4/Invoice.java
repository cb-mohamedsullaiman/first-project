package oopsjava;

public class Invoice{
	private String customerName;
	private Vehicle vehicle;
	private Float amountTotal;
	private String employeeAssigned;
	public Invoice(){
		amountTotal=(float)0.0;
	}
	public Invoice(String customerName,Vehicle vehicle,float amountTotal,String employeeAssigned){
		this.customerName=customerName;
		this.vehicle=vehicle;
		this.amountTotal=amountTotal;
		this.employeeAssigned=employeeAssigned;
	}
	public void setCustomerName(String customerName){
		this.customerName=customerName;
	}
	public void setVehicle(Vehicle vehicle){
		this.vehicle=vehicle;
	}
	public void calculateTotalAmount(){
		for(int i=0;i<vehicle.service.size();i++){
			amountTotal=amountTotal+vehicle.service.get(i).getServiceCharge();
		}
	}
	public void setEmployeeAssigned(String employeeAssigned){
		this.employeeAssigned=employeeAssigned;
	}
	public String getCustomerName(){
		return customerName;
	}
	public Vehicle getVehicle(){
		return vehicle;
	}
	public Float getTotalAmount(){
		return amountTotal;
	}
	public String getEmployeeAssigned(){
		return employeeAssigned;
	}
	public void printInvoiceDetails(){
		System.out.println("Name of the owner\t: "+customerName);
		System.out.println("Vehicle details : ");
		vehicle.printDetails();
		System.out.println("Total amount\t\t: "+amountTotal);
		System.out.println("Employee assigned\t: "+employeeAssigned);
	}
}