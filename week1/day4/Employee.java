package oopsjava;

public class Employee extends Person{
	private Integer empId;
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
	public Integer getEmpId(){
		return empId;
	}
	public void printDetails(){
		System.out.println("Employee Id\t: "+empId);
		super.printDetails();
	}
}