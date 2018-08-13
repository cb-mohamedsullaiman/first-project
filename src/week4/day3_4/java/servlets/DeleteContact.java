package portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**Deleting the customer based on the id */

public class DeleteContact extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        ResultSet resultSet = null;
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            Integer rowsAffected = DAOImplementation.delete(" from phone_directory_portal.phone_directory where id =  ?",id);
            
            if(rowsAffected!=0){
                response.sendRedirect("my_phone_directory.jsp");
            }
            else{
                request.setAttribute("mysqlerror", "not successful");
                request.getRequestDispatcher("my_phone_directory.jsp").forward(request,response);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            request.setAttribute("mysqlerror", "not successful");
            request.getRequestDispatcher("my_phone_directory.jsp").forward(request,response);
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}
