package classbasics;

public class Student{
	private Integer studentId;
	private String studentName;
	private Boolean gender;
	private Subject subjects;
	public Subject getSubjects(){
		return subjects;
	}
	public void setStudentDetails(Integer studentId,String studentName,Boolean gender,Subject subjects){
		this.studentId=studentId;
		this.studentName=studentName;
		this.gender=gender;
		this.subjects=subjects;
	}
	public void printStudentDetails(){						//Method to print the student details
		System.out.println("Student id\t:"+studentId);
		System.out.println("Student name\t:"+studentName);
		if(gender){
		 	System.out.println("Gender:\tMale");
		}
		else{
			System.out.println("Gender:\tFemale");
		}
		System.out.println("Subject details:\n");
		subjects.printSubjectDetails();
	}
}