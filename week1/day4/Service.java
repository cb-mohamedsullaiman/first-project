package oopsjava;

public class Service{
	private String serviceName;
	private Float serviceCharge;
	public Service(){
		serviceName=null;
		serviceCharge=(float)0.0;
	}

	public Service(String serviceName,float serviceCharge){
		this.serviceName=serviceName;
		this.serviceCharge=serviceCharge;
	}
	public void setServiceName(String serviceName){
		this.serviceName=serviceName;
	}
	public void setServiceCharge(float serviceCharge){
		this.serviceCharge=serviceCharge;	
	}
	public String getServiceName(){
		return serviceName;
	}
	public Float getServiceCharge(){
		return serviceCharge;
	}
	public void printServiceDetails(){
		System.out.println(serviceName+"\t\t\t"+serviceCharge);
	}
}