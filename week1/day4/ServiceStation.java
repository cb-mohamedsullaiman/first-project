package oopsjava;
import java.util.Scanner;
import java.util.ArrayList;
class Person{
	private String name;									//Members can be private if we dont want the member to be accessed outside of the class
	private int age;
	private String address;
	private long contactNumber;
	public Person(){										//Constructor with zero arguments	
		name=null;
		age=0;
		address=null;
		contactNumber=0;
	}
	public Person(String name,int age,String address,long contactNumber){	//Constructor with 4 arguments
		this.name=name;
		this.age=age;
		this.address=address;
		this.contactNumber=contactNumber;
	}
	public void setName(String name){						//Method for setting name
		this.name=name;
	}
	public void setAge(int age){							//Method for setting age
		this.age=age;
	}
	public void setAddress(String address){					//Method for setting address
		this.address=address;
	}
	public void setContactNumber(long contactNumber){		//Method for setting contact number
		this.contactNumber=contactNumber;
	}
	public String getName(){								//Method for getting name
		return name;
	}
	public int getAge(){									//Method for getting age
		return age;
	}
	public String getAddress(){								//Method for getting address
		return address;
	}
	public long getContactNumber(){							//Method for getting contact number
		return contactNumber;
	}
	public void printDetails(){								//Method for printing person details
		System.out.println("Name\t\t: "+name);
		System.out.println("Age\t\t: "+age);
		System.out.println("Address\t\t: "+address);
		System.out.println("Contact Number\t: "+contactNumber);
	}
}
class Customer extends Person{						
	int custId;
	String type;
	public Customer(){
		super();
		this.custId=0;
		this.type="Customer";
	}
	public Customer(int custId,String name,int age,String address,long contactNumber){
		super(name,age,address,contactNumber);
		this.custId=custId;
		this.type="Customer";
	}
	public void setCustId(int custId){
		this.custId=custId;
	}
	public int getCustId(){
		return custId;
	}
	public String getCustType(){
		return type;
	}
	public void setEmployeeAsCustomer(Employee emp){
		this.custId=emp.empId;
		this.setName(emp.getName());
		this.setAge(emp.getAge());
		this.setContactNumber(emp.getContactNumber());
		this.setAddress(emp.getAddress());
		this.type="Employee";
	}
	public void printDetails(){
		if(type.equals("Customer")){
			System.out.println("Customer Id \t\t: "+custId);
		}
		else if(type.equals("Employee")){
			System.out.println("Customer(Employee) Id \t: "+custId);
		}
		super.printDetails();
	}
}
class Employee extends Person{
	int empId;
	public Employee(){
		super();
		this.empId=0;
	}
	public Employee(int empId,String name,int age,String address,long contactNumber){
		super(name,age,address,contactNumber);
		this.empId=empId;
	}
	public void setEmpId(int empId){
		this.empId=empId;
	}
	public int getEmpId(){
		return empId;
	}
	public void printDetails(){
		System.out.println("Employee Id\t: "+empId);
		super.printDetails();
	}
}
class Vehicle{
	String brand;
	String color;
	String type;
	ArrayList<Service> service;
	public Vehicle(){
		this.brand=null;
		this.color=null;
		this.service=new ArrayList<Service>();
	}
	public Vehicle(String brand,String color,ArrayList<Service> service){
		this.brand=brand;
		this.color=color;
		this.service=service;
	}
	public void setBrand(String brand){
		this.brand=brand;
	}
	public void setColor(String color){
		this.color=color;
	}
	public void addService(Service service){
		this.service.add(service);
	}
	public String getBrand(){
		return brand;
	}
	public String getColor(){
		return color;
	}
	public ArrayList<Service> getServices(){
		return service;
	}
	public void printDetails(){
		System.out.println("Brand \t\t\t: "+brand);
		System.out.println("Color \t\t\t: "+color);
		System.out.println("Service details:");
		for(int i=0;i<service.size();i++){
			service.get(i).printServiceDetails();
		}
	}
}
 class Service{
	String serviceName;
	float serviceCharge;
	public Service(){
		serviceName=null;
		serviceCharge=(float)0.0;
	}

	public Service(String serviceName,float serviceCharge){
		this.serviceName=serviceName;
		this.serviceCharge=serviceCharge;
	}
	public void setServiceName(String serviceName){
		this.serviceName=serviceName;
	}
	public void setServiceCharge(float serviceCharge){
		this.serviceCharge=serviceCharge;	
	}
	public void printServiceDetails(){
		System.out.println(serviceName+"\t\t\t"+serviceCharge);
	}
}
class Car extends Vehicle{
	public Car(){
		super();
		this.type="Car";
	}
	public Car(String brand,String color,ArrayList<Service> service){
		super(brand,color,service);
		this.type="Car";
	}
	public void printDetails(){
		System.out.println("Vehicle type\t: "+type);
		super.printDetails();
	}
}
class Bike extends Vehicle{
	public Bike(){
		super();
		this.type="Bike";
	}
	public Bike(String brand,String color,ArrayList<Service> service){
		super(brand,color,service);
		this.type="Bike";
	}
	public void printDetails(){
		System.out.println("Vehicle type\t\t: "+type);
		super.printDetails();
	}
}
class Bus extends Vehicle{
	public Bus(){
		super();
		this.type="Bus";
	}
	public Bus(String brand,String color,ArrayList<Service> service){
		super(brand,color,service);
		this.type="Bus";
	}
	public void printDetails(){
		System.out.println("Vehicle type\t:"+type);
		super.printDetails();
	}
}
class Invoice{
	String customerName;
	Vehicle vehicle;
	float amountTotal;
	String employeeAssigned;
	public Invoice(){
		customerName=null;
		vehicle=null;
		amountTotal=(float)0.0;
		employeeAssigned=null;
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
			amountTotal=amountTotal+vehicle.service.get(i).serviceCharge;
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
	public float getTotalAmount(){
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
class ServiceStation{
	private String stationName;
	private String stationAddress;
	private long stationContact;
	private ArrayList<Customer> customer;
	private ArrayList<Employee> employee;
	private ArrayList<Vehicle> vehicle;
	private ArrayList<Invoice> invoice;
	public ServiceStation(){
		stationName=null;
		stationAddress=null;
		stationContact=0;
		customer=new ArrayList<Customer>();
		employee=new ArrayList<Employee>();
		vehicle=new ArrayList<Vehicle>();
		invoice=new ArrayList<Invoice>();
	}
	public ServiceStation(String stationName,String stationAddress,long stationContact,ArrayList<Customer> customer,ArrayList<Employee> employee,ArrayList<Vehicle> vehicle,ArrayList<Invoice> invoice){
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
	public void setStationContact(long stationContact){
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
	public long getStationContact(){
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
			System.out.println(vehicle.get(i).type+"\t"+vehicle.get(i).brand+"\t"+vehicle.get(i).color+"\t");
			for(int j=0;j<vehicle.get(i).service.size();j++){
				System.out.println(vehicle.get(i).service.get(j).serviceName+"\t\t"+vehicle.get(i).service.get(j).serviceCharge);
			}
		}
	}
	public void printEmployees(){
		System.out.println("Size : "+employee.size());
		System.out.println("\nEmployee Details");
		for(int i=0;i<employee.size();i++){
			employee.get(i).printDetails();
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
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		ServiceStation serviceStation=new ServiceStation();			//Creating a service station
		boolean choice;
		System.out.println("Enter the service station name");
		serviceStation.setStationName(scanner.nextLine());			//Setting the service station name
		System.out.println("Enter the service station address");
		serviceStation.setStationAddress(scanner.nextLine());		//Setting the service station address
		do{
			System.out.println("Enter the service station contact(10 digits)");
			serviceStation.setStationContact(scanner.nextLong());		//Setting the service station contact with 10 digit
		}while(String.valueOf(serviceStation.stationContact).length()!=10);
		scanner.nextLine();
		do{
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
				employee.setContactNumber(scanner.nextLong());
			}while(String.valueOf(employee.getContactNumber()).length()!=10);
			System.out.println("Enter the employee id");
			employee.setEmpId(scanner.nextInt());
			serviceStation.addEmployee(employee);						//Adding the employee to the serviceStation
			System.out.println("Do you want to add more employees? If yes type true if not type false");
			choice=scanner.nextBoolean();
			scanner.nextLine();
		}while(choice==true);
		System.out.println("\n");
		System.out.println("Customer details entry");
		do{
			System.out.println("Is he an employee of this service station? If yes type true and if not type false");
			choice=scanner.nextBoolean();
			scanner.nextLine();
			Customer customer= new Customer();
			if(choice==false){							//This will execute if the customer is not an employee
				System.out.println("Enter the customer name");
				customer.setName(scanner.nextLine());
				System.out.println("Enter the customer age");
				customer.setAge(scanner.nextInt());
				scanner.nextLine();
				System.out.println("Enter the customer address");
				customer.setAddress(scanner.nextLine());
				do{
					System.out.println("Enter the customer contact(10 digits)");
					customer.setContactNumber(scanner.nextLong());
				}while(String.valueOf(customer.getContactNumber()).length()!=10);
				System.out.println("Enter the customer id");
				customer.setCustId(scanner.nextInt());
			}
			else{										//This will execute if the customer is an employee of the service station
				int flag=0;
				int i;
				do{
					
					System.out.println("Enter the employee id");
					int empId=scanner.nextInt();
					
					for(i=0;i<serviceStation.employee.size();i++){
						if(serviceStation.employee.get(i).empId==empId){
							flag=1;
							break;
						}
					}
					if(flag==0){
						System.out.println("Employee id does not exist");
						continue;
					}
					else{
						customer.setEmployeeAsCustomer(serviceStation.employee.get(i));
					}
					
				}while(flag==0);
			}
			serviceStation.customer.add(customer);			//Adding the customer to the service station
			Vehicle vehicle=new Vehicle();
			System.out.println("Enter the vehicle type");
			System.out.println("1.Car\n2.Bike\n3.Bus");
			int vehicleChoice=scanner.nextInt();
			scanner.nextLine();
			switch(vehicleChoice){
				case 1: vehicle= new Car();
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
			do{
				service=new Service();
				System.out.println("Enter the service name");
				service.setServiceName(scanner.nextLine());
				System.out.println("Enter the service charge");
				service.setServiceCharge(scanner.nextFloat());
				vehicle.service.add(service);		//Adding the service for the vehicle
				System.out.println("Do you want to add more services to the vehicle? If yes press true if not press false");
				choice=scanner.nextBoolean();
				scanner.nextLine();
			}while(choice==true);
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
					if(serviceStation.employee.get(i).empId==empId){
						flag=1;
						break;
					}
				}
				if(flag==0){
					System.out.println("Employee id not found");
				}
			}while(flag==0);
			invoice.employeeAssigned=serviceStation.employee.get(i).getName();
			serviceStation.invoice.add(invoice);		//Adding the invoice to the service station
			System.out.println("Do you want to add more customers? If yes type true and if not type false");
			choice=scanner.nextBoolean();		//Getting the choice of user whether he is adding more customers or not
		}while(choice==true);
		serviceStation.printVehicleCharges();
		serviceStation.printEmployees();
		serviceStation.printCustomerInvoices();
	}
}
