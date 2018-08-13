<%@page import = "java.sql.*" %>
<%@page import = "portal.DAOImplementation" %>
<%
if(session.getAttribute("email")==null){
response.sendRedirect("index.jsp");
}
%>

<html>
    <head>
        <style>
            @import "default.css";
            .first-line{
                padding-top: 20px;
                padding-bottom: 20px;      

            }
            .search{
                display: inline;
                padding-left: 10px;
            }
            input[type="text"]{
                border-radius: 5px;
                width: auto;
                height: 4%;
                color: #999999;
                font-size: 11pt;
            }
            button{
                width: auto;
                height : 4%;
                border-radius: 5px;
                color: white;
                background-color: #6666ff;
                margin-left: 50%;
                font-size: 11pt;
            }
            table{
                margin:0 3%;
                position: absolute;
                width: 95%;
                border-spacing: 0;
            }
            tr:nth-child(even){
                background-color: #cccccc;
                border-collapse: collapse;
                
            }
            td{
                padding: 15px;
            }
            td.head{
                background-color: #6666ff;
                color: #ffffff;
                padding: 10px;
            }
        </style>
        <script>
            function searchByName(name) {
                var xmlhttp;
                var url = "search.jsp";
                url += "?name=" + name;
                if (window.XMLHttpRequest) {
                    xmlhttp = new XMLHttpRequest();
                }
                else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function () {

                    if (xmlhttp.readyState === 4)
                    {
                        document.getElementById("contacts").innerHTML = xmlhttp.responseText;
                    }
                };
                xmlhttp.open("GET", url, true);
                xmlhttp.send();
            }
            function searchByPartialName(partialName) {
                var xmlhttp;
                var url = "search.jsp";
                url += "?partialName=" + partialName;
                if (window.XMLHttpRequest) {
                    xmlhttp = new XMLHttpRequest();
                }
                else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function () {

                    if (xmlhttp.readyState === 4)
                    {
                        document.getElementById("contacts").innerHTML = xmlhttp.responseText;
                    }
                };
                xmlhttp.open("GET", url, true);
                xmlhttp.send();
            }
            function searchByPhoneNumber(phoneNumber) {
                var xmlhttp;
                var url = "search.jsp";
                url += "?phoneNumber=" + phoneNumber;
                if (window.XMLHttpRequest) {
                    xmlhttp = new XMLHttpRequest();
                }
                else {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function () {
                    if (xmlhttp.readyState === 4)
                    {
                        document.getElementById("contacts").innerHTML = xmlhttp.responseText;
                    }
                };
                xmlhttp.open("GET", url, true);
                xmlhttp.send();
            }
            function deleteContact(id){
                var canDelete = false;
                if(window.confirm("You want to delete the contact?")){
                   window.location.href = "/myPhoneDirectory/delete?id="+id;
                }
                
            }
        </script>
    </head>
    <body>
        <% String firstName= null;
        ResultSet resultSet = DAOImplementation.select("firstName from phone_directory_portal.users where email = ?",session.getAttribute("email")); 
        if(resultSet.next()){
            firstName = resultSet.getString("firstName");
        } 
        %>
        <%@include file="phone_directory_top_bar.jspf" %>
        <div class="first-line">
            <div class="search">
                <input  type="text" placeholder="Search by name" oninput="searchByName(this.value)"/>
            </div>
            <div class="search">
                <input  type="text" placeholder="Search by partial name" oninput="searchByPartialName(this.value)"/>
            </div>
            <div class="search" >
                <input  type="text" placeholder="Search by phone number" oninput="searchByPhoneNumber(this.value)"/>
            </div>
            <button  type="button" onclick="location.href = 'add_contact.jsp'">Add Person</button>
        </div>
        <div id="contacts">
            <% 
                out.println("<table name='contacts' ><tr><td class='head' style='border-top-left-radius:5px;border-bottom-left-radius:5px'>NAME</td><td class='head'>ADDRESS</td><td class='head'>PHONE NUMBER</td><td class='head'></td><td class='head' ></td><td class='head'></td><td class='head' style='border-top-right-radius:5px;border-bottom-right-radius:5px'></td></tr>");
                resultSet = DAOImplementation.select("* from phone_directory_portal.phone_directory where userName = '"+session.getAttribute("email")+"'");
                while(resultSet.next()){
                    String address[]=resultSet.getString("address").split("\\?",-1);
                    out.println("<tr><td>"+resultSet.getString("name")+"</td><td>");
                    out.println(address[0]);                            
                    for(int i=1;i<address.length;i++){
                        if(!address[i].equals("null")){
                            out.println(","+address[i]);                                                                             
                        }
                    }
                    Integer id = resultSet.getInt("id");
                    out.println("</td><td>"+resultSet.getString("phoneNumber1")+"</td><td>"+resultSet.getString("phoneNumber2")+"</td><td>"+resultSet.getString("phoneNumber3")+"</td><td><a href='edit_contact.jsp?id="+id+"' >Edit</a></td><td><a href='javascript:deleteContact("+id+")'>Delete</a></td></tr>");
                }
                out.println("</table>");
            %>
        </div>
    </body>
</html>