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
<form action="cal" method="post">
<table>
<tr><td>Anfangsdatum: </td><td><input type="date" name="Anfangsdatum" required="required"></td></tr>
<tr><td>Enddatum: </td><td><input type="date" name="Enddatum" required="required"></td></tr>
</table>
<input type="submit" value="Absenden">
</form>
</body>
</html>