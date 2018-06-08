package classbasics;
import java.util.ArrayList;
import java.util.Scanner;
class Employee{
	private int empId;
	String empName;
	float basicSalary;
	float totalSalary;
	float tax;
	char gender;
	public Employee(){							//Constructor with zero arguments
		empId=0;
		empName=null;
		basicSalary=0;
		totalSalary=0;
		gender='-';
		tax=0;
	}
	public Employee(int empId,String empName,char gender,float basicSalary){		//Constructor for 4 arguments
		this.empId=empId;
		this.empName=empName;
		this.gender=gender;
		this.basicSalary=basicSalary;
		this.totalSalary=0;
		this.tax=0;
	}
	public void setEmployeeId(int empId){					//Method for setting employee id
		this.empId=empId;
	}
	public void setEmployeeName(String empName){				//Method for setting employee name
		this.empName=empName;
	}
	public void setEmployeeGender(char gender){				//Method for setting employee gender
		this.gender=gender;
	}
	public void setBasicSalary(float basicSalary){				//Method for setting basic salary
		this.basicSalary=basicSalary;
	}
	public void calculateTotalSalary(float dearnessAllowance,float houseRentAllowance,float deduction){		//Method for calculating total salary
		this.totalSalary=this.basicSalary+(dearnessAllowance/100)*basicSalary+(houseRentAllowance/100)*basicSalary-(deduction/100)*basicSalary;
	}
	public void getEmployeeDetails(){					//Printing employee details
		System.out.println("Employee id : "+empId);
		System.out.println("Employee name : "+empName);
		System.out.println("Employee gender :"+gender);
		System.out.println("Employee salary : "+totalSalary);
	}
	public static void main(){						//This main is for Employee.class
		Scanner scanner=new Scanner(System.in);
		Employee emp= new Employee();
		System.out.println("Enter employee id");
		emp.setEmployeeId(scanner.nextInt());
		scanner.nextLine();
		System.out.println("Enter employee name");
		emp.setEmployeeName(scanner.nextLine());
		System.out.println("Enter the gender of the employee");
		emp.setEmployeeGender(scanner.next().charAt(0));
		System.out.println("Enter basic salary");
		emp.setBasicSalary(scanner.nextFloat());
		System.out.println("Enter Dearness allowance percentage");
		float dearnessAllowance=scanner.nextFloat();
		System.out.println("Enter House rent allowance percentage");
		float houseAllowance=scanner.nextFloat();
		System.out.println("Enter the deduction percentage");
		float deduction=scanner.nextFloat();
		emp.calculateTotalSalary(dearnessAllowance,houseAllowance,deduction);
		
	}
}
class IncomeTaxCalc{
	public static ArrayList<Employee> getTaxableEmployee(ArrayList<Employee> emp){		//Method for getting taxable employees
		ArrayList<Employee> emp1=new ArrayList<Employee>();
		for(int i=0;i<emp.size();i++){
			if(emp.get(i).totalSalary>=250000.0){
				emp1.add(emp.get(i));
			}
		}
		return emp1;
	}
	public static void calculateTax(ArrayList<Employee> emp){
		for(int i=0;i<emp.size();i++){
			if(emp.get(i).totalSalary>=1000000){				//Calculating tax for employees with salary greater than 1000000
				if(emp.get(i).gender=='M'){
					emp.get(i).tax=(float)0.3*emp.get(i).totalSalary;
				}
				else if(emp.get(i).gender=='F'){
					emp.get(i).tax=(float)0.28*emp.get(i).totalSalary;	
				}
				else{
					System.out.println("Problem with gender");
				}
			}
			else if(emp.get(i).totalSalary>=500000){			//Calculating tax for employees with salary 1000000>salary>=500000
				if(emp.get(i).gender=='M'){
					emp.get(i).tax=(float)0.2*emp.get(i).totalSalary;
				}
				else if(emp.get(i).gender=='F'){
					emp.get(i).tax=(float)0.18*emp.get(i).totalSalary;
				}
				else{
					System.out.println("Problem with gender");
				}
			}
			else if(emp.get(i).totalSalary>=250000){			//Calculating tax for employees with salary 500000>salary>=250000
				if(emp.get(i).gender=='M'){
					emp.get(i).tax=(float)0.05*emp.get(i).totalSalary;
				}
				else if(emp.get(i).gender=='F'){
					emp.get(i).tax=(float)0.045*emp.get(i).totalSalary;
				}
				else{
					System.out.println("Problem with gender");
				}
			}
		}
	}
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		boolean choice;
		ArrayList<Employee> employees=new ArrayList<Employee>();			//Creating a list of employees
		do{
			System.out.println("Enter the employee id");
			int empId=scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter the name of the employee");			
			String empName=scanner.nextLine();					//Getting name of employee
			System.out.println("Enter the gender of the employee");			
			char gender=scanner.nextLine().charAt(0);				//Getting gender
			System.out.println("Enter the basic pay of employee");			
			float basicSalary=scanner.nextFloat();					//Getting basic salary
			Employee emp=new Employee(empId,empName,gender,basicSalary);		//Creating an employee
			System.out.println("Enter the dearness allowance percentage");
			float dearnessAllowance=scanner.nextFloat();				//Getting dearness allowance percentage
			System.out.println("Enter the house rent allowance percentage");
			float houseAllowance=scanner.nextFloat();				//Getting house allowance percentage
			System.out.println("Enter the deduction percentage");
			float deduction=scanner.nextFloat();					//Getting deduction percentage
			emp.calculateTotalSalary(dearnessAllowance,houseAllowance,deduction);			//Calculating total salary
			employees.add(emp);									//Adding employee to the employee list
			System.out.println("Do you want to add more employees? If yes type true and if not type false");
			choice=scanner.nextBoolean();						//Getting choice
		}while(choice==true);
		calculateTax(employees);							//Calculating tax for employees
		ArrayList<Employee> taxableEmployees=new ArrayList<Employee>();			//Creating taxable employees list
		taxableEmployees=getTaxableEmployee(employees);					//Getting taxable employees list
		for(int i=0;i<taxableEmployees.size();i++){
			System.out.println("["+taxableEmployees.get(i).empName+"] | ["+taxableEmployees.get(i).gender+"] | ["+taxableEmployees.get(i).totalSalary+"] | ["+taxableEmployees.get(i).tax+"]");
			//Printing details of taxable employees
		}
	}
}
		
