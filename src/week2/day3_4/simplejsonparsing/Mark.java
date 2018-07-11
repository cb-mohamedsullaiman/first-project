/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4.simplejsonparsing;

/**
 *
 * @author cb-mohamedsullaiman
 */
public class Mark {
    private Integer marks;
    private String subject;
    
    
    public Mark(Integer marks, String subject){
        this.marks=marks;
        this.subject=subject;
    }
    public void setMarks(Integer marks){
        this.marks=marks;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public Integer getMarks(){
        return marks;
    }
    public String getSubject(){
        return subject;
    }
}
