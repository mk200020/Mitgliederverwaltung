package de.kasmi.verein.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import de.kasmi.verein.model.Mitglied;
import de.kasmi.verein.model.Spiel;

/**
 * Servlet implementation class NeuSpielServlet
 */

/**
 * 
 * @author mohamed kasmi (mk200020@gmail.com)
 *
 */
@WebServlet("/neuSpiel")
public class NeuSpielServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Date Spieldatum;
	private java.sql.Date sqlSpieldatum;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NeuSpielServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
        Mitglied mitglied = (Mitglied)session.getAttribute("mitglied");
    	
    	
    	response.setContentType(
				"text/html;charset=UTF-8");
    	
    	String Spieltitel = request.getParameter("Spieltitel");
    	
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	
    	String SpieldatumString = request.getParameter("Spieldatum");
    	String Spieldatumsjahr=SpieldatumString.substring(0, 4);
		String Spieldatumsmonat=SpieldatumString.substring(5,7);
    	
    	String MitgliederId=request.getParameter("MitgliederId");
    	
    	
    	try {
			this.Spieldatum = format.parse(SpieldatumString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Spiel spiel=new Spiel();
    	
    	sqlSpieldatum=new java.sql.Date(this.Spieldatum.getTime());
    	if(spiel!=null) {
    	spiel.setSpieltitel(Spieltitel);
    	spiel.setSpieldatum(Spieldatum);
    	spiel.setMitgliederId(Long.parseLong(MitgliederId));
    	}
    	
    	try {
			persist(spiel);
		} catch (Exception e) {
			throw new ServletException(e.getMessage()+"Die Persistierung hat nicht geklappt");
		}
    	
    	
    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("Spielplan.jsp");
		requestDispatcher.forward(request, response);
    	
    	
    }
    
    public void persist(Spiel spiel) 
			throws Exception {
		
		Connection con = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();
		String sqlAbfrage="INSERT INTO Mitgliederverwaltung.Spiel (Spieltitel,Spieldatum,MitgliederId) VALUES (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sqlAbfrage);
		
		stmt.setString(1, spiel.getSpieltitel());
		stmt.setDate(2, sqlSpieldatum);
		stmt.setLong(3, spiel.getMitgliederId());
				
		stmt.execute();
		
		
		stmt.close();
		con.close();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
