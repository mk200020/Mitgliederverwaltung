<%@ page language="java" import="de.kasmi.verein.model.*,java.text.*,java.sql.*,java.util.*,javax.sql.*,javax.naming.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%! private static int n=0; %>
<%! private static int m=0;%>  
<% n=Integer.parseInt(request.getParameter("n"));
   m=Integer.parseInt(request.getParameter("m"));
%>  
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
     <li><a href="liste">Mitgliederliste</a></li>
     <li><a href="Kalender.jsp?n=0&m=0">Kalender</a></li>
  </ul>
</nav>
<table>
<tr>
<td><a href="?n=<%=n-1%>&m=<%=m%>"><</a></td>
<td>
<%
   
   java.util.Date date = new java.util.Date();
   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   
 //Initialize your Date however you like it.
   Calendar calendar = new GregorianCalendar();
   calendar.setTime(date);
   int year = calendar.get(Calendar.YEAR);
   //Add one to month {0 - 11}
   int month = calendar.get(Calendar.MONTH)+1;
   //out.print("Monat="+wandelMonat(month));
   int day = calendar.get(Calendar.DAY_OF_MONTH);
   int Tag=0; //calendar.get(Calendar.DAY_OF_WEEK);
      
   year=year+n;
   month=month+m;
   if(month<1){
	   month=1;
	   m=1;
	   
   }else if(month>12){
	   month=12;
	   m=-1;
   }
   //System.out.println("month="+month);
   String dateString=null;
   if(month<10){
      dateString=""+year+"-0"+month+"-01";
   } else {
	  dateString=""+year+"-"+month+"-01";
   }
   
   //System.out.println(dateString);
   
   try {
		date = dateFormat.parse(dateString);
		//System.out.println("date="+date);
	} catch (ParseException e) {
		e.printStackTrace();
	}
   
   int[] Tage=new int[7];
   for(int i=0;i<7;i++){
	   calendar.setTime(date);
	   Tag=calendar.get(Calendar.DAY_OF_WEEK);
	   //System.out.println("Tag="+Tag);
	   Tage[i]=Tag;
	   date.setTime(date.getTime()+86400000);
   }
   
   //month=calendar.get(Calendar.MONTH);
   
   try {
		date = dateFormat.parse(dateString);
		//System.out.println("date"+date);
	} catch (ParseException e) {
		e.printStackTrace();
	}
   
   
   
   
   out.print(year);
   
   
