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
public class Student {
    
    private Date dateOfJoining;
    private String id;
    private Mark marks[];
    private String name;
    private Standard standard;
    
    public Student(){
        this.marks= new Mark[5];
    }
    public void setDateOfJoining(Date dateOfJoining){
        this.dateOfJoining=dateOfJoining;
    }
    public void setId(String id){
        this.id=id;
    }
    public void addMark(Mark mark,Integer index){
        this.marks[index]=mark;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setStandard(Standard standard){
        this.standard = standard;
    }
    public Date getDateOfJoining(){
        return dateOfJoining;
    }
    public String getId(){
        return id;
    }
    public Mark[] getMarks(){
        return marks;
    }
    public String getName(){
        return name;
    }
    public Standard getStandard(){
        return standard;
    }
}
