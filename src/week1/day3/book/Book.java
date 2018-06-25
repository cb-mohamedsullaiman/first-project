package week1.day3.book;
import week1.day3.book.Author;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Book{
	private String name;
	private ArrayList<Author> authors;
	private Double price;
	private Integer qtyInStock=0;
	public Book(String name,ArrayList<Author> authors,Double price,Integer qtyInStock){		//Constructor with 4 parameters
		this.name=name;
		this.authors=authors;
		this.price=price;
		this.qtyInStock=qtyInStock;
	}
	public Book(String name,Author author,Double price,Integer qtyInStock){		//Constructor with 4 parameter but different from the previous one with type
		this.name=name;
		this.authors=new ArrayList<Author>();
		this.authors.add(author);
		this.price=price;
		this.qtyInStock=qtyInStock;
	}
	public String getName(){							//Method to return the name of the book
		return name;
	}
	public ArrayList<Author> getAuthors(){							//Method to return the authors of the book
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
		for(int i=0;i<authors.size();i++){
			bookdetails.append(authors.get(i).getName()+" ");
			bookdetails.append("("+authors.get(i).getGender()+")");
			bookdetails.append(" at "+authors.get(i).getEmail());
			if(i!=authors.size()-1){
				bookdetails.append(", ");
			}
		}
		bookdetails.append("\nPrice : $"+price);
		bookdetails.append("\nNo of books available : "+qtyInStock);
		return bookdetails.toString();
	}
	public void printAuthors(){							//Method to print the authors of the book
		for(int i=0;i<authors.size();i++){
			System.out.println(authors.get(i).toDisplay());
		}
	}
	public void addAuthor(Author author){
		this.authors.add(author);
	}
	public static void main(String args[]){						//main for Book
		Scanner scanner=new Scanner(System.in);
		ArrayList<Book> books=new ArrayList<Book>();				//Creating a list of books
		System.out.println("1.Add a book\n2.Add an author for a particular book\n3.Display the authors for a particular book\n4.Print the details for the books availabale\n5.Exit");
		Integer choice=scanner.nextInt();
		scanner.nextLine();
		do{
			int flag=0;
			switch(choice){
				case 1:
					System.out.println("Enter the name of the book");
					String name=scanner.nextLine();					//Getting the name of the book
					System.out.println("Enter the number of authors");
					Integer numOfAuthors=new Integer(scanner.nextInt());				//Getting the number of authors for the book
					scanner.nextLine();
					if(numOfAuthors==1)
					{
						Author authors=new Author();
						System.out.println("Enter the name of the author");
						String authorName=scanner.nextLine();
						System.out.println("Enter the email id");
						String authorEmail=scanner.nextLine();	
						Character authorGender;
						do{
							System.out.println("Enter the gender of the author(M for Male and F for Female)\nNo other characters are allowed");
							authorGender=new Character(scanner.next().charAt(0));
						}while(!(authorGender.equals('M')||authorGender.equals('F')));
						scanner.nextLine();
						authors.setAuthor(authorName,authorEmail,authorGender);
						System.out.println("Enter the price for the book");			
						Double bookPrice=new Double(scanner.nextDouble());				//Getting the price of the book
						System.out.println("Enter the quantity in stock");
						Integer quantityInStock=new Integer(scanner.nextInt());				//Getting the quantiy of book in stock
						books.add(new Book(name,authors,bookPrice,quantityInStock));
					}
					else{
						ArrayList<Author> authors=new ArrayList<Author>();;
						for(int i=0;i<numOfAuthors;i++){
							Author author=new Author();
							System.out.println("Author "+(i+1));
							System.out.println("Enter the name of the author");
							String authorName=scanner.nextLine();
							System.out.println("Enter the email id");
							String authorEmail=scanner.nextLine();	
							Character authorGender;
							do{
								System.out.println("Enter the gender of the author(M for Male and F for Female)\nNo other characters are allowed");
								authorGender=new Character(scanner.next().charAt(0));
							}while(!(authorGender.equals('M')||authorGender.equals('F')));
							scanner.nextLine();
							author.setAuthor(authorName,authorEmail,authorGender);
							authors.add(author); //Adding author to the author list
						}
						System.out.println("Enter the price for the book");			
						Double bookPrice=new Double(scanner.nextDouble());				//Getting the price of the book
						System.out.println("Enter the quantity in stock");
						Integer quantityInStock=new Integer(scanner.nextInt());				//Getting the quantiy of book in stock
						books.add(new Book(name,authors,bookPrice,quantityInStock));
					}
					break;
				case 2:
					System.out.println("Enter the name of the book you want to add author");	
					String bookName=scanner.nextLine();				//Getting the name of the book for which you want to add author
					flag=0;
					for(int i=0;i<books.size();i++){
						if(books.get(i).name.equals(bookName)){
							System.out.println("Enter the name of the author");
							String authorName=scanner.nextLine();
							System.out.println("Enter the email id");
							String authorEmail=scanner.nextLine();
							Character authorGender;
							do{
								System.out.println("Enter the gender ( M for male and F for female)\nOther characters are not allowed");
								authorGender=scanner.next().charAt(0);
							}while(!(authorGender.equals('M')||authorGender.equals('F')));
							scanner.nextLine();
							Author author= new Author();
							author.setAuthor(authorName,authorEmail,authorGender);
							books.get(i).addAuthor(author);
							flag=1;
						}
					}
					if(flag==0){						//This block will executed if no such books are available
						System.out.println("\n*****No books found*****");
					}
					break;
				case 3:
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
						System.out.println("\n*****No book found******");
					}
					break;
				case 4:
					if(books.size()>0){
						for(int i=0;i<books.size();i++){			//Printing all the book details in a specified format
							System.out.println(books.get(i).toDisplay());
						}
					}
					else{
						System.out.println("\n******No books available******");
					}
					break;
				case 5: 
					break;
			}
			System.out.println("1.Add a book\n2.Add an author for a particular book\n3.Display the authors for a particular book\n4.Print the details for the books availabale\n5.Exit");
			choice=scanner.nextInt();
			scanner.nextLine();
		}while(choice!=5);
	}
} 
			
 
