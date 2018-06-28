/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week2.day3_4.simplejsonparsing;

import java.io.BufferedReader;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
/**
 *
 * @author cb-mohamedsullaiman
 */
public class SimpleJSONParsing {

    public static void main(String args[]) {
        try {
            Path path = Paths.get("/Users/cb-mohamedsullaiman/Downloads/students-teachers.json");
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            
            JSONObject root = new JSONObject(new JSONTokener(bufferedReader));
            
            JSONObject jsonStudent = root.getJSONObject("Student");
            
            Student student = new Student();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY");
            Date studentDateOfJoining = dateFormat.parse(jsonStudent.getString("Date Of Joining"));
            
            student.setDateOfJoining(studentDateOfJoining);
            
            String studentId = jsonStudent.getString("ID");
            student.setId(studentId);
            
            JSONArray markDetails = jsonStudent.getJSONArray("Marks");
            for(int i=0;i<markDetails.length();i++){
                JSONObject markDetail = markDetails.getJSONObject(i);
                Integer marks = markDetail.getInt("Mark");
                String subject = markDetail.getString("Subject");
                Mark mark = new Mark(marks,subject);
                student.addMark(mark,i);
            }
            
            String name = jsonStudent.getString("Name");
            student.setName(name);
            
            Standard standard = Standard.valueOf(jsonStudent.getString("Std"));
            student.setStandard(standard);
            
            JSONObject jsonTeacher = root.getJSONObject("Teacher");
            Teacher teacher = new Teacher();
            
            JSONArray jsonClassesTakingCareOf = jsonTeacher.getJSONArray("Classes Taking Care Of");
            for(int i=0;i<jsonClassesTakingCareOf.length();i++){
                Standard classTakingCareOf = Standard.valueOf(jsonClassesTakingCareOf.getString(i));
                teacher.addClassesToTakeCareOf(classTakingCareOf,i);
            }
            
            Date teacherDateOfJoining = dateFormat.parse(jsonTeacher.getString("Date Of Joining"));
            teacher.setDateOfJoining(teacherDateOfJoining);
            
            String teacherId = jsonTeacher.getString("ID");
            teacher.setId(teacherId);
            
            String teacherName = jsonTeacher.getString("Name");
            teacher.setName(teacherName);
            
            Float salary = jsonTeacher.getFloat("Salary");
            teacher.setSalary(salary);
            
            System.out.println("********Student instance details*******");
            System.out.println(student.getName());
            System.out.println(student.getId());
            System.out.println(student.getStandard());
            System.out.println(student.getDateOfJoining());
            for (Mark mark : student.getMarks()) {
                System.out.println(mark.getSubject());
                System.out.println(mark.getMarks());   
            }
            
            System.out.println("\n********Teacher instance details*******");
            System.out.println(teacher.getName());
            System.out.println(teacher.getId());
            for(Standard classTakingCareOf: teacher.getClassesTakingCareOf()){
                System.out.println(classTakingCareOf);
            }
            System.out.println(teacher.getDateOfJoining());
            System.out.println(teacher.getSalary());
        }
        catch(IOException ioException){
            System.out.println("IO Exception");
        } 
        catch (ParseException parseException) {
            System.out.println("Parse exception");
        }
    }
}
