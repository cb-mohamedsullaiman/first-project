import java.util.Scanner;

class InterestCalc{
	public static void main(String args[]){

		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the principal amount");
		Float principal=scanner.nextFloat();				//Getting the principal amount
		System.out.println("Enter the number of years");
		Float years=scanner.nextFloat();				//Getting the number of years
		System.out.println("Ente the rate of interest");
		Double rate=scanner.nextDouble();				//Getting the rate of interest in percentage
		System.out.println("Choose either 0 for SI or 1 for CI");
		int choice=scanner.nextInt();					//Getting the choice of user (SI/CI)
		switch(choice){
			case 0:			
 				Double simpInterest=(principal*years*rate)/100;			//Calculating SI
				System.out.println("The Simple interest is "+simpInterest);
				break;
			case 1:						//It will calculate CI
				System.out.println("Enter the number of times interest has to be compounded per year");		//Sometimes compound interest will be calculated many times in a year
				int n=scanner.nextInt();
				Double compInterest=(principal*(Math.pow((1+(rate/(n*100))),(n*years))))-principal;			//Calculating compound interest
				System.out.println("The Compound interest is "+compInterest);
				break;
			default:
				System.out.println("Invalid input...Please choose 0 or 1");			//Throwing error if the user choice is invalid
		}
	}
}
