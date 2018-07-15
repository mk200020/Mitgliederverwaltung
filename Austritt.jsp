<%@ page language="java" import="de.kasmi.verein.model.*,java.sql.*,javax.sql.*,javax.naming.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
/**
 * 
 * @author mohamed kasmi (mk200020@gmail.com)
 *
 */
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/styles.css">
<title>Mitgliederverwaltung</title>
</head>
<body>
<h1>Mitgliederverwaltung-Hertha</h1>
<nav>
  <ul>
     <li><a href="index.jsp?password=dummy">home</a></li>
     <li><a href="NeueMitglieder.jsp">Neues Mitglied</a></li>
     <li><a href="Spielplan.jsp">Neues Spiel eintragen</a></li>
     <li><a href="liste">Mitgliederliste</a></li>
     <li><a href="Kalender.jsp?n=0&m=0">Kalender</a></li>
     <li><a href="MitgliederSuchen.jsp">Mitglieder suchen</a></li>
     <li><a href="Austritt.jsp">Mitglieder Austritt</a></li>
  </ul>
</nav>
<br>
${message}<br> 
<form action="entfernen" method="post">
Geben Sie die ID des zu entfernendes Mitglied.

<table>
<tr><td>ID*: </td><td><input type="text" name="ID" required="required"></td></tr>
</table>
<table border="1">
<tr><td>Mitglieder-ID</td><td>Name</td><td>Vorname</td><td>Austrittsdatum</td></tr>
<% 
 Connection con = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();

 try
 {
     Statement stmt = con.createStatement();
     ResultSet results = stmt.executeQuery("select ID,Name,Vorname,Austrittsdatum from " + "Mitgliederverwaltung.Mitglied");
       
      
     
     %>
     
     <%

     while(results.next())
     {
         long id = results.getLong(1);
         String Name = results.getString(2);
         String Vorname = results.getString(3);
         java.sql.Date Austrittsdatum=results.getDate(4);
         
     %>
         <tr><td><%=id%></td><td><%=Name%></td><td><%=Vorname%></td><td><%=Austrittsdatum%></td></tr>
     <% 
     }
     
     results.close();
     stmt.close();
     con.close();
 }
 catch (SQLException sqlExcept)
 {
     sqlExcept.printStackTrace();
 }
 
%>
</table>
<input type="submit" value="Entfernen">
</form>
</body>
</html>