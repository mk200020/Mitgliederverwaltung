<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
<form action="suchen" method="post">
<table>
<tr><td>Name*: </td><td><input type="text" name="Name" required="required"></td></tr>
<tr><td>Vorname*: </td><td><input type="text" name="Vorname" required="required"></td></tr>
</table>
<input type="submit" value="Suchen">
</form>
</body>
</html>