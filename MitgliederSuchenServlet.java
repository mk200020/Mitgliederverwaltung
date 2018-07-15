package de.kasmi.verein.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * 
 * @author mohamed kasmi (mk200020@gmail.com)
 *
 */

/**
 * Servlet implementation class MitgliederSuchenServlet
 */
@WebServlet("/suchen")
public class MitgliederSuchenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long id;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MitgliederSuchenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	PrintWriter out=response.getWriter();
    	String Name=request.getParameter("Name");
    	String Vorname=request.getParameter("Vorname");
    	Name=Name.toLowerCase();
    	Vorname=Vorname.toLowerCase();
    	Name=Character.toString(Name.charAt(0)).toUpperCase()+Name.substring(1, Name.length());
    	//System.out.println(Name);
    	Vorname=Character.toString(Vorname.charAt(0)).toUpperCase()+Vorname.substring(1, Vorname.length());
    	//System.out.println(Vorname);
        out.print("<html><head><title>Gefundene Mitglieder</title></head><body>");
        out.print("<h1>Gefundene Mitglieder oder nicht gefundene Mitglieder</h1>");
        out.print("<h6>Dies sind fiktive Daten</h6>");
        out.print("<a href=\"index.jsp?password=dummy\">Zurück zur Hauptseite</a>");
        
        Connection con1 = null;
        Connection con2=  null;
		try {
			con1 = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();
			con2 = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		Statement stmt1 = null;
		Statement stmt2= null;
		try {
			stmt1 = con1.createStatement(); // ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt2=con2.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        try 
       {
        	ResultSet results1 = stmt1.executeQuery("select Count(*) from " + "Mitgliederverwaltung.Mitglied Where Name='"+Name+"' and Vorname='"+Vorname+"'");
        	ResultSet results2= stmt2.executeQuery("select ID,Name,Vorname,Geburtsdatum,Eintrittsdatum,Austrittsdatum from " + "Mitgliederverwaltung.Mitglied Where Name='"+Name+"' and Vorname='"+Vorname+"'");
        	int cols = 0;
        	while(results1.next()) {
        		cols=results1.getInt(1);
        		// Nothing to do
        	}
        	      	
            if(cols==0) {
            	out.print("<br><br>");
            	out.print("<h1>Leider kein Eintrag gefunden</h1>");
            	out.print("</body></html>");
            } else {
            
        	out.print("<table border=\"1\">");
            out.print("<tr><td>ID</td>");
            out.print("<td>Name</td>");
            out.print("<td>Vorname</td>");
            out.print("<td>Geburtsdatum</td>");
            out.print("<td>Eintrittsdatum</td>");
            out.print("<td>Austrittsdatum</td>");
            out.print("<td>Bild</td></tr>");	 
            
            
            
        	while(results2.next())
            {
                id = results2.getLong(1);
                String GefundeneName = results2.getString(2);
                String GefundeneVorname = results2.getString(3);
                java.sql.Date Geburtsdatum=results2.getDate(4);
                java.sql.Date Eintrittsdatum=results2.getDate(5);
                java.sql.Date Austrittsdatum=results2.getDate(6);
                
                out.print("<td>"+id+"</td>");
                out.print("<td>"+GefundeneName+"</td>");
                out.print("<td>"+GefundeneVorname+"</td>");
                
                out.print("<td>"+Geburtsdatum+"</td>");
                out.print("<td>"+Eintrittsdatum+"</td>");
                out.print("<td>"+Austrittsdatum+"</td>");
                        
                out.print("<td>"+this.printImage()+"</td></tr>");
               
            }

            }
            
        
            results2.close();
            results1.close();
            stmt2.close();
            stmt1.close();
            con1.close();
            con2.close();
            
            out.print("</table>");
            out.print("</body></html>");
            
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    	
    	
    	
    }
    
    public String printImage(){
    	String file;
    	file="<img src=\"foto?id="+id+"\">";
        return file; 
    }
    
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
