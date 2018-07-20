<%@ page language="java" import="de.kasmi.verein.model.*,java.util.*,java.text.*,java.sql.*,javax.sql.*,javax.naming.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
/**
 * 
 * @author mohamed kasmi (mk200020@gmail.com)
 *
 */
%>  
<%! ArrayList<Long> ID_liste=null; //new ArrayList<Long>();
    ArrayList<String> Name_liste=null; //new ArrayList<String>();
    ArrayList<String> Vorname_liste=null; //new ArrayList<String>();
    ArrayList<java.sql.Date> Eintrittsdatum_liste=null; //new ArrayList<java.sql.Date>();
    ArrayList<java.sql.Date> Austrittsdatum_liste=null; //new ArrayList<java.sql.Date>();
    ArrayList<Long> Zeitdifferenz=null; //new ArrayList<Long>();    
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
     <li><a href="Uebersicht.jsp">Übersicht</a></li>
  </ul>
</nav>
<br>
<br> 
<% 
 ID_liste=new ArrayList<Long>(); 
 Name_liste=new ArrayList<String>();
 Vorname_liste=new ArrayList<String>();
 Eintrittsdatum_liste=new ArrayList<java.sql.Date>();
 Austrittsdatum_liste=new ArrayList<java.sql.Date>();
 Zeitdifferenz=new ArrayList<Long>();
 
 
 Connection con = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();

 try
 {
     Statement stmt = con.createStatement();
     ResultSet results = stmt.executeQuery("select ID,Name,Vorname,Eintrittsdatum,Austrittsdatum from " + "Mitgliederverwaltung.Mitglied");
       
      
     
     %>
     
     <%

     while(results.next())
     {
         long id = results.getLong(1);
         String Name = results.getString(2);
         String Vorname = results.getString(3);
         java.sql.Date Austrittsdatum=results.getDate(5);
         java.sql.Date Eintrittsdatum=results.getDate(4);
        
         ID_liste.add(id);
         Name_liste.add(Name);
         Vorname_liste.add(Vorname);
         Austrittsdatum_liste.add(Austrittsdatum);
         Eintrittsdatum_liste.add(Eintrittsdatum);
         Zeitdifferenz.add(Austrittsdatum.getTime()-Eintrittsdatum.getTime());
     %>
         
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
<%
java.util.Date date1 = new java.util.Date();

Calendar calendar = new GregorianCalendar();
calendar.setTime(date1);
int year1 = calendar.get(Calendar.YEAR);
year1=year1-1;

java.util.Date date2 = new java.util.Date();
calendar.setTime(date2);
int year2 = calendar.get(Calendar.YEAR);
year2=year2+2;

DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

String dateString1=""+year1+"-01-01";
String dateString2=""+year2+"-12-31";

try {
	date1 = dateFormat.parse(dateString1);
	date2=dateFormat.parse(dateString2);
	//System.out.println("date="+date);
} catch (ParseException e) {
	e.printStackTrace();
}

java.sql.Date sqlDate1=new java.sql.Date(date1.getTime());
java.sql.Date sqlDate2=new java.sql.Date(date2.getTime());

int R = (int)(Math.random()*256);
int G = (int)(Math.random()*256);
int B= (int)(Math.random()*256);
java.awt.Color color = new java.awt.Color(R, G, B);
String farbe="#"+Integer.toHexString(color.getRed())+Integer.toHexString(color.getGreen())+Integer.toHexString(color.getBlue());
System.out.println("farbe="+farbe);


%>
Zeitachse vom <%=sqlDate1%> bis <%=sqlDate2%>
<hr style="position:absolute;left:0px;background-color:<%=farbe%>;height:1px;width:100%;text-align:left;">
<br><br><br><br><br>
<%
long Zeitdifferenzmax=sqlDate2.getTime()-sqlDate1.getTime();
//System.out.println("Zeitdifferenzmax="+Zeitdifferenzmax);
for(int i=0;i<ID_liste.size();i++) { 
	out.print("<br><br><br><br>"+Name_liste.get(i)+","+Vorname_liste.get(i)+" vom "+Eintrittsdatum_liste.get(i)+" bis "+Austrittsdatum_liste.get(i));
	long Zeit1=java.lang.Math.abs(Zeitdifferenz.get(i));
	long Zeit2=java.lang.Math.abs(sqlDate1.getTime()-Eintrittsdatum_liste.get(i).getTime());
	//System.out.println("Zeit="+Zeit1);
	double prozent1=((double)Zeit1/Zeitdifferenzmax)*100;
	double prozent2=((double)Zeit2/Zeitdifferenzmax)*100;
	//System.out.println("prozent1="+prozent1);
	//System.out.println("prozent2="+prozent2);
	R = (int)(Math.random()*256);
    G = (int)(Math.random()*256);
    B= (int)(Math.random()*256);
    color = new java.awt.Color(R, G, B);
    farbe="#"+Integer.toHexString(color.getRed())+Integer.toHexString(color.getGreen())+Integer.toHexString(color.getBlue());
    System.out.println("farbe="+farbe);
	out.print("<br><br><hr style=\"position:absolute; left:"+prozent2+"%;background-color:"+farbe+";height:1px;width:"+prozent1+"%;text-align:left;\">");
	
}
%>
</body>
</html>