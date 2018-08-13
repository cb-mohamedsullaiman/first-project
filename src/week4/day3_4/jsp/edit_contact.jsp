<%@page import = "portal.DAOImplementation" %>
<%@page import = "java.sql.*" %>
<%@page import = "java.nio.file.*" %>
<%@page import = "java.io.BufferedReader" %>
<%@page import = "java.io.FileReader" %>



<%
if(session.getAttribute("email")==null){
response.sendRedirect("index.jsp");
}
%>

<html>
    <head>
        <style> 
            @import "default.css";
            .full-width{
                width: 100%;
            }
            input[type="text"],select{
                border-radius: 5px;
                padding: 10px;
                border-style: solid;
                border-color: #999999;
                border-width: 1pt;
                font-size:14px;
                color: #85929E;
            }
            select{
                display: inline;
                width: 49%;                        
                -webkit-appearance: none;
                -moz-appearance: normal;
                appearance: normal;                          
                background: url("br_down.png") white no-repeat calc(100% - 10px) !important; 

            }
            tr{
                width: 70px;
            } 
            td{
                padding: 10px;
            }
            td.empty{
                padding-top: 2px;
                padding-bottom: 2px;
            }
            #email{
                background-color: #ECF0F1;
            }
            #first-address-line{
                padding-bottom: 0px;
            }
            button{
                color: white;
                width: 45%;
                padding: 10px 18px;
                background-color: #6666ff;
                border-radius: 5px;
                padding:10px;   
                font-size: 12pt;
            }
        </style>
        <script type="text/javascript">
            function loadStates(country) {
                var xmlhttp;
                var url = "states.jsp";
                url += "?country=" + country;
                if (window.XMLHttpRequest) {
                    xmlhttp = new XMLHttpRequest();
                }
                else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function () {

                    if (xmlhttp.readyState === 4)
                    {
                        document.getElementById("state").innerHTML = xmlhttp.responseText;
                    }
                };
                xmlhttp.open("GET", url, true);
                xmlhttp.send();
            }
        </script>            
    </head>
    <body>
        <%
                String contactFirstName = null;
                String contactLastName = null;
                String name[]=null;
                String address[]=null;
                String email = null;
                String addressLine1 = null;
                String addressLine2 = null;
                String city = null;
                String zip = null;
                String state = null;
                String country = null;
                Long phoneNumber1 = null;
                Long phoneNumber2 = null;
                Long phoneNumber3 = null;
                String firstName = null;
                String id = request.getParameter("id");
                ResultSet resultSet = DAOImplementation.select("* from phone_directory_portal.phone_directory where userName = ? and id = ?",session.getAttribute("email"),id); 
                if(resultSet.next()){
                    name = (resultSet.getString("name")).split("\\s",2);
                    address = (resultSet.getString("address")).split("\\?",6);
                    phoneNumber1 = resultSet.getLong("phoneNumber1");
                    phoneNumber2 = resultSet.getLong("phoneNumber2");
                    phoneNumber3 = resultSet.getLong("phoneNumber3");
                }
                if(name!=null){
                    contactFirstName = name[0];
                    contactLastName = name[1];
                }
                if(address!=null){
                    addressLine1 = address[0];
                    addressLine2 = address[1];
                    city = address[2];
                    zip = address[3];
                    state = address[4];
                    country = address[5];
                }
                resultSet = DAOImplementation.select("firstName from phone_directory_portal.users where email = ?",session.getAttribute("email"));
                if(resultSet.next()){
                    firstName = resultSet.getString("firstName");
                }
        %>
        <%@include file="phone_directory_top_bar.jspf" %>
        <div class="head-line">
            Edit Details      
        </div>
        <form class="container" method="POST" action="/myPhoneDirectory/update?id=<%= id %>">          
            <table>
                <tr>
                    <td class="header">
                        NAME
                    </td>
                    <td>
                        <%if(contactFirstName!=null){
                            out.println("<input type='text' name='firstName' value='"+contactFirstName+"'/>");
                        }
                        else{
                            out.println("<input type='text' name='firstName' placeholder='First name'/>");
                        }
                        if(contactLastName!=null){
                            out.println("<input type='text' name='lastName' value='"+contactLastName+"'/>");
                        }
                        else{
                            out.println("<input type='text' name='lastName' placeholder='Last name'/>");
                        }
                        %>        
                    </td>
                </tr>
                <tr>
                    <td class="header">
                        ADDRESS
                    </td>
                    <%
                    if(addressLine1==null){
                        out.println("<td id='first-address-line'><input type='text' class='full-width'  placeholder='Address Line 1' name='addressLine1'  /></td>");
                    }
                    else{
                        out.println("<td id='first-address-line'><input type='text' class='full-width'   name='addressLine1' value='"+addressLine1+"'/></td>");
                    }
                    %>
                </tr>
                <tr>
                    <td class="empty">

                    </td>
                    <%
                    if(addressLine2==null){
                    out.println("<td class='empty'><input type='text' class='full-width'   name='addressLine2' placeholder='Address Line 2' /></td>");
                    }
                    else{
                    out.println("<td class='empty'><input type='text' class='full-width'   name='addressLine2' value='"+addressLine2+"'/></td>");
                    }
                    %>
                </tr>
                <tr>
                    <td class="empty">

                    </td>
                    <td class="empty">
                        <%
                        if(city==null){
                        out.println("<input type='text' placeholder='City' id='city' name='city' />");
                        }
                        else{
                        out.println("<input type='text' id='city' name='city' value='"+city+"'/>");
                        }
                        %>
                        <div id="state" style="display:inline">
                            <select name="state">
                                <%
                                if(state!=null){
                                    out.println("<option>"+state+"</option>");
                                }  
                                else{
                                    out.println("<option>State</option>");
                                }
                                %>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="empty">

                    </td>
                    <td class="empty">
                        <%
                        if(zip==null){
                        out.println("<input type='text'  id='city' placeholder='Zip' name='zip' />");
                        }
                        else{
                        out.println("<input type='text' id='city' name='zip' id='zip' value='"+zip+"'/>");
                        }
                        %>
                        <select name="country" onchange="loadStates(this.value)" >
                            <option value="-1">Country</option> 
                            <%BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.home")+"/myTomcatProject/tomcat/webapps/myPhoneDirectory/countries.txt"));
                            String countries;
                            while((countries=bufferedReader.readLine())!=null){
                            String countryDetails[]=countries.split("\\s",2);
                            if(countryDetails[1].equals(country)){
                                out.println("<option value='"+countryDetails[0]+"' selected>"+countryDetails[1]+"</option>");  
                            }
                            else{
                                out.println("<option value='"+countryDetails[0]+"'>"+countryDetails[1]+"</option>");
                            }
                            }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="header">
                        Phone(WORK)
                    </td>
                    <%
                    if(phoneNumber1==null){
                        out.println("<td ><input type='text' class='full-width'  placeholder='Phone Number(WORK)' name='phoneNumber1'  /></td>");
                    }
                    else{
                        out.println("<td ><input type='text' class='full-width'   name='phoneNumber1' value='"+phoneNumber1+"'/></td>");
                    }
                    %>
                </tr>
                <tr>
                    <td class="header">
                        Phone(MOBILE)
                    </td>
                    <%
                    if(phoneNumber2==null){
                        out.println("<td><input type='text' class='full-width'  placeholder='Phone Number(MOBILE)' name='phoneNumber2'  /></td>");
                    }
                    else{
                        out.println("<td><input type='text' class='full-width'   name='phoneNumber2' value='"+phoneNumber2+"'/></td>");
                    }
                    %>
                </tr>
                <tr>
                    <td class="header">
                        Phone(HOME)
                    </td>
                    <%
                    if(phoneNumber3==null){
                        out.println("<td ><input type='text' class='full-width'  placeholder='PhoneNumber(HOME)' name='phoneNumber3'  /></td>");
                    }
                    else{
                        out.println("<td><input type='text' class='full-width'   name='phoneNumber3' value='"+phoneNumber3+"'/></td>");
                    }
                    %>
                </tr>
                <tr>
                    <td ></td>
                    <td>
                        <button type="submit">Save Details</button>
                        <a href="http://localhost:8080/myPhoneDirectory/my_phone_directory.jsp">Discard</a>
                    </td>
                </tr>
            </table>  
            <% 
                   String error = (String)request.getAttribute("mysqlerror");
                   if(error != null){
                   out.println("<font color=red size=4px>"+error+"</font>");
                   }
            %>
        </form>
    </body>
</html>