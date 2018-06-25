package week1.day5.MobileCreation;
import java.util.ArrayList;
import java.util.Scanner;

public class MobileCreation{
	public static void main(String args[]){
		ArrayList<Mobile> mobiles=new ArrayList<>();
		Scanner scanner=new Scanner(System.in);
		System.out.println("1.Create a mobile\n2.Print all the mobiles and remaining charge\n3.Exit");
		Integer choice=scanner.nextInt();
		scanner.nextLine();
		do{
			switch(choice){
				case 1:
					System.out.println("Enter the name of the mobile");
					String name=scanner.nextLine();
					Mobile mobile=new Mobile(name){
						@Override
						public void remainingCharge(){
							System.out.println("Remaining Charge: "+(int)(Math.random()*100));
						}
					};
					mobiles.add(mobile);
					break;
				case 2:
					if(mobiles.size()>0){
						for(int i=0;i<mobiles.size();i++){
							mobiles.get(i).name();
							mobiles.get(i).remainingCharge();
						}
					}
					else{
						System.out.println("\n*****Mobile list is empty*****");
					}
					break;
				case 3:
					break;
				default:
					System.out.println("\n*****Invalid choice******");
			}
			System.out.println("1.Create a mobile\n2.Print all the mobiles and remaining charge\n3.Exit");
			choice=scanner.nextInt();
			scanner.nextLine();
		}while(choice!=3);
	}
}
