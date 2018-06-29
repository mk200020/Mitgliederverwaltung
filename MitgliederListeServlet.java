package de.kasmi.verein.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import de.kasmi.verein.model.Mitglied;

/**
 * Servlet implementation class MitgliederListeServlet
 */

/**
 * 
 * @author mohamed kasmi (mk200020@gmail.com)
 *
 */
@WebServlet("/liste")
public class MitgliederListeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MitgliederListeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	//Mitglied mitglied=(Mitglied)session.getAttribute("mitglied");
        PrintWriter out=response.getWriter();
        out.print("<html><head><title>Mitgliederliste</title></head><body>");
        out.print("<h1>Mitgliederliste</h1>");
        out.print("<table border=\"1\">");
        out.print("<td>ID</td>");
        out.print("<td>Name</td>");
        out.print("<td>Vorname</td>");
        out.print("<td>Geburtsdatum</td>");
        out.print("<td>Eintrittsdatum</td>");
        out.print("<td>Austrittsdatum</td>");
        out.print("<td>Bild</td>");
        Connection con = null;
		try {
			con = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        try 
       {
        	ResultSet results = stmt.executeQuery("select ID,Name,Vorname,Geburtsdatum,Eintrittsdatum,Austrittsdatum from " + "Mitgliederverwaltung.Mitglied");
              
            out.print("<tr>"); 
            while(results.next())
            {
                id = results.getLong(1);
                String Name = results.getString(2);
                String Vorname = results.getString(3);
                java.sql.Date Geburtsdatum=results.getDate(4);
                java.sql.Date Eintrittsdatum=results.getDate(5);
                java.sql.Date Austrittsdatum=results.getDate(6);
                
                out.print("<td>"+id+"</td>");
                out.print("<td>"+Name+"</td>");
                out.print("<td>"+Vorname+"</td>");
                
                out.print("<td>"+Geburtsdatum+"</td>");
                out.print("<td>"+Eintrittsdatum+"</td>");
                out.print("<td>"+Austrittsdatum+"</td>");
                        
               
        	  
               
               out.print("<td>"+this.printImage()+"</td></tr>");
               
            }
            
            results.close();
            stmt.close();
            
            out.print("</table>");
            out.print("<a href=\"index.jsp\">Zurück</a>");
            out.print("</body></html>");
            
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
        
        /*
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Mitgliederliste.jsp");
		requestDispatcher.forward(request, response);
		*/
	
    }
    
    public String printImage(){
    	String file;
    	file="<img src=\"http://mohamedkasmi.selfhost.eu:8080/Mitgliederverwaltung/foto?id="+id+"\">";
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
		processRequest(request, response);
	}

}
