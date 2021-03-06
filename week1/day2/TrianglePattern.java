package userinput;
import java.util.Scanner;
public class TrianglePattern{
	public static void main(String args[]){
		int i,j;								//Iterator variables
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the number of layers");	
		int layers=scanner.nextInt();						//Number of layers in triangle
		for(i=0;i<layers;i++){							//For every layer
			for(j=0;j<2*layers;j++){						//In every layer
				if((i+(j/2))>=layers-1){					//If it is lower left right angled triangle
					if(j%2==0){					//If the j%2 gives remainder 0
						System.out.print((i+(j/2))-(layers-2));	//Print the number
					}
					else{
						System.out.print(" ");			//Print space
					}
				}
				else{							//If it is upper right angled triangle
					System.out.print(" ");				//Print space
				}
			}
			for(j=0;j<(2*(layers-1))-1;j++){					//For another triangle
				if((i-(j/2))>0){
					if(j%2==0){
						System.out.print(i-(j/2));
					}
					else{
						System.out.print(" ");
					}
				}
				else{
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}
}

