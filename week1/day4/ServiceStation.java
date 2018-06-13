package oopsjava;
import java.util.Scanner;
import java.util.ArrayList;

public class ServiceStation{
	private String stationName;
	private String stationAddress;
	private Long stationContact;
	private ArrayList<Customer> customer;
	private ArrayList<Employee> employee;
	private ArrayList<Vehicle> vehicle;
	private ArrayList<Invoice> invoice;
	public ServiceStation(){
		customer=new ArrayList<Customer>();
		employee=new ArrayList<Employee>();
		vehicle=new ArrayList<Vehicle>();
		invoice=new ArrayList<Invoice>();
	}
	public ServiceStation(String stationName,String stationAddress,Long stationContact,ArrayList<Customer> customer,ArrayList<Employee> employee,ArrayList<Vehicle> vehicle,ArrayList<Invoice> invoice){
		this.stationName=stationName;
		this.stationAddress=stationAddress;
		this.stationContact=stationContact;
		this.customer=customer;
		this.employee=employee;
		this.vehicle=vehicle;
		this.invoice=invoice;
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
		this.customer.add(customer);
	}
	public void addEmployee(Employee employee){
		this.employee.add(employee);
	}
	public void addVehicle(Vehicle vehicle){
		this.vehicle.add(vehicle);
	}
	public void addInvoice(Invoice invoice){
		this.invoice.add(invoice);
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
	public ArrayList<Vehicle> getVehicle(){
		return vehicle;
	}
	public ArrayList<Employee> getEmployee(){
		return employee;
	}	
	public ArrayList<Customer> getCustomer(){
		return customer;
	}
	public void printVehicleCharges(){
		System.out.println("Type\tBrand\tColor");
		for(int i=0;i<vehicle.size();i++){
			System.out.println(vehicle.get(i).getType()+"\t"+vehicle.get(i).getBrand()+"\t"+vehicle.get(i).getColor()+"\t");
			for(int j=0;j<vehicle.get(i).service.size();j++){
				System.out.println(vehicle.get(i).service.get(j).getServiceName()+"\t\t"+vehicle.get(i).service.get(j).getServiceCharge());
			}
			System.out.println("\n**************************************");
		}
	}
	public void printEmployees(){
		System.out.println("Size : "+employee.size());
		System.out.println("\nEmployee Details");
		for(int i=0;i<employee.size();i++){
			employee.get(i).printDetails();
			System.out.println("\n*************************************");
		}
	}
	public void printCustomerInvoices(){
		System.out.println("\n");
		for(int i=0;i<customer.size();i++){
			customer.get(i).printDetails();
			System.out.println("Invoice details of customer");
			invoice.get(i).printInvoiceDetails();
			System.out.println("**************************************");
		}
	}
	public Boolean checkContactNumber(Long contactNumber){
		if(String.valueOf(contactNumber).length()==10){
			return false;
		}
		else{
			return true;
		}
	}
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		ServiceStation serviceStation=new ServiceStation();			//Creating a service station
		Integer choice;
		System.out.println("Setting up the service station.....\nEnter the service station name");
		serviceStation.setStationName(scanner.nextLine());			//Setting the service station name
		System.out.println("Enter the service station address");
		serviceStation.setStationAddress(scanner.nextLine());		//Setting the service station address
		Long contactNumber;
		do{
			System.out.println("Enter the service station contact(10 digits)");
			contactNumber=scanner.nextLong();	//Setting the service station contact with 10 digit
		}while(serviceStation.checkContactNumber(contactNumber));
		serviceStation.setStationContact(contactNumber);
		scanner.nextLine();
		System.out.println("\n*****Service station created******");
		System.out.println("\n1.Add an employee\n2.Add a customer\n3.Add employee as customer\n4.List the service charges of all vehicles\n5.List all employees\n6.List all customers\n7.Exit");
		choice=scanner.nextInt();
		scanner.nextLine();
		Integer customerFlag=0;
		do{
			switch(choice){
				case 1:
					Employee employee=new Employee();						//Creating a new employee
					System.out.println("Enter the employee name ");
					employee.setName(scanner.nextLine());
					System.out.println("Enter the employee age");
					employee.setAge(scanner.nextInt());
					scanner.nextLine();
					System.out.println("Enter the employee address");
					employee.setAddress(scanner.nextLine());
					do{
						System.out.println("Enter the employee contact(10 digits)");
						contactNumber=scanner.nextLong();
					}while(serviceStation.checkContactNumber(contactNumber));
					employee.setContactNumber(contactNumber);
					System.out.println("Enter the employee id");
					employee.setEmpId(scanner.nextInt());
					serviceStation.addEmployee(employee);						//Adding the employee to the serviceStation
					System.out.println("\n*****Employee added******");
					break;
				case 2:
					if(serviceStation.employee.size()==0){
						System.out.println("\n*****Employee list is empty*****\n*****First add employees******");
						customerFlag=1;
						break;
					}
					Customer customer= new Customer();
					System.out.println("Enter the customer name");
					customer.setName(scanner.nextLine());
					System.out.println("Enter the customer age");
					customer.setAge(scanner.nextInt());
					scanner.nextLine();
					System.out.println("Enter the customer address");
					customer.setAddress(scanner.nextLine());
					do{
						System.out.println("Enter the customer contact(10 digits)");
						contactNumber=scanner.nextLong();
					}while(serviceStation.checkContactNumber(contactNumber));
					customer.setContactNumber(contactNumber);
					System.out.println("Enter the customer id");
					customer.setCustId(scanner.nextInt());
					serviceStation.customer.add(customer);			//Adding the customer to the service station
					System.out.println("\n******Customer added******\n");
					break;
				case 3:
					if(serviceStation.employee.size()==0){
						System.out.println("\n*****Employee list is empty*****\n*****First add employees******");
						customerFlag=1;
						break;
					}
					int flag=0;
					int i;
					customer=new Customer();
					System.out.println("Enter the employee id");
					int empId=scanner.nextInt();
					for(i=0;i<serviceStation.employee.size();i++){
						if(serviceStation.employee.get(i).getEmpId()==empId){
							flag=1;
							break;							
						}
					}
					if(flag==0){
						System.out.println("\n******Employee id does not exist******\n******Try again******");
						continue;
					}
					else{
						customer.setEmployeeAsCustomer(serviceStation.employee.get(i));
					}
					serviceStation.customer.add(customer);		//Adding the customer to the service station
					System.out.println("\n******Employee is added as customer successfully******\n");
					break;
				case 4:
					if(serviceStation.customer.size()>0){
						serviceStation.printVehicleCharges();
						System.out.println("\n****End of Service charges list*****");
					}
					else{
						System.out.println("\n******No customers are added....So, no vehicles to print******");
					}
					break;
				case 5:
					if(serviceStation.employee.size()>0){
						serviceStation.printEmployees();
						System.out.println("\n*****End of employee list*****");
					}
					else{
						System.out.println("\n******Employee list is empty******");
					}
					break;
				case 6:
					if(serviceStation.customer.size()>0){
						serviceStation.printCustomerInvoices();
						System.out.println("\n*****End of invoices list*****");
					}
					else{
						System.out.println("\n******No customers are added....So, no invoices to print******");
					}
					break;
				case 7:
					break;
				default:
					System.out.println("\n******Invalid choice*****");
			}
			if(choice==7){
				break;
			}
			if((choice==2||choice==3)&&customerFlag==0){
				Vehicle vehicle=new Vehicle();
				System.out.println("Enter the vehicle type");
				System.out.println("1.Car\n2.Bike\n3.Bus");
				int vehicleChoice=scanner.nextInt();
				scanner.nextLine();
				switch(vehicleChoice){
					case 1: vehicle= new oopsjava.Cars();
						break;
					case 2: vehicle=new Bike();
						break;
					case 3: vehicle=new Bus();
						break;
				}
				System.out.println("Enter the brand of the vehicle");
				vehicle.setBrand(scanner.nextLine());
				System.out.println("Enter the color of the vehicle");
				vehicle.setColor(scanner.nextLine());
				Service service;
				boolean serviceChoice;
				do{
					service=new Service();
					System.out.println("Enter the service name");
					service.setServiceName(scanner.nextLine());
					System.out.println("Enter the service charge");
					service.setServiceCharge(scanner.nextFloat());
					vehicle.service.add(service);		//Adding the service for the vehicle
					System.out.println("Do you want to add more services to the vehicle? If yes press true if not press false......");
					serviceChoice=scanner.nextBoolean();
					scanner.nextLine();
				}while(serviceChoice==true);
				serviceStation.vehicle.add(vehicle);	//Adding the vehicle
				Invoice invoice=new Invoice();
				invoice.setCustomerName(serviceStation.customer.get((serviceStation.customer.size())-1).getName());
				invoice.setVehicle(vehicle);
				invoice.calculateTotalAmount();
				int empId;
				int flag=0;
				int i;
				do{	
					System.out.println("Enter the id of the assigned employee");
					empId=scanner.nextInt();
					flag=0;
					for(i=0;i<serviceStation.employee.size();i++){
						if(serviceStation.employee.get(i).getEmpId()==empId){
							flag=1;
							break;
						}
					}
					if(flag==0){
						System.out.println("Employee id not found");
					}
				}while(flag==0);
				invoice.setEmployeeAssigned(serviceStation.employee.get(i).getName());
				serviceStation.invoice.add(invoice);		//Adding the invoice to the service station
			}
			customerFlag=0;
			System.out.println("\n1.Add an employee\n2.Add a customer\n3.Add employee as customer\n4.List the service charges of all vehicles\n5.List all employees\n6.List all customers\n7.Exit");
			choice=scanner.nextInt();	
			scanner.nextLine();
		}while(choice!=7);
	}
}
