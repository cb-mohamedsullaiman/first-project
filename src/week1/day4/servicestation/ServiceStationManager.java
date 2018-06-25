package week1.day4.servicestation;
import java.util.Scanner;

public class ServiceStationManager{
	public static Boolean checkContactNumberLength(Long contactNumber){
		return !(String.valueOf(contactNumber).length()==10);
	}
	public static void printVehicleCharges(ServiceStation serviceStation){
		System.out.println("Type\tBrand\tColor");
		for(int i=0;i<serviceStation.getVehicles().size();i++){
			System.out.println(serviceStation.getVehicles().get(i).getType()+"\t"+serviceStation.getVehicles().get(i).getBrand()+"\t"+serviceStation.getVehicles().get(i).getColor()+"\t");
			for(int j=0;j<serviceStation.getVehicles().get(i).getServices().size();j++){
				System.out.println(serviceStation.getVehicles().get(i).getServices().get(j).getServiceName()+"\t\t"+serviceStation.getVehicles().get(i).getServices().get(j).getServiceCharge());
			}
			System.out.println("\n**************************************");
		}
	}
	public static void printEmployees(ServiceStation serviceStation){
		System.out.println("Size : "+serviceStation.getEmployees().size());
		System.out.println("\nEmployee Details");
		for(int i=0;i<serviceStation.getEmployees().size();i++){
			serviceStation.getEmployees().get(i).printDetails();
			System.out.println("\n*************************************");
		}
	}
	public static void printCustomerInvoices(ServiceStation serviceStation){
		System.out.println("\n");
		for(int i=0;i<serviceStation.getCustomers().size();i++){
			serviceStation.getCustomers().get(i).printDetails();
			System.out.println("Invoice details of customer");
			serviceStation.getInvoices().get(i).printInvoiceDetails();
			System.out.println("**************************************");
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
		}while(checkContactNumberLength(contactNumber));
		serviceStation.setStationContact(contactNumber);
		scanner.nextLine();
		System.out.println("\n*****Service station created******");
		System.out.println("\n1.Add an employee\n2.Add a customer\n3.Add employee as customer\n4.List the service charges of all vehicles\n5.List all employees\n6.List all customers\n7.Exit");
		choice=scanner.nextInt();
		scanner.nextLine();
		Integer customerFlag=0;
		do{
			switch(choice){
				case 1:														//To add an employee
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
					}while(checkContactNumberLength(contactNumber));		//This will execute until we give 10 digit phoneNumber
					employee.setContactNumber(contactNumber);
					System.out.println("Enter the employee id");
					employee.setEmployeeId(scanner.nextInt());
					serviceStation.addEmployee(employee);						//Adding the employee to the serviceStation
					System.out.println("\n*****Employee added******");
					break;
				case 2:														//To add a customer
					if(serviceStation.getEmployees().isEmpty()){
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
					}while(checkContactNumberLength(contactNumber));	//Checking the length of contact number
					customer.setContactNumber(contactNumber);
					System.out.println("Enter the customer id");
					customer.setCustomerId(scanner.nextInt());
					serviceStation.addCustomer(customer);			//Adding the customer to the service station
					System.out.println("\n******Customer added******\n");
					break;
				case 3:														//To add an employee as customer
					if(serviceStation.getEmployees().isEmpty()){
						System.out.println("\n*****Employee list is empty*****\n*****First add employees******");
						customerFlag=1;
						break;
					}
					int flag=0;
					int i;
					customer=new Customer();
					System.out.println("Enter the employee id");
					int empId=scanner.nextInt();
					for(i=0;i<(serviceStation.getEmployees()).size();i++){
						if(serviceStation.getEmployees().get(i).getEmployeeId()==empId){		
							flag=1;										//Employee found
							break;							
						}
					}
					if(flag==0){
						System.out.println("\n******Employee id does not exist******\n******Try again******");
						continue;
					}
					else{
						customer.setEmployeeAsCustomer(serviceStation.getEmployees().get(i));
					}
					serviceStation.addCustomer(customer);		//Adding the customer to the service station
					System.out.println("\n******Employee is added as customer successfully******\n");
					break;
				case 4:										//To  print  vehicle charges
					if(serviceStation.getCustomers().size()>0){
						printVehicleCharges(serviceStation);
						System.out.println("\n****End of Service charges list*****");
					}
					else{
						System.out.println("\n******No customers are added....So, no vehicles to print******");
					}
					break;
				case 5:										//To print employees
					if(serviceStation.getEmployees().size()>0){
						printEmployees(serviceStation);
						System.out.println("\n*****End of employee list*****");
					}
					else{
						System.out.println("\n******Employee list is empty******");
					}
					break;
				case 6:										//To print the invoices
					if(serviceStation.getCustomers().size()>0){
						printCustomerInvoices(serviceStation);
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
			if((choice==2||choice==3)&&customerFlag==0){	//customer flag is set when we try to add customer without employees
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
				boolean serviceChoice;
				do{
					service=new Service();
					System.out.println("Enter the service name");
					service.setServiceName(scanner.nextLine());
					System.out.println("Enter the service charge");
					service.setServiceCharge(scanner.nextFloat());
					vehicle.addService(service);				//Adding the service for the vehicle
					System.out.println("Do you want to add more services to the vehicle? If yes press true if not press false......");
					serviceChoice=scanner.nextBoolean();
					scanner.nextLine();
				}while(serviceChoice==true);
				serviceStation.addVehicle(vehicle);					//Adding the vehicle
				Invoice invoice=new Invoice();
				invoice.setCustomerName(serviceStation.getCustomers().get((serviceStation.getCustomers().size())-1).getName());
				invoice.setVehicle(vehicle);
				invoice.calculateTotalCharge();
				int employeeId;
				int flag=0;
				int i;
				do{	
					System.out.println("Enter the id of the assigned employee");
					employeeId=scanner.nextInt();
					flag=0;
					for(i=0;i<serviceStation.getEmployees().size();i++){
						if(serviceStation.getEmployees().get(i).getEmployeeId()==employeeId){
							flag=1;						//Checking whether the employee id exists or not
							break;
						}
					}
					if(flag==0){
						System.out.println("Employee id not found");
					}
				}while(flag==0);
				invoice.assignEmployee(serviceStation.getEmployees().get(i).getName());
				serviceStation.addInvoice(invoice);				//Adding the invoice to the service station
			}
			customerFlag=0;
			System.out.println("\n1.Add an employee\n2.Add a customer\n3.Add employee as customer\n4.List the service charges of all vehicles\n5.List all employees\n6.List all customers\n7.Exit");
			choice=scanner.nextInt();	
			scanner.nextLine();
		}while(choice!=7);
	}
}