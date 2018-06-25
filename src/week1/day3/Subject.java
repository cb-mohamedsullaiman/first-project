package classbasics;

public class Subject{
	private String subject1;
	private Float mark1;
	private String subject2;
	private Float mark2;
	private String subject3;
	private Float mark3;
	public Subject(String subject1,Float mark1,String subject2,Float mark2,String subject3,Float mark3){	//Method to set the subject details
		this.subject1=subject1;
		this.mark1=mark1;
		this.subject2=subject2;
		this.mark2=mark2;
		this.subject3=subject3;
		this.mark3=mark3;
	}
	public Float getMark1(){
		return mark1;
	}
	public Float getMark2(){
		return mark2;
	}
	public Float getMark3(){
		return mark3;
	}
	public void printSubjectDetails(){						//Method to print the subject details
		System.out.println("Subject \t:"+subject1+"\t\t\tMarks:\t"+mark1);
		System.out.println("Subject \t:"+subject2+"\t\t\tMarks:\t"+mark2);
		System.out.println("Subject \t:"+subject3+"\t\t\tMarks:\t"+mark3);
	}
}
