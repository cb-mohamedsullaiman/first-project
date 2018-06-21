package javabasics;
import java.util.Scanner;
class AddNumbers{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		int firstNum=scanner.nextInt();			//Getting very first number
		int secondNum=scanner.nextInt();			//Getting second number
		int sum=firstNum+secondNum;                  	//Adding two numbers
		System.out.println(sum);				//Printing the number within the bracket
 	}
}
