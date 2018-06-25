package week1.day4.servicestation;

public class Person{
	private String name;									//Members can be private if we dont want the member to be accessed outside of the class
	private Integer age;
	private String address;
	private Long contactNumber;
	public Person(){										//Constructor with zero arguments	
	}
	public Person(String name,int age,String address,long contactNumber){	//Constructor with 4 arguments
		this.name=name;
		this.age=age;
		this.address=address;
		this.contactNumber=contactNumber;
	}
	public void setName(String name){						//Method for setting name
		this.name=name;
	}
	public void setAge(int age){							//Method for setting age
		this.age=age;
	}
	public void setAddress(String address){					//Method for setting address
		this.address=address;
	}
	public void setContactNumber(long contactNumber){		//Method for setting contact number
		this.contactNumber=contactNumber;
	}
	public String getName(){								//Method for getting name
		return name;
	}
	public Integer getAge(){									//Method for getting age
		return age;
	}
	public String getAddress(){								//Method for getting address
		return address;
	}
	public Long getContactNumber(){							//Method for getting contact number
		return contactNumber;
	}
	public void printDetails(){								//Method for printing person details
		System.out.println("Name\t\t: "+name);
		System.out.println("Age\t\t: "+age);
		System.out.println("Address\t\t: "+address);
		System.out.println("Contact Number\t: "+contactNumber);
	}
}