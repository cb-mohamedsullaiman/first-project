<%@page import= "java.sql.*"%>
<%@page import= "portal.DAOImplementation" %>

<%
String country=request.getParameter("countr");  

 String buffer="<select name='state'><option value='-1'>Select</option>";  
 ResultSet rs = DAOImplementation.select("* from service_portal.states where countryName='"+country+"' ");  

   while(rs.next()){

   buffer=buffer+"<option value='"+rs.getString(1)+"'>"+rs.getString(2)+"</option>";  

   }  
 buffer=buffer+"</select>";  

 response.getWriter().println(buffer); 

%>