<%@page import = "portal.DAOImplementation" %>
<%@page import = "java.sql.*" %>
<%
if(session.getAttribute("email")==null){
response.sendRedirect("index.jsp");
}
%>

<html>
    <head>
        <style> 
            @import "default.css";
            .two_input{
                width: 100%;
            }
        </style>
    </head>
    <body>
        <%
                String firstName = null;
                String lastName = null;
                String email = null;
                ResultSet resultSet = DAOImplementation.select("* from users where email = ?",session.getAttribute("email")); 
                if(resultSet.next()){
                    firstName = resultSet.getString("firstName");
                    lastName = resultSet.getString("lastName");
                    email = resultSet.getString("email");
                }
        %>
        <%@include file="top_bar.jspf" %>
        <div class="head-line">
            Edit Details      
        </div>
        <form class="container">
            <table>
                <tr>
                    <td class="header">
                        NAME
                    </td>
                    <td>
                        <input type="text" />
                        <input type="text" />           
                    </td>
                </tr>
                <tr>
                    <td class="header">
                        EMAIL
                    </td>
                    <td>
                        <input type="text" class="two_input"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>