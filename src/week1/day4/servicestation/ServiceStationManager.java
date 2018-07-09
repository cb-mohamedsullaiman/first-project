package week1.day4.servicestation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ServiceStationManager {
    
    public static Set<Integer> employeeIdSet = new HashSet<>();
    public static Set<Integer> customerIdSet = new HashSet<>();

    public static Boolean checkContactNumberLength(Long contactNumber) {
        return !(String.valueOf(contactNumber).length() >= 10);
    }

    public static void printVehicleCharges(ServiceStation serviceStation) {
        ArrayList<Vehicle> vehicles = serviceStation.getVehicles();
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            System.out.println("Type\t:"+vehicle.getType() + "\nBrand\t:" + vehicle.getBrand() + "\nColor\t:" + vehicle.getColor());
            ArrayList<Service> services = vehicle.getServices();
            System.out.println("Service Name\t\tService Charges");
            System.out.println("_________________________________________________");
            for (int j = 0; j < services.size(); j++) {
                Service service = services.get(j);
                System.out.println(service.getServiceName() + "\t\t\t" + service.getServiceCharge());
            }
            System.out.println("\n**************************************");
        }
    }

    public static void printEmployees(ServiceStation serviceStation) {
        ArrayList<Employee> employees = serviceStation.getEmployees();
        System.out.println("\nEmployee Details");
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).printDetails();
            System.out.println("\n*************************************");
        }
    }

    public static void printCustomerInvoices(ServiceStation serviceStation) {
        System.out.println("\n");
        ArrayList<Customer> customers = serviceStation.getCustomers();
        ArrayList<Invoice> invoices = serviceStation.getInvoices();
        for (int i = 0; i < customers.size(); i++) {
            customers.get(i).printDetails();
            System.out.println("Invoice details of customer");
            invoices.get(i).printInvoiceDetails();
            System.out.println("**************************************");
        }
    }
    
    public static void main(String args[])throws IOException {
        Scanner scanner = new Scanner(System.in);
        ServiceStation serviceStation = new ServiceStation();			//Creating a service station
        Integer choice;
        System.out.println("Setting up the service station.....\nEnter the service station name");
        String stationName = scanner.nextLine();
        while(stationName.isEmpty()){
            stationName = scanner.nextLine();
        } 
        serviceStation.setStationName(stationName);			//Setting the service station name
        System.out.println("Enter the service station address");
        String stationAddress = scanner.nextLine();
        while(stationAddress.isEmpty()){
            stationAddress = scanner.nextLine();
        }
        serviceStation.setStationAddress(stationAddress);		//Setting the service station address
        Long contactNumber;
        do {
            System.out.println("Enter the service station contact(must have atleast 10 digits)");
            contactNumber = scanner.nextLong();	//Setting the service station contact with 10 digit
        } while (checkContactNumberLength(contactNumber));
        serviceStation.setStationContact(contactNumber);
        scanner.nextLine();
        System.out.println("\n*****Service station created******");
        System.out.println("\n1.Add an employee\n2.Add a customer\n3.Add employee as customer\n4.List the service charges of all vehicles\n5.List all employees\n6.List all customers\n7.Exit");
        choice = scanner.nextInt();
        scanner.nextLine();
        do {
            switch (choice) {
                case 1:														//To add an employee
                    Employee employee = new Employee();						//Creating a new employee
                    System.out.println("Enter the employee name ");
                    String employeeName = scanner.nextLine();
                    while(employeeName.isEmpty()){
            employeeName = scanner.nextLine();
        }
                    employee.setName(employeeName);
                    System.out.println("Enter the employee age");
                    employee.setAge(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Enter the employee address");
                    String employeeAddress = scanner.nextLine();
                     while(employeeAddress.isEmpty()){
            employeeAddress = scanner.nextLine();
        }
                    employee.setAddress(employeeAddress);
                    do {
                        System.out.println("Enter the employee contact(must have atleast 10 digits)");
                        contactNumber = scanner.nextLong();
                    } while (checkContactNumberLength(contactNumber));		//This will execute until we give 10 digit phoneNumber
                    employee.setContactNumber(contactNumber);
                    System.out.println("Enter the employee id");
                    Integer employeeId = scanner.nextInt();
                    while(!employeeIdSet.add(employeeId)){
                        System.out.println("The employee Id already exists.. Please try with another..");
                        employeeId = scanner.nextInt();
                    }
                    employee.setEmployeeId(employeeId);
                    serviceStation.addEmployee(employee);						//Adding the employee to the serviceStation
                    System.out.println("\n*****Employee added******");
                    break;
                case 2:														//To add a customer
                    if (employeeIdSet.isEmpty()) {
                        System.out.println("\n*****Employee list is empty*****\n*****First add employees******");
                        break;
                    }
                    Customer customer = new Customer();
                    System.out.println("Enter the customer name");
                    String customerName = scanner.nextLine();
                    while(customerName.isEmpty()){
                        customerName = scanner.nextLine();
                    }
                    customer.setName(customerName);
                    System.out.println("Enter the customer age");
                    customer.setAge(scanner.nextInt());
                    scanner.nextLine();
                    System.out.println("Enter the customer address");
                    String customerAddress = scanner.nextLine();
                    while(customerAddress.isEmpty()){
                        customerAddress = scanner.nextLine();
                    }
                    customer.setAddress(customerAddress);
                    do {
                        System.out.println("Enter the customer contact(must have atleast 10 digits)");
                        contactNumber = scanner.nextLong();
                    } while (checkContactNumberLength(contactNumber));	//Checking the length of contact number
                    customer.setContactNumber(contactNumber);
                    System.out.println("Enter the customer id");
                    Integer customerId = scanner.nextInt();
                    while(!customerIdSet.add(customerId)){
                        System.out.println("The customer Id already exists.. Please try with another..");
                        customerId = scanner.nextInt();
                    }
                    customer.setCustomerId(customerId);
                    serviceStation.addCustomer(customer);			//Adding the customer to the service station
                    System.out.println("\n******Customer added******\n");
                    break;
                case 3:														//To add an employee as customer
                    if (employeeIdSet.isEmpty()) {
                        System.out.println("\n*****Employee list is empty*****\n*****First add employees******");
                        break;
                    }
           
                    customer = new Customer();
                    System.out.println("Enter the employee id");
                    employeeId = scanner.nextInt();
                    while(!employeeIdSet.contains(employeeId)){
                       System.out.println("No employees found with this id..try again");
                       employeeId=scanner.nextInt();
                    }
                    
                    customerId = employeeId;
                    while(!customerIdSet.add(customerId)){
                        System.out.println("This employee id already exists in the customer id set..We cannot assign this employee id as customer id .. so please assign the customer id manually..");
                        customerId = scanner.nextInt();
                    }
                    int i;
                    for ( i = 0; i < (serviceStation.getEmployees()).size(); i++) {
                        if (serviceStation.getEmployees().get(i).getEmployeeId().equals(employeeId)) {
                            break;
                        }
                    }
                    
                    customer.setEmployeeAsCustomer(serviceStation.getEmployees().get(i),customerId);
                    
                    serviceStation.addCustomer(customer);		//Adding the customer to the service station
                    System.out.println("\n******Employee is added as customer successfully******\n");
                    break;
                case 4:										//To  print  vehicle charges
                    if(customerIdSet.isEmpty()){
                        System.out.println("\n******No customers are added...******");
                        break;
                    }
                        printVehicleCharges(serviceStation);
                        System.out.println("\n****End of Service charges list*****");
                    break;
                case 5:										//To print employees
                    if(employeeIdSet.isEmpty()){
                        System.out.println("\n******Employee list is empty******");
                        break;
                    }
                        printEmployees(serviceStation);
                        System.out.println("\n*****End of employee list*****");
                    break;
                case 6:										//To print the invoices
                    if(customerIdSet.isEmpty()){
                        System.out.println("\n******No customers are added....So, no invoices to print******");
                    }
                        printCustomerInvoices(serviceStation);
                        System.out.println("\n*****End of invoices list*****");
                    break;
                case 7:
                    break;
                default:
                    System.out.println("\n******Invalid choice*****");
            }
            if (choice == 7) {
                break;
            }
            if ((choice == 2 || choice == 3) && !employeeIdSet.isEmpty()) {	//This code should be executed when a customer is added... so 2 and 3 are the cases where customers are added
                Vehicle vehicle = new Vehicle();
                System.out.println("Enter the vehicle type");
                System.out.println("1.Car\n2.Bike\n3.Bus");
                int vehicleChoice = scanner.nextInt();
                scanner.nextLine();
                switch (vehicleChoice) {
                    case 1:
                        vehicle = new Car();
                        break;
                    case 2:
                        vehicle = new Bike();
                        break;
                    case 3:
                        vehicle = new Bus();
                        break;
                }
                System.out.println("Enter the brand of the vehicle");
                vehicle.setBrand(scanner.nextLine());
                System.out.println("Enter the color of the vehicle");
                vehicle.setColor(scanner.nextLine());
                Service service;
                String serviceChoice;
                do {
                    service = new Service();
                    System.out.println("Enter the service name");
                    service.setServiceName(scanner.nextLine());
                    System.out.println("Enter the service charge");
                    service.setServiceCharge(scanner.nextFloat());
                    vehicle.addService(service);				//Adding the service for the vehicle
                    System.out.println("Do you want to add more services to the vehicle? (yes/no)");
                    serviceChoice = scanner.next();
                    scanner.nextLine();
                } while (serviceChoice.equalsIgnoreCase("yes"));
                serviceStation.addVehicle(vehicle);					//Adding the vehicle
                Invoice invoice = new Invoice();
                invoice.setCustomerName(serviceStation.getCustomers().get((serviceStation.getCustomers().size()) - 1).getName());
                invoice.setVehicle(vehicle);
                invoice.calculateTotalCharge();
                System.out.println("IDs of available employees:");
                Iterator employeeIdIterator = employeeIdSet.iterator();
                while(employeeIdIterator.hasNext()){
                    System.out.println(employeeIdIterator.next());
                }
                Integer employeeId;
                int i;
                System.out.println("Enter the employee id to be assigned from the above list");
                employeeId = scanner.nextInt();
                while(!employeeIdSet.contains(employeeId)){
                    System.out.println("Employee ID does not exists... select from the above mentioned list");
                    employeeId = scanner.nextInt();
                }    
                    for (i = 0; i < serviceStation.getEmployees().size(); i++) {
                        if (serviceStation.getEmployees().get(i).getEmployeeId().equals(employeeId)) {
                            break;
                        }
                    }
                    invoice.assignEmployee(serviceStation.getEmployees().get(i).getName());
                serviceStation.addInvoice(invoice);				//Adding the invoice to the service station
            }
            System.out.println("\n1.Add an employee\n2.Add a customer\n3.Add employee as customer\n4.List the service charges of all vehicles\n5.List all employees\n6.List all customers\n7.Exit");
            choice = scanner.nextInt();
            scanner.nextLine();
        } while (choice != 7);
    }
}
