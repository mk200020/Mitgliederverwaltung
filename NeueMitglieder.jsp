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
<p>${message}</p>
<nav>
  <ul>
     <li><a href="index.jsp">home</a></li>
     <li><a href="NeueMitglieder.jsp">Neuer Mitglieder</a></li>
     <li><a href="Spielplan.jsp">Neues Spiel eintragen</a></li>
     <li><a href="http://mohamedkasmi.selfhost.eu:8080/Mitgliederverwaltung/liste">Mitgliederliste</a></li>
     <li><a href="Kalender.jsp">Kalender</a></li>
  </ul>
</nav>
Bitte geben Sie die Daten der neuen Mitglieder. Alle Felder sind notwendig.
<form action="neu" method="post" enctype="multipart/form-data">
<table>
<tr><td>Name:</td><td><input type="text" name="Name" required="required"></td></tr>
<tr><td>Vorname: </td><td><input type=text" name="Vorname" required="required"></td></tr>
<tr><td>Anrede: </td><td><input type=text name="Anrede" required="required"></td></tr>
<tr><td>Titel: </td><td><input type="text" name="Titel" required="required"></td></tr>
<tr><td>Geschlecht: </td><td><input type="text" name="Geschlecht" required="required"></td></tr>
<tr><td>Strasse: </td><td><input type="text" name="Strasse" required="required"></td></tr>
<tr><td>Plz: </td><td><input type="text" name="Plz" required="required"></td></tr>
<tr><td>Ort: </td><td><input type="text" name="Ort" required="required"></td></tr>
<tr><td>Staat: </td><td><input type="text" name="Staat" required="required"></td></tr>
<tr><td>Beitrag: </td><td><input type="text" name="Beitrag" required="required"></td></tr>
<tr><td>Geburtsdatum: </td><td><input type="date" name="Geburtsdatum" required="required"></td></tr>
<tr><td>Eintrittsdatum: </td><td><input type="date" name="Eintrittsdatum" required="required"></td></tr>
<tr><td>Austrittsdatum: </td><td><input type="date" name="Austrittsdatum" required="required"></td></tr>
<tr><td>Email: </td><td><input type="email" name="Email" required="required"></td></tr>
<tr><td>Foto: </td><td><input type="file" name="Foto" required="required"></td></tr>
</table>
<input type="submit" value="Absenden">
</form>
</body>
</html>