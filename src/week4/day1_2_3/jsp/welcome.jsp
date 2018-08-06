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
            td.address{
                padding-top: 0px;
            }
        </style>
    </head>
    <body> 
        <%
                String firstName = null;
                String lastName = null;
                String email = null;
                String addressLine1= null;
                String addressLine2 = null;
                String city = null;
                Integer zip = 0;
                String state = null;
                String country = null;
                ResultSet resultSet = DAOImplementation.select("* from users where email = ?",session.getAttribute("email")); 
                if(resultSet.next()){
                    firstName = resultSet.getString("firstName");
                    lastName = resultSet.getString("lastName");
                    email = resultSet.getString("email");
                    addressLine1 = resultSet.getString("addressLine1");
                    addressLine2 = resultSet.getString("addressLine2");
                    city = resultSet.getString("city");
                    zip = resultSet.getInt("zip");
                    state = resultSet.getString("state");
                    country = resultSet.getString("country");
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
            <%
            if(addressLine1 ==null &&addressLine2==null &&city ==null&& zip == 0 &&state==null && country==null){
                out.println("<tr><td class='header'>ADDRESS </td><td class = 'details'>--NA--</td></tr>");
            }
            else{
                out.println("<tr><td class='header'>ADDRESS </td><td class = 'details'>");
                if(addressLine1!=null){
                    out.println(addressLine1+"</td></tr><tr><td class='header'><td class='details' style='padding-top:0px'>");
                }
                if(addressLine2!=null){
                    out.println(addressLine2+"</td></tr><tr><td class='header'><td class='details' style='padding-top:0px'>");
                }   
                if(city!=null){
                    out.println(city+"</td></tr><tr><td class='header'><td class='details' style='padding-top:0px'>");
                }
                if(zip!=null){
                    out.println(zip+"</td></tr><tr><td class='header'><td class='details' style='padding-top:0px'>");
                }
                if(state!=null){
                    out.println(state+"</td></tr><tr><td class='header'><td class='details' style='padding-top:0px'>");
                }
                if(country!=null){
                    out.println(country+"</td></tr>");
                }              
            }
            %>

        </table> 
        <div class="button">
            <button id="edit" onclick="window.location.href = 'edit_details.jsp'">Edit Details</button>
        </div>
    </body>
</html>