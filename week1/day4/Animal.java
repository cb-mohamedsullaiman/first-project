package oopsjava;

public abstract class Animal implements oopsjava.AnimalCharacter{
	private String name;
	private Boolean isHerbivore;
	private String type; 
	private Boolean canFly;
	public Animal(){
		this.isHerbivore=false;
		this.canFly=false;
	}
	public void isHerbivorous(){
		if(isHerbivore){
			System.out.println("It is herbivorous");
		}
		else{
			System.out.println("It is not herbivorous");
		}
	}
	public void canFly(){
		if(canFly){
			System.out.println("It can fly");
		}
		else{
			System.out.println("It cannot fly");
		}
	}
	public Boolean getCanFly(){
		return canFly;
	}
	public Boolean getIsHerbivore(){
		return isHerbivore;
	}
	public String getName(){
		return name;
	}
	public String getType(){
		return type;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setType(String type){
		this.type=type;
	}
	public void setCanFly(Boolean canFly){
		this.canFly=canFly;
	}
	public void setIsHerbivore(Boolean isHerbivore){
		this.isHerbivore=isHerbivore;
	}
	abstract public void hasHair();
	abstract public void printAdditionalInfo();
	
}