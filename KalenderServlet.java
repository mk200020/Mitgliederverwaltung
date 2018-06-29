package de.kasmi.verein.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class KalenderServlet
 */

/**
 * 
 * @author mohamed kasmi; (mk200020@gmail.com)
 *
 */

@WebServlet("/cal")
public class KalenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Date utilAnfangsdatum;
	private Date utilEnddatum;
	private String Spieltitel;
	private ArrayList<String> Spieltitel_list;
	private String Name;
	private ArrayList<String> Name_list;
	private String Vorname;
	private ArrayList<String> Vorname_list;
	private java.sql.Date Spieldatum;
	private ArrayList<java.sql.Date> Spieldatum_list;
	private long id;
	private ArrayList<Long> id_list;
	private Connection con;
	private Statement stmt;
	private ResultSet results;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KalenderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	Spieltitel_list=new ArrayList<String>();
    	Name_list=new ArrayList<String>();
    	Vorname_list=new ArrayList<String>();
    	Spieldatum_list=new ArrayList<java.sql.Date>();
    	id_list=new ArrayList<Long>();
    	
    	
    	PrintWriter out=response.getWriter();
    	out.print("<!DOCTYPE html><html><head><title>Kalender</title></head><body>");
    	out.print("<h1>Kalender</h1>");
    	out.print("<h6>Dies sind fiktive Daten</h6>");
    	out.print("<table border=\"1\">");
    	out.print("<tr><td>Datum</td><td>ID</td><td>Spieltitel</td><td>Spieldatum</td><td>Name</td><td>Vorname</td></tr>");
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	String AnfangsdatumString=request.getParameter("Anfangsdatum");
    	String Anfangsdatumjahr=AnfangsdatumString.substring(0, 4);
    	
    	String EnddatumString=request.getParameter("Enddatum");
    	String Enddatumsjahr=EnddatumString.substring(0, 4);
    	
    	
    	try {
			utilAnfangsdatum = dateFormat.parse(AnfangsdatumString);
			utilEnddatum=dateFormat.parse(EnddatumString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    	
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
			    String sqlString="SELECT MitgliederId,Spieltitel,Spieldatum,Name,Vorname"+ 
                  " FROM Mitgliederverwaltung.Spiel INNER JOIN Mitgliederverwaltung.Mitglied "+
                  " ON MitgliederId = Mitgliederverwaltung.Mitglied.ID";
			    
			    
			        results = stmt.executeQuery(sqlString);
			        
			    
			    
			    
	        	while(results.next()){
	        		id = results.getLong(1);
	        		Spieltitel=results.getString(2);
	        		Spieldatum=results.getDate(3);
	        		Name = results.getString(4);
	                Vorname = results.getString(5);
	                
	                id_list.add(id);
	                Spieltitel_list.add(Spieltitel);
	                Spieldatum_list.add(Spieldatum);
	                Name_list.add(Name);
	                Vorname_list.add(Vorname);
	                
	                
	                
	                
	                
	            	}
	        	
			    
			    
			    results.close();
	            stmt.close();
	            con.close();
			    
	       } catch (SQLException sqlExcept) {
	            sqlExcept.printStackTrace();
	       }
		
		
		utilEnddatum.setTime(utilEnddatum.getTime()+86400000);
		Date date=utilAnfangsdatum;
       	
       	java.sql.Date sqlDate;
       	sqlDate=new java.sql.Date(date.getTime());
       	
       	while(date.before(utilEnddatum)){
               
       		if(id_list.size()==1){
       			if(sqlDate.toString().equals(Spieldatum_list.get(0).toString())){
       				out.print("<tr><td style=\"background-color:yellow;\">"+sqlDate+"</td><td style=\\\"background-color:yellow;\\\">"+id_list.get(0)+"</td><td style=\"background-color:yellow;\">"+Spieltitel_list.get(0)+"</td><td style=\"background-color:yellow;\">"+Spieldatum_list.get(0)+"</td><td style=\"background-color:yellow;\">"+Name_list.get(0)+"</td><td style=\"background-color:yellow;\">"+Vorname_list.get(0)+"</td></tr>");
       			}
       		} else {
       		
       		for (int i=0;i<id_list.size();i++) {
       			if(sqlDate.toString().equals(Spieldatum_list.get(i).toString())){
           	    	out.print("<tr><td style=\"background-color:yellow;\">"+sqlDate+"</td><td style=\"background-color:yellow;\">"+id_list.get(i)+"</td><td style=\"background-color:yellow;\">"+Spieltitel_list.get(i)+"</td><td style=\"background-color:yellow;\">"+Spieldatum_list.get(i)+"</td><td style=\"background-color:yellow;\">"+Name_list.get(i)+"</td><td style=\"background-color:yellow;\">"+Vorname_list.get(i)+"</td></tr>");
           		}else if(sqlDate.toString().equals(Spieldatum_list.get(i).toString())!=true && i>0){
           			continue;
           		}else if(sqlDate.toString().equals(Spieldatum_list.get(i).toString())!=true && i==0)
           		{   
           			out.print("<tr><td>"+sqlDate+"</td><td>x</td><td>x</td><td>x</td><td>x</td><td>x</td></tr>");
           		    
           		}	
				
			  }
       		}
       		
       		long day=utilAnfangsdatum.getTime();
       		day=day+86400000;
       		date.setTime(day);
       		
       		sqlDate=new java.sql.Date(date.getTime());
       		
       		
		
       	}
	       
    	    	
    	
    	out.print("</table>");
    	out.print("<a href=\"index.jsp\">Zurück</a>");
    	out.print("</body></html>"); 
    	
    	
    }
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
