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
                background-color: #f44336;
                border-radius: 5px;
                padding:10px;   
                font-size: 12pt;
            }
        </style>
        <script type="text/javascript">
            function loadStates(country) {
                var xmlhttp;
                var url = "states.jsp";
                url += "?countr=" + country;
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
            function emptyField(element) {
                if (element.defaultValue === element.value) {
                    element.value = "";
                }
            }
            function setDefaultValue(element) {
                if (element.value === "") {
                    element.value = element.defaultValue;
                }
            }
        </script>            
    </head>
    <body>
        <%
                String firstName = null;
                String lastName = null;
                String email = null;
                String addressLine1 = null;
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
            Edit Details      
        </div>
        <form class="container" method="POST" action="/mySelfServicePortal/save">          
            <table>
                <tr>
                    <td class="header">
                        NAME
                    </td>
                    <td>
                        <input type="text" name="firstName" value="<%=firstName%>"/>
                        <input type="text" name="lastName" value="<%=lastName%>"/>           
                    </td>
                </tr>
                <tr>
                    <td class="header">
                        EMAIL
                    </td>
                    <td>
                        <input type="text" id="email" class="full-width" name="email" value="<%=email%>" readonly/>
                    </td>
                </tr>
                <tr>
                    <td class="header">
                        ADDRESS
                    </td>
                    <%
                    if(addressLine1==null){
                        out.println("<td id='first-address-line'><input type='text' class='full-width'   name='addressLine1' value='Address Line 1' onfocus='emptyField(this)' onblur='setDefaultValue(this)'/></td>");
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
                    out.println("<td class='empty'><input type='text' class='full-width'   name='addressLine2' value='Address Line 2' onfocus='emptyField(this)' onblur='setDefaultValue(this)'/></td>");
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
                        out.println("<input type='text' value='City' id='city' name='city' onfocus='emptyField(this)' onblur='setDefaultValue(this)'/>");
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
                        <input type="text" value="ZIP" name="zip" id="zip" onfocus="emptyField(this)" onblur="setDefaultValue(this)"/>
                        <select name="country" onchange="loadStates(this.value)" >
                            <option value="-1">Country</option> 
                            <%BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.home")+"/myTomcatProject/tomcat/webapps/mySelfServicePortal/countries.txt"));
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
                    <td ></td>
                    <td>
                        <button type="submit">Save Details</button>
                        <a href="http://localhost:8080/mySelfServicePortal/welcome.jsp">Discard</a>
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