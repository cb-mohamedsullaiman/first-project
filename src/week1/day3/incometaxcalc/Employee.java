package week1.day3.incometaxcalc;
import java.util.Scanner;

public class Employee{
	private Integer employeeId;
	private String employeeName;
	private Float basicSalary;
	private Float totalSalary;
	private Float tax;
	private Character gender;
	public Employee(){							//Constructor with zero arguments
		
	}
	public Employee(Integer employeeId,String employeeName,Character gender,Float basicSalary){		//Constructor for 4 arguments
		this.employeeId=employeeId;
		this.employeeName=employeeName;
		this.gender=gender;
		this.basicSalary=basicSalary;
		this.tax=(float)0.0;
	}
	public void setEmployeeId(Integer employeeId){					//Method for setting employee id
		this.employeeId=employeeId;
	}
	public void setEmployeeName(String employeeName){				//Method for setting employee name
		this.employeeName=employeeName;
	}
	public void setEmployeeGender(Character gender){				//Method for setting employee gender
		this.gender=gender;
	}
	public void setBasicSalary(Float basicSalary){				//Method for setting basic salary
		this.basicSalary=basicSalary;
	}
	public void setTax(Float tax){
		this.tax=tax;
	}
	public String getEmployeeName(){
		return employeeName;
	}
	public Float getTotalSalary(){
		return totalSalary;
	}
	public Character getGender(){
		return gender;
	}
	public Float getTax(){
		return tax;
	}
	public void calculateTotalSalary(Float dearnessAllowance,Float houseRentAllowance,float deduction){		//Method for calculating total salary
		this.totalSalary=this.basicSalary+(dearnessAllowance/100)*basicSalary+(houseRentAllowance/100)*basicSalary-(deduction/100)*basicSalary;
	}
	public void getEmployeeDetails(){					//Printing employee details
		System.out.println("Employee id : "+employeeId);
		System.out.println("Employee name : "+employeeName);
		System.out.println("Employee gender :"+gender);
		System.out.println("Employee salary : "+totalSalary);
	}
}
