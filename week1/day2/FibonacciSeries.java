import java.util.Scanner;
class FibonacciSeries{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		int n=scanner.nextInt();			//Getting the number uptowhich we are going to print Fibonacci numbers
		int factFirst=0;				//First factorial variable
		int factSecond=1;				//Second factorial variable
		System.out.println(factFirst);			//Printing 0
		while(factSecond<=n){
			System.out.println(factSecond);		//Printing the second factorial variable 
			int temp=factSecond;
			factSecond=factFirst+factSecond;	//We are iterating through the fibonacci series upto the number
			factFirst=temp;
		}
	}
}
