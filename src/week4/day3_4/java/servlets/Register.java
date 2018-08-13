package portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {

    @Override
    public void init() {
        try {
            if (ConnectionPool.getAvailableConnectionsCount() == 0 && ConnectionPool.getUsedConnectionsCount() == 0) {
                System.out.println("Initialization in servlet");

                ConnectionPool.setUpConnectionPool("jdbc:mysql://localhost/service_portal", "root", "");
                System.out.println("After setting up the pool in servlet");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        ResultSet resultSet = null;
        try (PrintWriter out = response.getWriter()) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String confirmationEmail = request.getParameter("confirmEmail");
            String password = request.getParameter("registrationPassword");
            String confirmationPassword = request.getParameter("confirmRegistrationPassword");
            System.out.println(firstName+"\t"+lastName+"\t"+email+"\t"+confirmationEmail+"\t"+password+"\t"+confirmationPassword);
            
            if(!validateFirstName(firstName)){
                request.setAttribute("firstNameError","firstName cannot be empty");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            else if(!validateLastName(lastName)){
                request.setAttribute("lastNameError","Last name cannot be empty");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            else if(!validateEmail(email)){
                request.setAttribute("email","invalid email id");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            else if(!checkEmailAvailability(email)){
                request.setAttribute("email","*email already used");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            
            else if(!confirmEmail(email,confirmationEmail)){
                request.setAttribute("confirmEmail", "email must match");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            else if(!validatePassword(password)){
                request.setAttribute("password", "password must contain atleast one digit, one lower case and one upper case letter");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            else if(!confirmPassword(password, confirmationPassword)){
                request.setAttribute("confirmPassword", "passwords must match");
                request.getRequestDispatcher("index.jsp").include(request, response);
            }
            else{
               Integer rowsAffected = DAOImplementation.insert("into phone_directory_portal.users(firstName,lastName,email,password) values(?,?,?,?)", firstName, lastName, email, password);
               if(rowsAffected ==0){
                   request.setAttribute("registrationError","registration unsuccessful");
                   request.getRequestDispatcher("index.jsp").include(request, response);
               }
               else{
                   request.setAttribute("error", "Registered successfully..Try logging in" );
                   request.getRequestDispatcher("index.jsp").include(request, response);
               }
            }
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public Boolean validateFirstName(String firstName) {
        return firstName!=null;
    }
    public Boolean validateLastName(String lastName){
        return lastName!=null;
    }
    public Boolean validateEmail(String email){
        if(email!=null){
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
        }
        return false;
    }
    public Boolean confirmEmail(String email, String confirmationEmail){
        if(confirmationEmail!=null){
        return email.equals(confirmationEmail);
        }
        return false;
    }
    public Boolean validatePassword(String password){
        if(password!=null){
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.find();
        }
        return false;
    }
    public Boolean confirmPassword(String password, String confirmationPassword){
        if(confirmationPassword!=null){
        return password.equals(confirmationPassword);
        }
        return false;
    }
    public Boolean checkEmailAvailability(String email) throws SQLException{
        ResultSet resultSet = DAOImplementation.select("* from phone_directory_portal.users where email = ?", email);
        return !resultSet.next();
    }
}
