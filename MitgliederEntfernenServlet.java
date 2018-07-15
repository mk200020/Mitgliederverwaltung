package de.kasmi.verein.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MitgliederEntfernenServlet
 */
@WebServlet("/entfernen")
public class MitgliederEntfernenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MitgliederEntfernenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session=request.getSession();
    	String password=(String)session.getAttribute("password");
    	String message=(String)session.getAttribute("message");
    	String ID_String=request.getParameter("ID");
    	long ID=Long.parseLong(ID_String);
    	
    	Connection con=null;
		try {
			con = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	 try
    	 {
    	     Statement stmt = con.createStatement();
    	     if(password.equals("network")) {
    	     int results1=stmt.executeUpdate("delete from Mitgliederverwaltung.Spiel Where MitgliederId="+ID_String);
    	     int results2 = stmt.executeUpdate("delete from " + "Mitgliederverwaltung.Mitglied where ID="+ID_String);
    	     message="<p style=\"color:green;\">Sie sind berechtigt,die Transaktion durchzuführen</p>";
    	     session.setAttribute("message", message);
    	     }else {
    	    	 message="<p style=\"color:red;\">Sie sind nicht berechtigt,die Transaktion durchzuführen</p>";
    	    	 session.setAttribute("message", message);
    	    	 
    	     }
    	     
    	     stmt.close();
    	     con.close();
    	 }
    	 catch (SQLException sqlExcept)
    	 {
    	     sqlExcept.printStackTrace();
    	 }
    	
    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("Austritt.jsp");
		requestDispatcher.forward(request, response);
    	
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
