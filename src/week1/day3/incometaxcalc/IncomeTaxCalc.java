package week1.day3.incometaxcalc;
import week1.day3.incometaxcalc.Employee;
import java.util.ArrayList;
import java.util.Scanner;

public class IncomeTaxCalc{
	public static ArrayList<Employee> getTaxableEmployee(ArrayList<Employee> employees){		//Method for getting taxable employees
		ArrayList<Employee> taxableEmployees=new ArrayList<Employee>();
		for(int i=0;i<employees.size();i++){
			if(employees.get(i).getTotalSalary()>=250000.0){
				taxableEmployees.add(employees.get(i));
			}
		}
		return taxableEmployees;
	}
	public static ArrayList<Employee> getTaxableEmployeeByGender(ArrayList<Employee> employees,Character gender){
		ArrayList<Employee> taxableEmployeesByGender=new ArrayList<Employee>();
		for(int i=0;i<employees.size();i++){
			if(employees.get(i).getTotalSalary()>=250000.0&&employees.get(i).getGender()==gender){
				taxableEmployeesByGender.add(employees.get(i));
			}
		}
		return taxableEmployeesByGender;
	}
	public static void calculateTax(Employee employee){
		switch(employee.getGender()){
			case 'M':
				if(employee.getTotalSalary()>=1000000){				//Calculating tax for employees with salary greater than 1000000
					employee.setTax((float)0.3*employee.getTotalSalary());
				}
				else if(employee.getTotalSalary()>=500000){
					employee.setTax((float)0.2*employee.getTotalSalary());
				}
				else if(employee.getTotalSalary()>=250000){
					employee.setTax((float)0.05*employee.getTotalSalary());
				}
				break;
			case 'F':
				if(employee.getTotalSalary()>=1000000){				//Calculating tax for employees with salary greater than 1000000
					employee.setTax((float)0.28*employee.getTotalSalary());
				}
				else if(employee.getTotalSalary()>=500000){
					employee.setTax((float)0.18*employee.getTotalSalary());
				}
				else if(employee.getTotalSalary()>=250000){
					employee.setTax((float)0.045*employee.getTotalSalary());
				}
				break;
			default:
				System.out.println("Problem with gender");
			
		}
	}
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		ArrayList<Employee> employees=new ArrayList<Employee>();		//Creating a list of employees
		System.out.println("1.Add employee\n2.Print Taxable employees\n3.Print Taxable Male Employees\n4.Print Taxable Female Employees\n5.Print all employees\n6.Exit");
		Integer choice=scanner.nextInt();
		do{
			switch(choice){
				case 1:
					System.out.println("Enter the employee id");
					Integer empId=scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter the name of the employee");			
					String empName=scanner.nextLine();					//Getting name of employee
					Character gender;
					do{
						System.out.println("Enter the gender of the employee(M for Male and F for female\n****No other characters are allowed***");			
						gender=scanner.nextLine().charAt(0);				//Getting gender
					}while(!(gender.equals('M')||gender.equals('F')));
					System.out.println("Enter the basic pay of employee");			
					Float basicSalary=scanner.nextFloat();					//Getting basic salary
					Employee employee=new Employee(empId,empName,gender,basicSalary);		//Creating an employee
					System.out.println("Enter the dearness allowance percentage");
					Float dearnessAllowance=scanner.nextFloat();				//Getting dearness allowance percentage
					System.out.println("Enter the house rent allowance percentage");
					Float houseAllowance=scanner.nextFloat();				//Getting house allowance percentage
					System.out.println("Enter the deduction percentage");
					Float deduction=scanner.nextFloat();					//Getting deduction percentage
					employee.calculateTotalSalary(dearnessAllowance,houseAllowance,deduction);			//Calculating total salary
					calculateTax(employee);
					employees.add(employee);	
					break;								//Adding employee to the employee list
				case 2:		
					ArrayList<Employee> taxableEmployees=new ArrayList<Employee>();			//Creating taxable employees list
					taxableEmployees=getTaxableEmployee(employees);					//Getting taxable employees list
					if(taxableEmployees.size()>0){
						for(int i=0;i<taxableEmployees.size();i++){
							System.out.println("["+taxableEmployees.get(i).getEmployeeName()+"] | ["+taxableEmployees.get(i).getGender()+"] | ["+taxableEmployees.get(i).getTotalSalary()+"] | ["+taxableEmployees.get(i).getTax()+"]");
							//Printting details of taxable employees
						}
					}
					else{
						System.out.println("\n*****No taxable employees found*****");
					}
					break;
				case 3:
					ArrayList<Employee> taxableMaleEmployees=new ArrayList<Employee>();
					taxableMaleEmployees=getTaxableEmployeeByGender(employees,'M');
					if(taxableMaleEmployees.size()>0){
						for(int i=0;i<taxableMaleEmployees.size();i++){
							System.out.println("["+taxableMaleEmployees.get(i).getEmployeeName()+"] | ["+taxableMaleEmployees.get(i).getGender()+"] | ["+taxableMaleEmployees.get(i).getTotalSalary()+"] | ["+taxableMaleEmployees.get(i).getTax()+"]");
							//Printing details of taxable Male employees
						}
					}
					else{
						System.out.println("\n*****No taxable Male employees found*****");
					}
					break;
				case 4:
					ArrayList<Employee> taxableFemaleEmployees=new ArrayList<Employee>();
					taxableFemaleEmployees=getTaxableEmployeeByGender(employees,'F');
					if(taxableFemaleEmployees.size()>0){
						for(int i=0;i<taxableFemaleEmployees.size();i++){
							System.out.println("["+taxableFemaleEmployees.get(i).getEmployeeName()+"] | ["+taxableFemaleEmployees.get(i).getGender()+"] | ["+taxableFemaleEmployees.get(i).getTotalSalary()+"] | ["+taxableFemaleEmployees.get(i).getTax()+"]");
							//Printing details of taxable Male employees
						}
					}
					else{
						System.out.println("\n*****No taxable Female employees found*****");
					}
					break;
				case 5:
					if(employees.size()>0){
						for(int i=0;i<employees.size();i++){
							System.out.println("["+employees.get(i).getEmployeeName()+"] | ["+employees.get(i).getGender()+"] | ["+employees.get(i).getTotalSalary()+"] | ["+employees.get(i).getTax()+"]");
							//Printing details of taxable Male employees
						}
					}
					else{
						System.out.println("\n*****No employees found*****");
					}
					break;
				case 6:
					break;
			}
		System.out.println("1.Add employee\n2.Print Taxable employees\n3.Print Taxable Male Employees\n4.Print Taxable Female Employees\n5.Print all employees\n6.Exit");
		choice=scanner.nextInt();
		scanner.nextLine();
		}while(choice!=6);
	}
}
		
