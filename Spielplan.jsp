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
     <li><a href="index.jsp">home</a></li>
     <li><a href="NeueMitglieder.jsp">Neuer Mitglieder</a></li>
     <li><a href="Spielplan.jsp">Neues Spiel eintragen</a></li>
     <li><a href="http://mohamedkasmi.selfhost.eu:8080/Mitgliederverwaltung/liste">Mitgliederliste</a></li>
     <li><a href="Kalender.jsp">Kalender</a></li>
  </ul>
</nav>
<form action="neuSpiel" method="post">
Geben Sie die Daten des neuen Spiels. Alle Felder sind notwendig.
<table>
<tr><td>Spieltitel: </td><td><input type="text" name="Spieltitel" required="required"></td></tr>
<tr><td>Spieldatum: </td><td><input type="date" name="Spieldatum" required="required"></td></tr>
<tr><td>Tragen Sie eine MitgliederId von der Liste hier unten</td></tr>
<tr><td>MitgliederId: </td><td><input type="text" name="MitgliederId" required="required"></td></tr>
</table>
<table border="1">
<% 
 Connection con = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();

 try
 {
     Statement stmt = con.createStatement();
     ResultSet results = stmt.executeQuery("select ID,Name,Vorname from " + "Mitgliederverwaltung.Mitglied");
       
      
     
     %>
     
     <%

     while(results.next())
     {
         long id = results.getLong(1);
         String Name = results.getString(2);
         String Vorname = results.getString(3);
         
     %>
         <tr><td><%=id%></td><td><%=Name%></td><td><%=Vorname%></td></tr>
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
<input type="submit" value="Absenden">
</form>
</body>
</html>