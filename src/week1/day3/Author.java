package classbasics;
import java.util.Scanner;

public class Author{
	private String name;
	private String email;
	private Character gender;
	public void setAuthor(String name,String email,Character gender){			//Constructor with 2 parameters
		this.name=name;
		this.email=email;
		this.gender=gender;
	}
	public String getName(){						//Method to get name
		return name;
	}
	public String getEmail(){						//Method to get email
		return email;
	}
	public void setEmail(String email){					//Method to set email
		this.email=email;
	}
	public char getGender(){						//Method to get gender
		return gender;
	}
	public String toDisplay(){						//Method to return author details
		return ("["+name+"] [("+gender+")] at ["+email+"]");
	}
	public static void main(String args[]){					//main for author  class
		Scanner scanner=new Scanner(System.in);
		Author author=new Author();
		System.out.println("1.Construct an author\n2.Print author\n3.Change email of an author\n4.Exit");
		Integer choice=new Integer(scanner.nextLine());
		do{
			switch(choice){
				case 1:
					System.out.println("Enter the author name");
					String name=scanner.nextLine();
					System.out.println("Enter the email id");
					String email=scanner.nextLine();
					Character gender;
					do{
						System.out.println("Enter the gender : M for male and F for female");
						gender=new Character(scanner.next().charAt(0));
						scanner.nextLine();
					}while(!(gender.equals('F')||gender.equals('M')));
					author.setAuthor(name,email,gender);
					break;
				case 2:
					if(author.name!=null){
						System.out.println(author.toDisplay());
					}
					else{
						System.out.println("*******Construct author first******");
					}
					break;
				case 3:
					if(author.name!=null){
						System.out.println("Enter the email you want to be updated");
						String newEmail=scanner.nextLine();
						author.setEmail(newEmail); 
					}
					else{
						System.out.println("*****Construct author first*****\n");
					}
					break;
				case 4: break;
				default: System.out.println("Invalid choice");
			}
			System.out.println("1.Construct an author\n2.Print author\n3.Change email of an author\n4.Exit");
			choice=scanner.nextInt();
			scanner.nextLine();
		}while(choice!=4);
	}
}

