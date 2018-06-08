package userinput;
import java.util.Scanner;
class FibonacciSeries{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the number upto which you want the fibonacci numbers");
		int num=scanner.nextInt();			//Getting the number upto which we are going to print Fibonacci numbers
		int factFirst=0;				//First factorial variable
		int factSecond=1;				//Second factorial variable
		System.out.println("The fibonacci series upto "+num+" is");
		System.out.println(factFirst);			//Printing 0
		while(factSecond<=num){
			System.out.println(factSecond);		//Printing the second factorial variable 
			int temp=factSecond;
			factSecond=factFirst+factSecond;	//We are iterating through the fibonacci series upto the number
			factFirst=temp;
		}
	}
}
