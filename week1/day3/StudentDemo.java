package classbasics;
import java.util.Scanner;
class Student{
	int studentId;
	String studentName;
	boolean gender;
	Subject subjects;
	public void setStudentDetails(int stuId,String stuName,boolean gender,Subject subj){		//Method to set the student details
		studentId=stuId;
		studentName=stuName;
		this.gender=gender;
		subjects =subj;
	}
	public void getStudentDetails(){						//Method to print the student details
		System.out.println("Student id\t:"+studentId);
		System.out.println("Student name\t:"+studentName);
		if(gender==true){
		 	System.out.println("Gender:\tMale");
		}
		else if(gender==false){
					System.out.println("Gender:\tFemale");
		}
		System.out.println("Subject details:\n");
		subjects.getSubjectDetails();
	}
}
class Subject{
	String subject1;
	float marks1;
	String subject2;
	float marks2;
	String subject3;
	float marks3;
	public void setSubjectDetails(String subj1,float mark1,String subj2,float mark2,String subj3,float mark3){	//Method to set the subject details
		subject1=subj1;
		marks1=mark1;
		subject2=subj2;
		marks2=mark2;
		subject3=subj3;
		marks3=mark3;
	}
	public void getSubjectDetails(){						//Method to print the subject details
		System.out.println("Subject \t:"+subject1+"\t\tMarks:\t"+marks1);
		System.out.println("Subject \t:"+subject2+"\t\tMarks:\t"+marks2);
		System.out.println("Subject \t:"+subject3+"\t\tMarks:\t"+marks3);
	}
}
class ResultGenerator{
	public static void generateResult(Student student){			//This method is used to generate result
		student.getStudentDetails();
		float total=student.subjects.marks1+student.subjects.marks2+student.subjects.marks3;
		float average=total/3;
		System.out.println("Total\t:"+total);
		System.out.println("Average\t:"+average);
	}
}
class StudentDemo{
	public static void storeStudentData(Student student){			//This method is used to get all student details from user and setting it to the object
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the student id:");
		int stuId=scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter the student name:");
		String name=scanner.nextLine();
		System.out.println("Enter the gender: true for male and false for female");
		boolean gend=scanner.nextBoolean();
		scanner.nextLine();
		System.out.println("Enter the name of subject 1:");
		String sub1=scanner.nextLine();
		System.out.println("Enter the mark for subject 1:");
		float mark1=scanner.nextFloat();
		scanner.nextLine();
		System.out.println("Enter the name of subject 2:");
		String sub2=scanner.nextLine();
		System.out.println("Enter the mark for subject 2:");
		float mark2=scanner.nextFloat();
		scanner.nextLine();
		System.out.println("Enter the name for subject 3:");
		String sub3=scanner.nextLine();
		System.out.println("Enter the mark for subject 3:");
		float mark3=scanner.nextFloat();
		Subject subject=new Subject();
		subject.setSubjectDetails(sub1,mark1,sub2,mark2,sub3,mark3);
		student.setStudentDetails(stuId,name,gend,subject);
	}
	public static void main(String args[]){		
		Student student=new Student();		//This will create a student object
		storeStudentData(student);		//This will set all the student details in the object
		ResultGenerator.generateResult(student);		//This will display the output
	}
}

