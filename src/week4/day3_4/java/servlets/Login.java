package portal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

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
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            resultSet = DAOImplementation.select("* from phone_directory_portal.users where email = ?", userName);
            if (resultSet.next()) {
                resultSet = DAOImplementation.select("* from phone_directory_portal.users where email = ? and password = ?",userName,password);
                if(resultSet.next()){
                    request.getSession().setAttribute("email",userName);
                    request.getRequestDispatcher("welcome.jsp").forward(request,response);
                }
                else{
                    request.setAttribute("error","*password incorrect");
                    request.getRequestDispatcher("index.jsp").forward(request,response);
                }
            } else {
                request.setAttribute("error", "*user not found");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public ResultSet validateUser(String userName) throws SQLException {
        return DAOImplementation.select("* from users where email = " + userName);
    }
}
