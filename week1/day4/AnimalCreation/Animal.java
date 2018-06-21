package oopsjava;

public abstract class Animal implements oopsjava.AnimalProperty{
	private String name;
	private Boolean isHerbivore;
	private String type;  
	private Boolean canFly;
	private Boolean canRun;
	private Boolean canWalk;
	public Animal(){
		this.isHerbivore=false;
		this.canFly=false;
		this.canRun=true;
		this.canWalk=true;
	}
	public Boolean isHerbivorous(){
		return isHerbivore;
	}
	public Boolean isFlyable(){
		return canFly;
	}
	public Boolean isRunnable(){
		return canRun;
	}
	public Boolean isWalkable(){
		return canWalk;
	}
	public String getName(){
		return name;
	}
	public String getType(){
		return type;
	}
	public void getMovement(){
		System.out.println("Is it flyable?\t: "+isFlyable());
		System.out.println("Is it runnable?\t: "+isRunnable());
		System.out.println("Is it walkable?\t: "+isWalkable());
	}
	public void setName(String name){
		this.name=name;
	}
	public void setType(String type){
		this.type=type;
	}
	public void setFlyable(Boolean canFly){
		this.canFly=canFly;
	}
	public void setHerbivorous(Boolean isHerbivore){
		this.isHerbivore=isHerbivore;
	}
	public void setRunnable(Boolean canRun){
		this.canRun=canRun;
	}
	public void setWalkable(Boolean canWalk){
		this.canWalk=canWalk;
	}
	// Do I really need this?
	abstract public void printAdditionalInfo();
	
}