import java.util.Scanner;

class InterestCalc{
	public static void main(String args[]){

		Scanner scanner=new Scanner(System.in);
		System.out.println("Press 0 for SI and 1 for CI");
		int choice=scanner.nextInt();					//Getting the choice of user
		System.out.println("Enter the principal amount");
		Float principal=scanner.nextFloat();				//Getting the principal amount
		System.out.println("Enter the number of years");
		Float years=scanner.nextFloat();				//Getting the number of years
		System.out.println("Ente the rate of interest");
		Double rate=scanner.nextDouble();				//Getting the rate of interest in percentage
		Double interest = 0.0;
		switch(choice){
			case 0:							//It will calculate SI
 				interest=(principal*years*rate)/100;			//Calculating SI
				break;
			case 1:						//It will calculate CI
				System.out.println("Enter the number of times interest has to be compounded per year");		//Sometimes compound interest will be calculated many times in a year
				int n=scanner.nextInt();
				interest=(principal*(Math.pow((1+(rate/(n*100))),(n*years))))-principal;			//Calculating compound interest
		}
		System.out.println(interest);					//Printing interest
	}
}
