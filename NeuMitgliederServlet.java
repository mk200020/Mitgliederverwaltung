package de.kasmi.verein.controller;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;


import de.kasmi.verein.model.Mitglied;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.servlet.annotation.MultipartConfig;

/**
 * Servlet implementation class NeuMitgliederServlet
 */

/**
 * 
 * @author mohamed kasmi (mk200020@gmail.com)
 *
 */
@WebServlet("/neu")
@MultipartConfig(
		location="C:/tmp", 
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*5, 
		maxRequestSize=1024*1024*5*5)
public class NeuMitgliederServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final static int MAX_IMAGE_LENGTH = 400;
	private Date Geburtsdatum;
	private Date Eintrittsdatum;
	private Date Austrittsdatum;
	private java.sql.Date sqlGeburtsdatum;
	private java.sql.Date sqlEintrittsdatum;
	private java.sql.Date sqlAustrittsdatum;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NeuMitgliederServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		
		
		
		response.setContentType(
				"text/html;charset=UTF-8");
		
        HttpSession session = request.getSession();
        
        
        Mitglied mitglied=new Mitglied();
        
			String Name = request.getParameter("Name");
			String Vorname = request.getParameter("Vorname");
			String Anrede = request.getParameter("Anrede");
			String Titel = request.getParameter("Titel");
			String Geschlecht = request.getParameter("Geschlecht");
			String Strasse = request.getParameter("Strasse");
			String Plz = request.getParameter("Plz");
			String Ort = request.getParameter("Ort");
			String Staat = request.getParameter("Staat");
			String Beitrag=request.getParameter("Beitrag");

			
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			String GeburtsdatumString = request.getParameter("Geburtsdatum");
			String Geburtsjahr=GeburtsdatumString.substring(0, 4);
			String Geburtsmonat=GeburtsdatumString.substring(5,7);
			
			String EintrittsdatumString = request.getParameter("Eintrittsdatum");
			String Eintrittsjahr=EintrittsdatumString.substring(0, 4);
			String Eintrittsmonat=EintrittsdatumString.substring(5,7);
			String AustrittsdatumString = request.getParameter("Austrittsdatum");
			String Austrittsjahr=EintrittsdatumString.substring(0, 4);
			String Austrittsmonat=EintrittsdatumString.substring(5,7);

			try {
				this.Geburtsdatum = format.parse(GeburtsdatumString);
				this.Eintrittsdatum =format.parse(EintrittsdatumString);
				this.Austrittsdatum = format.parse(AustrittsdatumString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String Email = request.getParameter("Email");

			Part part = request.getPart("Foto");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				InputStream in = part.getInputStream();
				int i = 0;
				while ((i = in.read()) != -1) {
					baos.write(i);
				}
			} catch (IOException ex) {
				throw new ServletException(ex.getMessage());
			}

						
			sqlGeburtsdatum=new java.sql.Date(this.Geburtsdatum.getTime());
			
			sqlEintrittsdatum=new java.sql.Date(this.Eintrittsdatum.getTime());
			sqlAustrittsdatum=new java.sql.Date(this.Austrittsdatum.getTime());
			
			if(mitglied!=null) {
			mitglied.setName(Name);
			mitglied.setVorname(Vorname);
			mitglied.setAnrede(Anrede);
			mitglied.setTitel(Titel);
			mitglied.setGeschlecht(Geschlecht);
			mitglied.setStrasse(Strasse);
			mitglied.setPlz(Plz);
			mitglied.setOrt(Ort);
			mitglied.setStaat(Staat);
			mitglied.setEintrittsdatum(Eintrittsdatum);
			mitglied.setAustrittsdatum(Austrittsdatum);
			mitglied.setGeburtsdatum(Geburtsdatum);
			mitglied.setBeitrag(Double.valueOf(Beitrag));
			mitglied.setFoto(scale(baos.toByteArray()));
			mitglied.setEmail(Email);
			baos.flush();
			}
			
			try {
				persist(mitglied);
			} catch (Exception e) {
				throw new ServletException(e.getMessage()+"Die Persistierung hat nicht geklappt");
			}
			
			session.setAttribute("mitglied", mitglied);
			   	
       
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("NeueMitglieder.jsp");
		requestDispatcher.forward(request, response);

        
    }
	
	public void persist(Mitglied mitglied) 
			throws Exception {
		
		Connection con = ((DataSource)InitialContext.doLookup("jdbc/Mitgliederverwaltung")).getConnection();
		PreparedStatement stmt = con.prepareStatement(
			"INSERT INTO Mitgliederverwaltung.Mitglied ("+
				"Name, " +
				"Vorname, "+
				"Anrede, "+
				"Titel, "+
				"Geschlecht, "+
				"Strasse, "+
				"Ort, "+
				"Plz," +
				"Staat, "+
				"Geburtsdatum, "+
				"Eintrittsdatum, "+
				"Austrittsdatum, "+
				"Email, "+
				"Foto, "+
				"Beitrag"+
			") VALUES (" +
				"?, " +
			    "?, " +
			    "?, " +
			    "?, " +
			    "?, " +
			    "?, " +
			    "?, " +
			    "?, " +
			    "?, " +
				"?, " +
				"?, " + 
				"?, " +
				"?, " +
				"?, " +
				"?)"
		);
		
		stmt.setString(1, mitglied.getName());
		stmt.setString(2, mitglied.getVorname());
		stmt.setString(3, mitglied.getAnrede());
		stmt.setString(4, mitglied.getTitel());
		stmt.setString(5, mitglied.getGeschlecht());
		stmt.setString(6, mitglied.getStrasse());
		stmt.setString(7, mitglied.getOrt());
		stmt.setString(8, mitglied.getPlz());
		stmt.setString(9, mitglied.getStaat());
		stmt.setDate(10, sqlGeburtsdatum);
		stmt.setDate(11, sqlEintrittsdatum);
		stmt.setDate(12, sqlAustrittsdatum);
		stmt.setString(13, mitglied.getEmail());
		stmt.setBytes(14, mitglied.getFoto());
		stmt.setDouble(15, mitglied.getBeitrag());
				
		stmt.execute();
		
		
		stmt.close();
		con.close();
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
	
	/**
     * F�r die Transformation ben�tigen wir die Java Klasse Graphics2D, die zur Verbesserung der urspr�nglichen (unzul�nglichen)
     * Klasse Graphis entwickelt worden ist. Die Klasse Graphics2D ist zwar Erbe der Klasse Graphics, jedoch gehen die
     * M�glichkeiten weit �ber die alte Klasse Graphics hinaus.
     * Grafics2D wird f�r jegliche zweidimensionalen Umwandlung, wie Verschiebungen, Skalierungen oder Farbmanipulationen verwendet werden.
     * Die Umwandlung basiert auf dem sogenannten benutzerabh�ngigen Kooradinatensystem "Transformation Userspace".
     * Diese Standardeinstellung ist f�r Ger�te bestimmt, die im Bereich von 72 dpi sind, also 72 Punkte pro Inch darstellen.
     * Da eine Transformation somit ger�teabh�ngig ist, m�ssten wir die Ger�tekonfiguration erst abfragen.
     * Unser Zielger�t ist jedoch ein Unix Root-Server ohne Grafik API, deshalb werden wir uns an dieser Stelle mit der gegebenen
     * Sch�tzung begn�gen.
     */
	public byte[] scale(byte[] foto) throws IOException {
		/**
		 * Unser Bild ist nun in einem Byte Buffer in Originalgr��e gespeichert.
		 * Da die Gr��e des Bildes unseren Anforderungen eventuell nicht entspricht,
		 * m�ssen wir Sorge tragen, dass nun das Bildskaliert wird.
		 * Hierzu m�ssen wir erstmal herausfinden, welche Breite und welche H�he bei dem Bild vorliegen.
		 * Dies erreichen wir, indem wir aus den Bilddaten und der Klasse ImageIO ein Objekt der Klasse BufferedImage erstellen.
		 * Die Klasse ImageIO ben�tigt die Bilddaten als Eingabestrom.
		 */
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(foto);
		BufferedImage originalBufferedImage = ImageIO.read(byteArrayInputStream);	        		
		
		/**
		 * Die Breite und die H�he des Bildes sind nun einfach zu bekommen.
		 * Wir erhalten sie mithilfe der Convenience Methoden getWidth() und getHeight(). 
		 * Da wir gleich eine Bruchrechnung mit den Werten vorhaben, casten wir beide
		 * Wert zum Datentyp double. Der f�r die Skalierung relevante Wert ist der gr��ere der Beiden.
		 */
		double originalWidth = (double) originalBufferedImage.getWidth();
		double originalHeight = (double) originalBufferedImage.getHeight();
		double relevantLength = originalWidth > originalHeight ? originalWidth : originalHeight; 
		
		/**
		 * Wir legen fest, dass weder Breite noch H�he gr��er als eine von uns
		 * willk�rlich festgelegte L�nge mit dem Namen MAX_IMAGE_LENGTH von 600 Pixel sein d�rfen.
		 * Der Skalierungsfaktor f�r die Transformation errechnet sich aus der maximal zul�ssigen 
		 * L�nge MAX_IMAGE_LENGTH geteilt durch die relevante L�nge.
		 */
		int MAX_IMAGE_LENGTH = 400;
    	double transformationScale = MAX_IMAGE_LENGTH / relevantLength;
    	
    	/**
    	 * Die resultierende Breite und L�nge des Bildes erhalten wir, indem wir die urspr�nglichen Werte
    	 * mit dem Skalierungswert multiplizieren. Das Ergebnis wird mit Math.round(double) zu einer Ganzzahl
    	 * gerundet. Math.round(double) liefert die Werte im Datentyp long. So hohe Wertbereiche erwartet wir
    	 * in Pixeln nicht, deshalb k�nnen wir die erhaltenen Ganzzahlen von long auf int casten.
    	 * Das Gleiche werden wir f�r die Thumb Werte erledigen.
    	 */
    	int width = (int) Math.round( transformationScale * originalWidth );
    	int height = (int) Math.round( transformationScale * originalHeight );

    	/**
    	 * Nun erzeugen wir ein neues Objekt der Klasse BufferedImage und instanziieren es
    	 * mit der erw�nschten und soeben erhaltenen Breite und L�nge.
    	 * Als dritten Parameter erwartet BufferedImage eine Angabe zu dem Bildtypen.
    	 * Wir entscheiden uns hier f�r den Typen INT_RGB, ein 8-bit RGB Bild ohne alpha-Werte.
    	 */
        BufferedImage resizedBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        /**
         * F�r die Transformation ben�tigen wir die Java Klasse Graphics2D, die zur Verbesserung der urspr�nglichen (unzul�nglichen)
         * Klasse Graphis entwickelt worden ist. Die Klasse Graphics2D ist zwar Erbe der Klasse Graphics, jedoch gehen die
         * M�glichkeiten weit �ber die alte Klasse Graphics hinaus.
         * Grafics2D wird f�r jegliche zweidimensionalen Umwandlung, wie Verschiebungen, Skalierungen oder Farbmanipulationen verwendet werden.
         * Die Umwandlung basiert auf dem sogenannten benutzerabh�ngigen Kooradinatensystem "Transformation Userspace".
         * Diese Standardeinstellung ist f�r Ger�te bestimmt, die im Bereich von 72 dpi sind, also 72 Punkte pro Inch darstellen.
         * Da eine Transformation somit ger�teabh�ngig ist, m�ssten wir die Ger�tekonfiguration erst abfragen.
         * Unser Zielger�t ist jedoch ein Unix Root-Server ohne Grafik API, deshalb werden wir uns an dieser Stelle mit der gegebenen
         * Sch�tzung begn�gen.
         */
        Graphics2D g2d = resizedBufferedImage.createGraphics();
        
        /**
         * F�r Graphische Umwandlungen gibt es eine Vielzahl von an Vorgehensweisen.
         * Um Graphics2D einen Hinweis drauf zu geben, welche Art von Umwandlung erw�nscht ist, kann mithilfe der Methode  
         * Um einen Hinweis darauf zu erhalten, welche Vorgehensweise oder Algorthmus erw�nscht ist, k�nnen mithilfe der Methode
         * setRenderingHint(Key, Object) Vorlieben gennant werden. Hierdurch wird insbesondere die Quality bzw. die Laufzeit des Prozesses beeinflusst. 
         * Das hei�t, umso besser die Qualit�t des Resultats, desto l�nger muss auf das Ergebnis gewartet werden.
         * Die Wahl steht dem Nutzer frei. Der Implementation steht allerdings es frei, inwieweit sie sich der Zielvorgabe des Hinweises n�hert
         * oder ob sogar die Hinweise vollst�ndig ignoriert werden. Wir setzen an dieser Stelle eine BICUBIC Interpolation ein.
         */
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        /**
         * Die eigentliche Skalierung erhalten wir mithilfe der Klasse AffineTransform.
         * Sie erwirkt eine lineare Abbildung von 2D-Koordinaten zu anderen 2D-Koordinaten.
         * Bewahrt also durch ein spezielles Verfahren die "Geradlinigkeit" und "Parallelit�t" von Linien.
         * Wir m�ssen zwei Parameter angeben. Der erste gibt die Skalierung in der X-Achse an und der zweite
         * die Skalierung in der y-Achse. Da wir eine gleichm��ige Skalierung w�nschen,
         * geben wir jeweils den Wert transformationScale an. 
         * 
         * Generiert einen RenderedImage, Aufbringen einer von Bildraum verwandeln 
         * sich in User-Space vor dem Zeichnen. 
         * Der Wandel vom User-Space ins Ger�t Raum ist mit der aktuellen Transformation in der Graphics2D getan. 
         * Die angegebene Umwandlung ist auf das Bild angewendet, bevor die Transformation Attribut in der Graphics2D Kontext angewendet wird. 
         * Das Rendering im Internet eingesetzt werden, enthalten die Clip, 
         * Transformieren und Composite Internet. Beachten Sie, dass kein Rendering geschieht, 
         * wenn die angegebene Transformation ist nichtinvertierbare.
         * img - das Bild gerendert werden. Diese Methode bewirkt nichts, wenn img null ist. 
         * XForm - die Transformation von Bildraum in User Space
         */
        AffineTransform affineTransform = AffineTransform.getScaleInstance(transformationScale, transformationScale);
        g2d.drawRenderedImage(originalBufferedImage, affineTransform);
        
        /**
         * F�r die Speicherung in die Datenbank ben�tigen wir einen Eingabestrom.
         * Mithilfe der Klasse ImageIO schreiben wir unser neues BufferedImage Objekt in ein ByteArrayOutputStream
         * und setzen das so erhaltene Buffer in ein ByteArrayInputStream.
         */
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedBufferedImage, "PNG", baos);
        return baos.toByteArray();
	}

}
