package oopsjava;

public class Customer extends Person{						
	private Integer custId;
	private String type;
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
	public Integer getCustId(){
		return custId;
	}
	public String getCustType(){
		return type;
	}
	public void setEmployeeAsCustomer(Employee emp){
		this.custId=emp.getEmpId();
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