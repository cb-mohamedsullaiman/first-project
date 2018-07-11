package week2.day1_2.phonedirectory;


public class PhoneNumberDetails  {

    private Long phoneNumber;
    private TypeOfUsage typeOfUsage;
    public enum TypeOfUsage{
        MOBILE ,WORK, HOME
    };
    


    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setType(TypeOfUsage typeOfUsage) {
        this.typeOfUsage = typeOfUsage;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public TypeOfUsage getTypeOfUsage() {
        return typeOfUsage;
    }

    public void printPhoneNumber() {
        System.out.println(phoneNumber + "\t-" + typeOfUsage);
    }
}
