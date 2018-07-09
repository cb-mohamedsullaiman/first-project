package week1.day4.servicestation;

public class Customer extends Person{						
	private Integer customerId;
	private Boolean isEmployee;
	
        public Customer(){
            this.isEmployee = false;
        }
	public Customer(int customerId,String name,int age,String address,long contactNumber){
		super(name,age,address,contactNumber);
		this.customerId=customerId;
                this.isEmployee = false;
	}
	public void setCustomerId(int customerId){
		this.customerId=customerId;
	}
	public Integer getCustomerId(){
		return customerId;
	}
	public Boolean isEmployee(){
		return isEmployee;
	}
	public void setEmployeeAsCustomer(Employee emp,Integer customerId){
		this.customerId=customerId;							//Setting the customer id as employee id
		this.setName(emp.getName());
		this.setAge(emp.getAge());
		this.setContactNumber(emp.getContactNumber());
		this.setAddress(emp.getAddress());
		this.isEmployee=true;									//Since this customer is an employee we are setting the type as employee
	}
        @Override
	public void printDetails(){
		if(!isEmployee){
			System.out.println("Customer Id \t\t: "+customerId);
		}
		else {
			System.out.println("Customer(Employee) Id \t: "+customerId);
		}
		super.printDetails();
	}
}