%>
<%! String wandelTag(int Tag){ 
	String TagString=null;
	switch(Tag){
	   case Calendar.SUNDAY: TagString="SO";break;
	   case Calendar.MONDAY: TagString="MO";break;
	   case Calendar.TUESDAY: TagString="DI";break;
	   case Calendar.WEDNESDAY: TagString="MI";break;
	   case Calendar.THURSDAY: TagString="DO";break;
	   case Calendar.FRIDAY: TagString="FR";break;
	   case Calendar.SATURDAY: TagString="SA";break;
	   }
	return TagString;
}

   String wandelMonat(int month){
	   String monthString=null;
	   switch(month){
	   case Calendar.JANUARY: monthString="Januar";break;
	   case Calendar.FEBRUARY: monthString="Februar";break;
	   case Calendar.MARCH: monthString="März";break;
	   case Calendar.APRIL: monthString="April";break;
	   case Calendar.MAY: monthString="Mai";break;
	   case Calendar.JUNE: monthString="Juni";break;
	   case Calendar.JULY: monthString="Juli";break;
	   case Calendar.AUGUST: monthString="August";break;
	   case Calendar.SEPTEMBER: monthString="September";break;
	   case Calendar.OCTOBER: monthString="Oktober";break;
	   case Calendar.NOVEMBER: monthString="November";break;
	   case Calendar.DECEMBER: monthString="Dezember";break;
	   
	   }
	   return monthString;
   }
   
   ArrayList<String> printString(java.sql.Date sqlDate,int index){
	   
	   ArrayList<String> Ausgabe=new ArrayList<String>();
	   java.sql.Connection con=null;
	   java.sql.Statement stmt=null;
	   java.sql.ResultSet results=null;
	   java.sql.Date Spieldatum=null;
	   
	   
	   ArrayList<java.sql.Date> Spieldatum_list=new ArrayList<java.sql.Date>();
	   
	   
	   try {
		   
		   con = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   try {
			
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   try 
       {
		    String sqlString="SELECT Spieldatum FROM Mitgliederverwaltung.Spiel ORDER BY Spieldatum" ;
		    
		    
		    results = stmt.executeQuery(sqlString);
		        
		    
		    
		    
        	while(results.next()){
        		Spieldatum=results.getDate(1);
                Spieldatum_list.add(Spieldatum);
                Spieldatum.setTime(Spieldatum.getTime()+86400000);
                
                
           }
        	
        	
        	
           //System.out.println("Spieldatum_list="+Spieldatum_list);
           
           int AnzahlMitglieder=0;
           
           for(int j=0;j<Spieldatum_list.size();j++){	
        	 if(sqlDate.equals(Spieldatum_list.get(j))) {
        		  AnzahlMitglieder++;
        		  
        		  //System.out.println("Anfangsdatum"+Anfangsdatum);
        		  //System.out.println("Enddatum"+Enddatum);
        		  if(j==0) Ausgabe.add(""+index);
            	  
            	  
     	     } 
        	
        	 else if(j==0){
     	    	  Ausgabe.add(""+index);
     	    	   
     	     }
        	 
        	 String Anfangsdatum=""+(Spieldatum_list.get(0).getYear()-100+2000)+"-01-01";
   		     String Enddatum=""+(Spieldatum_list.get(Spieldatum_list.size()-1).getYear()-100+2000)+"-12-31";
   		     if(AnzahlMitglieder==0) continue;
   		     else if(j==Spieldatum_list.size()-1) Ausgabe.add("<a href=\"cal?Anfangsdatum="+Anfangsdatum+"&Enddatum="+Enddatum+"\">"+AnzahlMitglieder+"</a>");
        	 
           }
        	
		    results.close();
            stmt.close();
            con.close();
		    
       } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
       }

	   
	//   System.out.println(Ausgabe);
	   
	   
	   return Ausgabe;
   }
%>
</td>
<td>
<a href="?n=<%=n+1%>&m=<%=m%>">></a>
</td>
</tr>
</table>
<a href="?n=<%=n%>&m=<%=m-1%>"><</a>
<% out.println(wandelMonat(month-1)); %>
<a href="?n=<%=n%>&m=<%=m+1%>">></a>
<table border="2">
<tr>
<% 
 for(int i=0;i<7;i++){
  out.print("<td>"+wandelTag(Tage[i])+"</td>");
 }
%>
</tr>

<% 
       
   out.print("<tr>");
   for(int i=calendar.getActualMinimum(Calendar.DAY_OF_MONTH);i<=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);i++){
	   
	   
	   if(i%7!=0){
		   
		   java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		   
		   date.setTime(date.getTime()+86400000);
		   
		   sqlDate=new java.sql.Date(date.getTime());
		   
		   int size=0;
		   if(printString(sqlDate,i)!=null){
		     size=printString(sqlDate, i).size();
		   
		   }
		   
		   String ausgabe="";
		   
		   out.print("<td>");
			  for(int j=0;j<size;j++){
				  if(printString(sqlDate,i)!=null){
					  if(j==0) { ausgabe=printString(sqlDate,i).get(0); }
					  else {
			             ausgabe=ausgabe+printString(sqlDate,i).get(j);
					  }
				  }   
			  }
		   out.print(ausgabe+"</td>");
		   //System.out.println("ausgabe="+ausgabe);		  
	   }else {
		   java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		   
		   //System.out.println("sqlDate im else Zweig vorher ="+sqlDate);
		   date.setTime(date.getTime()+86400000);
		   
		   sqlDate=new java.sql.Date(date.getTime());
		   //System.out.println("sqlDate im else Zweig nachher="+sqlDate);
		   int size=0;
		   if(printString(sqlDate,i)!=null){
		      size=printString(sqlDate,i).size();
		   }
		   String ausgabe="";
		   
		    
		   out.print("<td>");
			  for(int j=0;j<size;j++){
				  if(printString(sqlDate,i)!=null){
					  if(j==0){ ausgabe=printString(sqlDate,i).get(0);}
					  else {
			              ausgabe=ausgabe+printString(sqlDate,i).get(j);
					  }
				  }
			  }
		   out.print(ausgabe+"</td>");
		   out.print("</tr>");
		   
	   }
	   
   }    
   
   out.print("</tr>");
%> 


</table>
<p>Die blau gefärbte Zahl zeigt wieviele Mitglieder in der Datenbank, das Spiel an dem Tag gucken</p>
</body>
</html>
