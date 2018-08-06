package portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveDetails extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        ResultSet resultSet = null;
        try (PrintWriter out = response.getWriter()) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String addressLine1 = request.getParameter("addressLine1");
            String addressLine2 = request.getParameter("addressLine2");
            String city = request.getParameter("city");
            String zip = request.getParameter("zip");
            String state = null;
            resultSet = DAOImplementation.select("stateName from states where stateValue = ?",request.getParameter("state"));
            if(resultSet.next()){
            state = resultSet.getString(1);
            }
            String country = null;
            resultSet = DAOImplementation.select("countryName from countries where countryValue = ?",request.getParameter("country"));
            if(resultSet.next()){
            country = resultSet.getString(1);
            }
            Integer rowsAffected = DAOImplementation.update(" users set firstName = ?, lastName = ?, addressLine1 = ?, addressLine2 = ?, city = ?, zip = ?, state = ?, country = ? where email = ?;",firstName,lastName,addressLine1,addressLine2,city,zip,state,country,email);
            if(rowsAffected!=0){
                request.getRequestDispatcher("welcome.jsp").forward(request, response);
            }
            else{
                request.setAttribute("mysqlerror", "not successful");
                request.getRequestDispatcher("edit_details.jsp").forward(request,response);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            request.setAttribute("mysqlerror", "not successful");
            request.getRequestDispatcher("edit_details.jsp").forward(request,response);
        }
    }
}
