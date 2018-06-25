package week1.day3.studentdemo;
import week1.day3.studentdemo.ResultGenerator;
import java.util.Scanner;

public class StudentDemo{
	public static void storeStudentData(Student student){			//This method is used to get all student details from user and setting it to the object
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the student id:");
		Integer studentId=new Integer(scanner.nextInt());
		scanner.nextLine();
		System.out.println("Enter the student name:");
		String studentName=scanner.nextLine();
		System.out.println("Enter the gender: true for male and false for female");
		Boolean gender=new Boolean(scanner.nextBoolean());
		scanner.nextLine();
		System.out.println("Enter the name of subject 1:");
		String subject1=scanner.nextLine();
		System.out.println("Enter the mark for subject 1:");
		Float mark1=new Float(scanner.nextFloat());
		scanner.nextLine();
		System.out.println("Enter the name of subject 2:");
		String subject2=scanner.nextLine();
		System.out.println("Enter the mark for subject 2:");
		Float mark2=new Float(scanner.nextFloat());
		scanner.nextLine();
		System.out.println("Enter the name for subject 3:");
		String subject3=scanner.nextLine();
		System.out.println("Enter the mark for subject 3:");
		Float mark3=new Float(scanner.nextFloat());
		week1.day3.studentdemo.Subject subject=new week1.day3.studentdemo.Subject(subject1,mark1,subject2,mark2,subject3,mark3);
		student.setStudentDetails(studentId,studentName,gender,subject);
	}
	public static void main(String args[]){		
		Student student=new Student();		//This will create a student object
		storeStudentData(student);		//This will set all the student details in the object
		ResultGenerator.generateResult(student);		//This will display the output
	}
}

