package de.kasmi.verein.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 
 * @author mohamed kasmi (mk200020@gmail.com)
 *
 */



public class Mitglied implements Serializable {
	
	
	
	private Long ID;
	
	private String Name;
	private String Vorname;
	private String Anrede;
	private String Titel;
	private String Geschlecht;
	private String Strasse;
	private String Plz;
	private String Ort;
	private String Staat;
	private Date Geburtsdatum;
	private Date Eintrittsdatum;
	private Date Austrittsdatum;
	private String Email;
	private byte[] Foto;
	private double Beitrag;
	
	
	
	

	

	public Mitglied(){
		
	}
	
	public Mitglied(String Name,String Vorname,String Anrede,String Titel,String Geschlecht,String Strasse,String Plz,String Ort,String Staat,Date Geburtsdatum,Date Eintrittsdatum,Date Austrittsdatum,String Email,byte[] Foto,double Beitrag){
		
		this.Name=Name;
		this.Vorname=Vorname;
		this.Anrede=Anrede;
		this.Titel=Titel;
		this.Geschlecht=Geschlecht;
		this.Strasse=Strasse;
		this.Plz=Plz;
		this.Ort=Ort;
		this.Staat=Staat;
		this.Geburtsdatum=Geburtsdatum;
		this.Eintrittsdatum=Eintrittsdatum;
		this.Austrittsdatum=Austrittsdatum;
		this.Email=Email;
		this.Foto=Foto;
		this.Beitrag=Beitrag;
		
		
		
	}
		
	public Long getID() {
		return ID;
	}
	
	public void setID(Long iD) {
		ID = iD;
	}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getVorname() {
		return Vorname;
	}
	
	public void setVorname(String vorname) {
		Vorname = vorname;
	}
	
	public String getAnrede() {
		return Anrede;
	}
	
	public void setAnrede(String anrede) {
		Anrede = anrede;
	}
	
	public String getTitel() {
		return Titel;
	}
	
	public void setTitel(String titel) {
		Titel = titel;
	}
	
	public String getGeschlecht() {
		return Geschlecht;
	}
	
	public void setGeschlecht(String geschlecht) {
		Geschlecht = geschlecht;
	}
	
	public String getStrasse() {
		return Strasse;
	}
	
	public void setStrasse(String strasse) {
		Strasse = strasse;
	}
	
	public String getPlz() {
		return Plz;
	}
	
	public void setPlz(String plz) {
		Plz = plz;
	}
	
	public String getOrt() {
		return Ort;
	}
	
	public void setOrt(String ort) {
		Ort = ort;
	}
	
	
	public String getStaat() {
		return Staat;
	}
	
	public void setStaat(String staat) {
		Staat = staat;
	}
	
	public Date getGeburtsdatum() {
		return Geburtsdatum;
	}
	
	public void setGeburtsdatum(Date geburtsdatum) {
		Geburtsdatum = geburtsdatum;
	}
	
	public Date getEintrittsdatum() {
		return Eintrittsdatum;
	}
	
	public void setEintrittsdatum(Date eintrittsdatum) {
		Eintrittsdatum = eintrittsdatum;
	}
	
	public Date getAustrittsdatum() {
		return Austrittsdatum;
	}
	
	public void setAustrittsdatum(Date austrittsdatum) {
		Austrittsdatum = austrittsdatum;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public byte[] getFoto() {
		return Foto;
	}
	
	public void setFoto(byte[] foto) {
		Foto = foto;
	}
	
	public double getBeitrag() {
		return Beitrag;
	}

	public void setBeitrag(double beitrag) {
		Beitrag = beitrag;
	}
	

}
