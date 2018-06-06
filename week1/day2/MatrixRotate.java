import java.util.Scanner;
public class MatrixRotate{
	public static void main(String args[]){
		int i,j;							//Iterator values
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter n for n*n matrix");			//Getting n for nxn matrix
		int n=scanner.nextInt();
		int mat[][]=new int[n][n];
		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				mat[i][j]=scanner.nextInt();				//Reading matrix values
			}
		}
		System.out.println("Press 0 if you want to rotate the matrix left\nPress 1 if you want to rotate the matrix right");
		int choice=scanner.nextInt();
		int length=mat.length-1;
		if(choice==0){							//It will rotate left
			for(i=0;i<=length;i++){
				for(j=i;j<length-i;j++){
					int temp1=mat[i][j]; 			//Storing all four values across four sides
					int temp2=mat[j][length-i];
					int temp3=mat[length-i][length-j];
					int temp4=mat[length-j][i];
					mat[length-j][i]=temp1;			//Changing the values across four sides(rotating)
					mat[length-i][length-j]=temp4;
					mat[j][length-i]=temp3;
					mat[i][j]=temp2;
				}
			}
		}
		else if(choice==1){						//It will rotate right
			for(i=0;i<=length;i++){
				for(j=i;j<length-i;j++){
					int temp1=mat[length-i][j];		//Storing four values across four sides
					int temp2=mat[length-j][length-i];
					int temp3=mat[i][length-j];
					int temp4=mat[j][i];
					mat[j][i]=temp1;			//Changing those values by rotating them
					mat[length-i][j]=temp2;
					mat[length-j][length-i]=temp3;
					mat[i][length-j]=temp4;
				}
			}
		}
		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				System.out.print(mat[i][j]+"\t");		//Printing the matrix elements
			}
			System.out.print("\n");					//Nextline(next row)
		}
	}
}
