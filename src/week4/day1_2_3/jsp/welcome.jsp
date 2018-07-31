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
            td{
                margin: 0 15px;
            }
            table{
                margin: auto; 
            }
            td.details{
                padding-left: 30px;
                font-size: 18pt;
                color: #666666
            }
            td{
                padding-top: 15pt;
            }
            #edit{
                background-color: #f44336;
                border-radius: 5px;
                padding: 10px;
                font-size: 12pt;
                color: white;               
            }
            div.button{
                display: flex;
                justify-content: center;
                padding-top: 20px;
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
            Welcome to the Space Portal
        </div>
        <table>
            <tr>
                <td class="header"> NAME</td>
                
                <td class="details">
                    <%
                        out.println(firstName+" "+lastName);
                    %>
                </td>
               
            </tr>
            <tr>
                <td class="header">
                    EMAIL
                </td>
                <td class = "details">
                    <% out.println(email); %>
                </td>
               
            </tr>
            <tr>
                <td class="header">
                    ADDRESS 
                </td>
                <td class = "details">
                    --NA--
                </td>
            </tr>
        </table> 
    <div class="button">
                <button id="edit" onclick="window.location.href='edit_details.jsp'">Edit Details</button>
    </div>
    </body>
</html>