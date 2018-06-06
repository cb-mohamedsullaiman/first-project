import java.util.Scanner;
class Conditional{
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		if(num%2==0){				//Checking whether it gives 0 as remainder or not
			System.out.println("Even");     //If the above condition is true, this statement gets executed
		}
		else{					//This block will get executed when the if condition becomes false	
			System.out.println("Odd");
		}
	}
}
