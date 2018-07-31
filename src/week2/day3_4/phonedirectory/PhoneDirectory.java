/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4.phonedirectory;

/**
 *
 * @author cb-mohamedsullaiman
 */
public interface PhoneDirectory extends Readable{

    public Boolean retrievePersonByName(String name);

    public Boolean retrievePersonByPartialName(String name);
    
    public Boolean retrievePersonByPhoneNumber(Long phoneNumber);
}
