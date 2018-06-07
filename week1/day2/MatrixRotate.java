import java.util.Scanner;
public class MatrixRotate{
	public static void main(String args[]){
		int i,j;							//Iterator values
		Scanner scanner=new Scanner(System.in);
		MatrixRotate matRotate=new MatrixRotate();
		System.out.println("Enter n for n*n matrix");			//Getting n for nxn matrix
		int num=scanner.nextInt();
		int mat[][]=new int[num][num];
		System.out.println("Enter the elements one by one");
		for(i=0;i<num;i++){
			for(j=0;j<num;j++){
				mat[i][j]=scanner.nextInt();				//Reading matrix values
			}
		}
		System.out.println("Press 0 if you want to rotate the matrix left\nPress 1 if you want to rotate the matrix right");
		int choice=scanner.nextInt();
		int length=mat.length-1;
		switch(choice){
			case 0:
				mat=matRotate.rotateLeft(mat,length);						//It will rotate left
				break;
			case 1:
				mat=matRotate.rotateRight(mat,length);
				break;
			default:
				System.out.println("Invalid choice");
			
		}
		matRotate.printArray(mat,num);
	}
	int[][] rotateLeft(int mat[][],int length){
		for(int i=0;i<=length;i++){
			for(int j=i;j<length-i;j++){
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
		return mat;
	}
	int[][] rotateRight(int mat[][],int length){						//It will rotate right
		for(int i=0;i<=length;i++){
			for(int j=i;j<length-i;j++){
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
		return mat;
	}
	void printArray(int mat[][],int num){
		for(int i=0;i<num;i++){
			for(int j=0;j<num;j++){
				System.out.print(mat[i][j]+"\t");		//Printing the matrix elements
			}
			System.out.print("\n");					//Nextline(next row)
		}
	}
}
