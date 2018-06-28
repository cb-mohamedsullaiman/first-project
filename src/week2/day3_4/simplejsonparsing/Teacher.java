/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4.simplejsonparsing;

import week2.day3_4.simplejsonparsing.Standard;
import java.util.Date;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class Teacher {
    private Standard[] classesTakingCareOf;
    private Date dateOfJoining;
    private String id;
    private String name;
    private float salary;
    
    public Teacher(){
        classesTakingCareOf = new Standard[3];
    }
    public void addClassesToTakeCareOf(Standard standard,Integer index){
        this.classesTakingCareOf[index] = standard;
        
    }
    public void setDateOfJoining(Date dateOfJoining){
        this.dateOfJoining = dateOfJoining;
    }
    public void setId(String id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setSalary(Float salary){
        this.salary=salary;
    }
    public Standard[] getClassesTakingCareOf(){
        return classesTakingCareOf;
    }
    public Date getDateOfJoining(){
        return dateOfJoining;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public Float getSalary(){
        return salary;
    }
}
