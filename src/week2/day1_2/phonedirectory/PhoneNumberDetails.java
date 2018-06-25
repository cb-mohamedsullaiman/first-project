package week2.day1_2.phonedirectory;

import java.util.Comparator;
import java.util.Objects;


public class PhoneNumberDetails implements Comparator {

    private Long phoneNumber;
    private Type type;
    public enum Type{
        MOBILE ,WORK, HOME
    };
    

    @Override
    public int compare(Object numberDetail1, Object numberDetail2) {
        try{
            if(!(numberDetail1 instanceof PhoneNumberDetails && numberDetail2 instanceof PhoneNumberDetails)){
                throw new ClassCastException();
            }
            PhoneNumberDetails givenNumberDetails1 = (PhoneNumberDetails) numberDetail1;
            PhoneNumberDetails givenNumberDetails2 = (PhoneNumberDetails) numberDetail2;
            if(givenNumberDetails1.equals(givenNumberDetails2)){
                return 0;
            }
            else if(givenNumberDetails1.getPhoneNumber()<givenNumberDetails2.getPhoneNumber()){
                return -1;
            }
            else{
                return 1;
            }
        }
        catch(ClassCastException e){
            System.out.println("Object is not of type phone number");
        }
        return 0;
        
    }

    @Override
    public boolean equals(Object phoneNumber) {

        // Exit check
        if (!(phoneNumber instanceof PhoneNumberDetails)) {
            return false;
        }

        PhoneNumberDetails givenPhoneNumberObject = (PhoneNumberDetails) phoneNumber;

        return this.phoneNumber.equals(givenPhoneNumberObject.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.phoneNumber);
        return hash;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Type getType() {
        return type;
    }

    public void printPhoneNumber() {
        System.out.println(phoneNumber + "\t-" + type);
    }
}
