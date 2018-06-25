package week1.day4.servicestation;

public class Employee extends Person{
	private Integer employeeId;
	public Employee(){
	}
	public Employee(int employeeId,String name,int age,String address,long contactNumber){
		super(name,age,address,contactNumber);
		this.employeeId=employeeId;
	}
	public void setEmployeeId(int employeeId){
		this.employeeId=employeeId;
	}
	public Integer getEmployeeId(){
		return employeeId;
	}
        @Override
	public void printDetails(){
		System.out.println("Employee Id\t: "+employeeId);
		super.printDetails();
	}
}