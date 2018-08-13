<%@page import= "java.sql.*"%>
<%@page import= "portal.DAOImplementation" %>

<%
    String name = request.getParameter("name"); 
    String partialName = request.getParameter("partialName");
    String phoneNumber = request.getParameter("phoneNumber");
    String userName = (String)session.getAttribute("email");

    String buffer="<table name='contacts'>";  
    ResultSet resultSet= null;
    if(name!=null &&  !name.equals("")){
        resultSet = DAOImplementation.select("* from phone_directory_portal.phone_directory where name='"+name+"' and userName = '"+userName+"'");  
    }
    else if(partialName!=null && !partialName.equals("")){
        resultSet = DAOImplementation.select("* from phone_directory_portal.phone_directory where name like '%"+partialName+"%' and userName='"+userName+"'");  
    }
    else if(phoneNumber!=null && !phoneNumber.equals("")){
       resultSet = DAOImplementation.select("* from phone_directory_portal.phone_directory where phoneNumber1 = '"+phoneNumber+"' or phoneNumber2 = '"+phoneNumber+"' or phoneNumber3 = '"+phoneNumber+"'");
    }
    else{
       resultSet = DAOImplementation.select("* from phone_directory_portal.phone_directory where userName ='"+userName+"'");
    }
    buffer+="<tr><td class='head' style='border-top-left-radius:5px;border-bottom-left-radius:5px'>NAME</td><td class='head'>ADDRESS</td><td class='head'>PHONE NUMBER</td><td class='head'></td><td class='head' ></td><td class='head'></td><td class='head' style='border-top-right-radius:5px;border-bottom-right-radius:5px'></td></tr>";
    while(resultSet.next()){
        String address[]=resultSet.getString("address").split("\\?",-1);
        buffer+="<tr><td>"+resultSet.getString("name")+"</td><td>";  
        buffer+=address[0];
        for(int i=1;i<address.length;i++){
            if(!address[i].equals("null")){
                buffer+=","+address[i];
            }
        }
        Integer id = resultSet.getInt("id");                    
        buffer+="</td><td>"+resultSet.getString("phoneNumber1")+"</td><td>"+resultSet.getString("phoneNumber2")+"</td><td>"+resultSet.getString("phoneNumber3")+"</td><td><a href='#' id='e-"+id+"' onclick='editContact(this.id)'>Edit</a></td><td><a href='#' id='d-"+id+"' onclick='deleteContact(this.id)'>Delete</a></td></tr>";
        
    }  
    buffer=buffer+"</table>";  

    response.getWriter().println(buffer); 

%>