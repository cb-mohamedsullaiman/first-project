<%@ page import ="java.sql.*"%>
    <%
    String userid = request.getParameter("uname");    
    String pwd = request.getParameter("pass");
    ConnectionPool pool = new ConnectionPool();
    Connection connection = pool.getConnection();
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from member where uname='" + userid + "' and pass='" + pwd + "'");
    if (rs.next()) {
        session.setAttribute("userid", userid);
        //out.println("welcome " + userid);
        //out.println("<a href='logout.jsp'>Log out</a>");
        response.sendRedirect("success.jsp");
    } else {
        //out.println("Invalid password <a href='index.jsp'>try again</a>");
        request.setAttribute("errorMessage", "Invalid user or password");
        response.sendRedirect("index.jsp");
             }
%>
