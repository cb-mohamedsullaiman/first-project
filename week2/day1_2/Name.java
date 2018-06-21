package collectionsjava;


public class Name{
	private String firstName;
	private String secondName;
	private String thirdName;
	public void setName(String name){
		String tempName[]=name.split(" ",3);
		switch(tempName.length){
			case 1: 
				firstName=name;
				break;
			case 2:
				firstName=tempName[0];
				secondName=tempName[1];
				break;
			case 3:
				firstName=tempName[0];
				secondName=tempName[1];
				thirdName=tempName[2];
				break;
			default:
				firstName=name;
		}
	}
	public String getName(){
		if(secondName==null){
			return firstName;
		}
		else if(thirdName==null){
			return firstName+" "+secondName;
		}
		else{
			return firstName+" "+secondName+" "+thirdName;
		}
	}
	
}