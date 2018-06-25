package week1.day4.servicestation;
import java.util.ArrayList;

public class ServiceStation{
	private String stationName;
	private String stationAddress;
	private Long stationContact;
	private ArrayList<Customer> customers;
	private ArrayList<Employee> employees;
	private ArrayList<Vehicle> vehicles;
	private ArrayList<Invoice> invoices;
	public ServiceStation(){
		customers=new ArrayList<>();
		employees=new ArrayList<>();
		vehicles=new ArrayList<>();
		invoices=new ArrayList<>();
	}
	public ServiceStation(String stationName,String stationAddress,Long stationContact,ArrayList<Customer> customers,ArrayList<Employee> employees,ArrayList<Vehicle> vehicles,ArrayList<Invoice> invoices){
		this.stationName=stationName;
		this.stationAddress=stationAddress;
		this.stationContact=stationContact;
		this.customers=customers;
		this.employees=employees;
		this.vehicles=vehicles;
		this.invoices=invoices;
	}
	public void setStationName(String stationName){
		this.stationName=stationName;
	}
	public void setStationAddress(String stationAddress){
		this.stationAddress=stationAddress;
	}
	public void setStationContact(Long stationContact){
		this.stationContact=stationContact;
	}
	public void addCustomer(Customer customer){
		customers.add(customer);
	}
	public void addEmployee(Employee employee){
		employees.add(employee);
	}
	public void addVehicle(Vehicle vehicle){
		vehicles.add(vehicle);
	}
	public void addInvoice(Invoice invoice){
		invoices.add(invoice);
	}
	public ArrayList<Vehicle> getVehicles(){
		return vehicles;
	}
	public ArrayList<Employee> getEmployees(){
		return employees;
	}	
	public ArrayList<Customer> getCustomers(){
		return customers;
	}
	public ArrayList<Invoice> getInvoices(){
		return invoices;
	}
	public String getStationName(){
		return stationName;
	}
	public String getStationAddress(){
		return stationAddress;
	}
	public Long getStationContact(){
		return stationContact;
	}	
}
