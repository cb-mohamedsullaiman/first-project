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
        <% String firstName= null;
        ResultSet resultSet = DAOImplementation.select("firstName from users where email = ?",session.getAttribute("email")); 
        if(resultSet.next()){
            firstName = resultSet.getString("firstName");
        } 
        %>
        <%@include file="phone_directory_top_bar.jspf" %>
        <div class="head-line">
            Add contact      
        </div>
        <form class='container' method="POST" action="/myPhoneDirectory/savecontact">
            <table>
                <tr>
                    <td class='header'>
                        NAME
                    </td>
                    <td>
                        <input type='text' name='firstName' placeholder='First name'/>

                        <input type='text' name='lastName' placeholder='Last name'/>
                    </td>
                </tr>
                <tr>
                    <td class="header">
                        ADDRESS
                    </td>

                    <td id='first-address-line'><input type='text' class='full-width'   name='addressLine1' placeholder='Address Line1'/></td>

                </tr>
                <tr>
                    <td class="empty">

                    </td>
                    <td class='empty'><input type='text' class='full-width'   name='addressLine2' placeholder='Address Line2'/></td>

                </tr>
                <tr>
                    <td class="empty">

                    </td>
                    <td class="empty">
                        <input type='text' id='city' name='city' placeholder='city'/>

                        <div id="state" style="display:inline">
                            <select name="state">

                                <option>State</option>

                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="empty">

                    </td>
                    <td class="empty">
                        <input type='text' id='city' name='zip' id='zip' placeholder='zip'/>

                        <select name="country" onchange="loadStates(this.value)" >
                            <option value="-1">Country</option> 
                            <%BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.home")+"/myTomcatProject/tomcat/webapps/myPhoneDirectory/countries.txt"));
                            String countries;
                            while((countries=bufferedReader.readLine())!=null){
                            String countryDetails[]=countries.split("\\s",2);
                            
                                out.println("<option value='"+countryDetails[0]+"'>"+countryDetails[1]+"</option>");
                           
                            }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="header">
                        Phone(WORK)
                    </td>
                    <td ><input type='text' class='full-width'   name='phoneNumber1' placeholder='Phone Number(Work)'/></td>

                </tr>
                <tr>
                    <td class="header">
                        Phone(MOBILE)
                    </td>
                    <td><input type='text' class='full-width'   name='phoneNumber2' placeholder='Phone Number(Mobile)'/></td>

                </tr>
                <tr>
                    <td class="header">
                        Phone(HOME)
                    </td>
                    <td><input type='text' class='full-width'   name='phoneNumber3' placeholder='Phone Number(Home)'/></td>

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