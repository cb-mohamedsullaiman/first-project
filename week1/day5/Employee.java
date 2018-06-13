package nestedbasics;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class Employee{
	String name;
	Float salary;
	Integer age;
	public Employee(String name,Float salary,Integer age){
		this.name=name;
		this.salary=salary;
		this.age=age;
	}
	public static void printEmployees(ArrayList<Employee> employees){
		for(int i=0;i<employees.size();i++){
			System.out.println("Name:\t\t"+employees.get(i).name);
			System.out.println("Salary:\t\t"+employees.get(i).salary);
			System.out.println("Age:\t\t"+employees.get(i).age);
			System.out.println("\n");
		}
	}
	public static void main(String args[]){
		ArrayList<Employee> employees=new ArrayList<Employee>();
		Scanner scanner=new Scanner(System.in);
		System.out.println("1.Add an employee\n2.Sort employees by Name\n3.Sort employees by Salary\n4.Sort employees by Age\n5.Exit");
		Integer choice=scanner.nextInt();
		scanner.nextLine();
		do{
			switch(choice){
				case 1:
					System.out.println("Enter the name of the employee");
					String name=scanner.nextLine();
					System.out.println("Enter the salary of the employee");
					Float salary=scanner.nextFloat();
					System.out.println("Enter the age of the employee");
					Integer age=scanner.nextInt();
					Employee employee=new Employee(name,salary,age);
					employees.add(employee);
					break;
				case 2:
					if(employees.size()>0){
						ArrayList<Employee> employeeByName=(ArrayList<Employee>)employees.clone();
						Collections.copy(employeeByName,employees);
						Collections.sort(employeeByName,new Comparator<Employee>(){
							public int compare(Employee employee1,Employee employee2){
								return employee1.name.compareTo(employee2.name);
							}
						});
						printEmployees(employeeByName);
					}
					else{
						System.out.println("*****Employee list is empty*****");
					}
					break;
				case 3:
					if(employees.size()>0){
						ArrayList<Employee> employeeBySalary= (ArrayList<Employee>)employees.clone();
						Collections.sort(employeeBySalary,new Comparator<Employee>(){
							public int compare(Employee employee1,Employee employee2){
								return (int)(employee1.salary-employee2.salary);
							}
						});
						printEmployees(employeeBySalary);
					}
					else{
						System.out.println("*****Employee list is empty*****");
					}
					break;
				case 4:
					if(employees.size()>0){
						ArrayList<Employee> employeeByAge=(ArrayList<Employee>)employees.clone();
						Collections.copy(employeeByAge,employees);
						Collections.sort(employeeByAge,new Comparator<Employee>(){
							public int compare(Employee employee1,Employee employee2){
								return employee1.age-employee2.age;
							}
						});
						printEmployees(employeeByAge);
					}
					else{
						System.out.println("*****Employee list is empty*****");
					}
					break;
				case 5:
					break;
				default:
					System.out.println("\n*****Invalid choice*****");
			}
			System.out.println("1.Add an employee\n2.Sort employees by Name\n3.Sort employees by Salary\n4.Sort employees by Age\n5.Exit");
			choice=scanner.nextInt();
			scanner.nextLine();
		}while(choice!=5);
	} 
}