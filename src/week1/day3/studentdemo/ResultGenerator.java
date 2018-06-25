package week1.day3.studentdemo;

public class ResultGenerator{
	public static void generateResult(Student student){			//This method is used to generate result
		student.printStudentDetails();
		Float total=new Float(student.getSubjects().getMark1()+student.getSubjects().getMark2()+student.getSubjects().getMark3());
		Float average=total/3;
		System.out.println("Total\t:"+total);
		System.out.println("Average\t:"+average);
	}
}
