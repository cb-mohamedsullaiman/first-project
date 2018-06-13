package nestedbasics;
import java.util.Iterator;
import java.util.Scanner;

public class Factorial implements Iterable{
	private Integer lowerLimit;
	private Integer upperLimit;
	public Factorial(Integer lowerLimit,Integer upperLimit){
		this.lowerLimit=lowerLimit;
		this.upperLimit=upperLimit;
	}
	public Iterator iterator() {
		Iterator iterator= new Iterator(){
			private int index=lowerLimit;
			public boolean hasNext(){
				if(index<=upperLimit){
					return true;
				}
				else{
					return false;
				}
			}
			public Object next(){
				return index++;
			}
		}; 
		return iterator;
	}
	public String toString(){
		Iterator iterator=this.iterator();
		StringBuilder factorialString=new StringBuilder();
		while(iterator.hasNext()){
			Integer factorialNumber=Integer.parseInt(iterator.next().toString());
			if(factorialNumber==0||factorialNumber==1){
				factorialString.append(1);
			}
			else{
				int factorial=1;
				for(int i=factorialNumber;i>1;i--){
					factorial=factorial*i;
				}
				factorialString.append(factorial);
			}
			if(iterator.hasNext()){
				factorialString.append(", ");
			}

		}
		return factorialString.toString();

	}
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		System.out.println("1. Finding factorial\n2.Exit");
		Integer choice=scanner.nextInt();
		do{
			switch(choice){
				case 1:
					System.out.println("Enter the lower limit number");
					Integer lowerLimit=scanner.nextInt();
					System.out.println("Enter the upper limit number");
					Integer upperLimit=scanner.nextInt();
					Factorial factorial=new Factorial(lowerLimit,upperLimit);
					System.out.println("The factorial of the number between "+lowerLimit+" and "+upperLimit+" is "+factorial.toString());
					break;
				case 2:
					break;
				default:
					System.out.println("\n*****Invalid choice*****");
			}
			if(choice==2){
				break;
			}
			System.out.println("1. Finding factorial\n2.Exit");
			choice=scanner.nextInt();
		}while(choice!=2);

	}
}