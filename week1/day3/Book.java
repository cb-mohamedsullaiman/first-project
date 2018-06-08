package classbasics;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
class Author{
	String name;
	String email;
	char gender;
	public Author(String name,String email,char gender){			//Constructor with 2 parameters
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
		System.out.println("Enter the author name");
		String name=scanner.nextLine();
		System.out.println("Enter the email id");
		String email=scanner.nextLine();
		System.out.println("Enter the gender /M for male and F for female");
		char gender=scanner.next().charAt(0);
		Author author=new Author(name,email,gender);
		author.toDisplay();
		System.out.println("Enter the email you want to be updated");
		String newEmail=scanner.nextLine();
		author.setEmail(newEmail); 
		author.toDisplay();
	}
}
class Book{
	private String name;
	private Author[] authors;
	private Double price;
	private int qtyInStock=0;
	public Book(String name,Author[] authors,double price,int qtyInStock){		//Constructor with 4 parameters
		this.name=name;
		this.authors=authors;
		this.price=price;
		this.qtyInStock=qtyInStock;
	}
	public Book(String name,Author author,double price,int qtyInStock){		//Constructor with 4 parameter but different from the previous one with type
		this.name=name;
		this.authors[0]=author;
		this.price=price;
		this.qtyInStock=qtyInStock;
	}
	public String getName(){							//Method to return the name of the book
		return name;
	}
	public Author[] getAuthors(){							//Method to return the authors of the book
		return authors;
	}
	public double getPrice(){							//Method to return the price of the book
		return price;
	}
	public void setPrice(double price){						//Method to set the price of the book
		this.price=price;
	}
	public int getQtyInStock(){							//Method to get the number of books available in stock
		return qtyInStock;
	}
	public void setQtyInStock(int qtyInStock){					//Method to set the number of books available in stock
		this.qtyInStock=qtyInStock;
	}
	public String toDisplay(){							//Method to print the book details
		StringBuilder bookdetails=new StringBuilder();
		bookdetails.append("["+name+"]");
		bookdetails.append(" by ");
		for(int i=0;i<authors.length;i++){
			bookdetails.append(authors[i].name+" ");
			bookdetails.append("("+authors[i].gender+")");
			bookdetails.append(" at "+authors[i].email);
			if(i!=authors.length-1){
				bookdetails.append(", ");
			}
		}
		bookdetails.append("\nPrice : $"+price);
		bookdetails.append("\nNo of books available : "+qtyInStock);
		return bookdetails.toString();
	}
	public void printAuthors(){							//Method to print the authors of the book
		for(int i=0;i<authors.length;i++){
			System.out.println(authors[i].toDisplay());
		}
	}
	public void addAuthor(Author author){						//Method to add author for a particular book
		ArrayList<Author> auth=new ArrayList<Author>(Arrays.asList(authors));
		auth.add(author);
		Author[] tempAuthor=new Author[auth.size()];
		tempAuthor=auth.toArray(tempAuthor);
		authors=tempAuthor;
	}
	public static void main(String args[]){						//main for Book
		Scanner scanner=new Scanner(System.in);
		ArrayList<Book> books=new ArrayList<Book>();				//Creating a list of books
		boolean choice;
		do{
			System.out.println("Enter the name of the book");
			String name=scanner.nextLine();					//Getting the name of the book
			System.out.println("Enter the number of authors");
			int numOfAuthors=scanner.nextInt();				//Getting the number of authors for the book
			scanner.nextLine();
			ArrayList<Author> auth=new ArrayList<Author>();;
			for(int i=0;i<numOfAuthors;i++){
				System.out.println("Author "+i);
				System.out.println("Enter the name of the author");
				String authorName=scanner.nextLine();
				System.out.println("Enter the email id");
				String authorEmail=scanner.nextLine();
				System.out.println("Enter the gender of the author");
				char authorGender=scanner.next().charAt(0);
				scanner.nextLine();
				auth.add(new Author(authorName,authorEmail,authorGender)); //Adding author to the author list
			}
			System.out.println("Enter the price for the book");			
			double bookPrice=scanner.nextInt();				//Getting the price of the book
			System.out.println("Enter the quantity in stock");
			int quantityInStock=scanner.nextInt();				//Getting the quantiy of book in stock
			Author[] authorr=new Author[auth.size()];
			authorr=auth.toArray(authorr);
			books.add(new Book(name,authorr,bookPrice,quantityInStock));		//Adding current book to the book list
			System.out.println("Do you want to add another book? \n If so type true if not type false");
			choice=scanner.nextBoolean();					//Getting the user input
			scanner.nextLine();
		}while(choice==true);
		System.out.println("Enter the name of the book you want to add author");	
		String bookName=scanner.nextLine();				//Getting the name of the book for which you want to add author
		int flag=0;
		for(int i=0;i<books.size();i++){
			if(books.get(i).name.equals(bookName)){
				System.out.println("Enter the name of the author");
				String authName=scanner.nextLine();
				System.out.println("Enter the email id");
				String authEmail=scanner.nextLine();
				System.out.println("Enter the gender ( M for male and F for female)");
				char authGender=scanner.next().charAt(0);
				scanner.nextLine();
				Author auth= new Author(authName,authEmail,authGender);
				books.get(i).addAuthor(auth);
				flag=1;
			}
		}
		if(flag==0){						//This block will executed if no such books are available
			System.out.println("No books found");
		}
		System.out.println("Enter the book name for which you want to print authors");
		bookName=scanner.nextLine();				//Enter the name of the book for which you want to print authors
		flag=0;
		for(int i=0;i<books.size();i++){
			if(books.get(i).name.equals(bookName)){
				books.get(i).printAuthors();
				flag=1;
			}
		}
		if(flag==0){						//This block will get executed if no such books are available
			System.out.println("No book found");
		}
		for(int i=0;i<books.size();i++){			//Printing all the book details in a specified format
			System.out.println(books.get(i).toDisplay());
		}
	}
} 
			
 
