package portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaveContactDetails extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        ResultSet resultSet = null;
        System.out.println("out of try");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("Start of try");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String addressLine1 = request.getParameter("addressLine1");
            String addressLine2 = request.getParameter("addressLine2");
            String city = request.getParameter("city");
            System.out.println("After city");
            Integer zip = Integer.valueOf(request.getParameter("zip"));
            System.out.println(zip);
            Long phoneNumber1 = Long.valueOf(request.getParameter("phoneNumber1"));
            System.out.println(phoneNumber1);
            Long phoneNumber2 = Long.valueOf(request.getParameter("phoneNumber2"));
            System.out.println(phoneNumber2);
            Long phoneNumber3 = Long.valueOf(request.getParameter("phoneNumber3"));
            System.out.println(phoneNumber3);
            HttpSession session = request.getSession();
            String state = null;
            System.out.println("Before dao in contact");
            resultSet = DAOImplementation.select("stateName from phone_directory_portal.states where stateValue = ?",request.getParameter("state"));
            if(resultSet.next()){
            state = resultSet.getString(1);
            }
            String country = null;
            resultSet = DAOImplementation.select("countryName from phone_directory_portal.countries where countryValue = ?",request.getParameter("country"));
            if(resultSet.next()){
            country = resultSet.getString(1);
            }
            System.out.println("before dao for phone directory in contact");
            Integer rowsAffected = DAOImplementation.insert(" phone_directory_portal.phone_directory(name, address, phoneNumber1, phoneNumber2, phoneNumber3, userName) values ( ?, ?,  ?, ?,?, ?) ",firstName+" "+lastName,addressLine1+"?"+addressLine2+"?"+city+"?"+zip+"?"+state+"?"+country,phoneNumber1,phoneNumber2,phoneNumber3,session.getAttribute("email"));
            System.out.println(rowsAffected);
            if(rowsAffected!=0){
                response.sendRedirect("my_phone_directory.jsp");
            }
            else{
                request.setAttribute("mysqlerror", "not successful");
                request.getRequestDispatcher("edit_contact.jsp").forward(request,response);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            request.setAttribute("mysqlerror", "not successful");
            request.getRequestDispatcher("edit_contact.jsp").forward(request,response);
        }
    }
}
