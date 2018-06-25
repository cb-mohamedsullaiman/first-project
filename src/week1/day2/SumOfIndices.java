package week1.day2;
import java.util.Scanner;

public class SumOfIndices{
	public static void main(String args[]){
		int sum=0,i;
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter a string");
		String str=scanner.next();			//Getting the string
		for(i=0;i<str.length();i++){
			sum=sum+(str.charAt(i)-'a'+1);		//Summing the indices of alphabets
		}
		if(sum%2==0){					//If the sum is even
			System.out.println("even");
		}
		else{						//if the sum is not even
			System.out.println("odd");
		}
	}
}
