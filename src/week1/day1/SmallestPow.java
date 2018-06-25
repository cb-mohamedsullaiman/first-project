package week1.day1;
import java.util.Scanner;
class SmallestPow{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter a number");
		int firstNum=scanner.nextInt();			//Getting first number
		System.out.println("Enter another number");
		int secondNum=scanner.nextInt();		//Getting second number
		int smallest_power=0,product=1;
		while(product<=secondNum){ 			//It works until product becomes greater than the secondNum	
			product=product*firstNum;	  	//Increasing the power gradually
			smallest_power++;			//Total number of power increase
		}
		System.out.println("The smallest power of "+firstNum+" greater than "+secondNum+" is "+smallest_power);
	}
}
