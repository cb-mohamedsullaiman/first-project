package userinput;
import java.util.Scanner;
class ArrayMaxMin{
	public static void main(String args[]){
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the number of elements in array");
		int num=scanner.nextInt();		//Getting number of elements in array
		int max=-9999,min=9999;			//Just assigning some small and large number
		int array[]=new int[num];		//Allocating memory
		System.out.println("Enter the elements one by one");
		for(int i=0;i<num;i++){			
			array[i]=scanner.nextInt();	//Getting array element
			if(max<array[i]){
				max=array[i];		//Storing the element as max if the current element is greater than max
			}
			if(min>array[i]){		
				min=array[i];		//Storing the element as min if the current element is smaller than min
			}
		}
		System.out.println("The maximum of array is "+max+" and the minimum of array is "+min);		//Printing max and min
	}
}
