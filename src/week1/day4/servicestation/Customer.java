package week1.day4.servicestation;

public class Customer extends Person{						
	private Integer customerId;
	private String type;
	public Customer(){
		this.type="Customer";
	}
	public Customer(int customerId,String name,int age,String address,long contactNumber){
		super(name,age,address,contactNumber);
		this.customerId=customerId;
		this.type="Customer";
	}
	public void setCustomerId(int customerId){
		this.customerId=customerId;
	}
	public Integer getCustomerId(){
		return customerId;
	}
	public String getCustomerType(){
		return type;
	}
	public void setEmployeeAsCustomer(Employee emp){
		this.customerId=emp.getEmployeeId();							//Setting the customer id as employee id
		this.setName(emp.getName());
		this.setAge(emp.getAge());
		this.setContactNumber(emp.getContactNumber());
		this.setAddress(emp.getAddress());
		this.type="Employee";									//Since this customer is an employee we are setting the type as employee
	}
        @Override
	public void printDetails(){
		if(type.equals("Customer")){
			System.out.println("Customer Id \t\t: "+customerId);
		}
		else if(type.equals("Employee")){
			System.out.println("Customer(Employee) Id \t: "+customerId);
		}
		super.printDetails();
	}
}