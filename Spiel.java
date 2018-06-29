package de.kasmi.verein.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author mohamed kasmi (mk200020@gmail.com)
 *
 */


public class Spiel {

	
	private Long Id;
	private String Spieltitel;
	private Date Spieldatum;
	private Long mitgliederId; // Fremdschlüssel
	
	
	

	public Long getMitgliederId() {
		return mitgliederId;
	}


	public void setMitgliederId(Long mitgliederId) {
		this.mitgliederId = mitgliederId;
	}


	public Long getId() {
		return Id;
	}


	public void setSpielId(Long Id) {
		this.Id = Id;
	}


	public String getSpieltitel() {
		return Spieltitel;
	}


	public void setSpieltitel(String spieltitel) {
		Spieltitel = spieltitel;
	}


	public Date getSpieldatum() {
		return Spieldatum;
	}


	public void setSpieldatum(Date spieldatum) {
		Spieldatum = spieldatum;
	}


	
}
