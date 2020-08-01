package com.knjigaServer.mysql.dto;



public class Knjiga {
public String ISBN;
public String naslovKnjige;
public String zanr;
public String imePisca;
public String kratakOpis;
public int godsinaIzdavanja;
public int brojStranica;
public String izdavac;

public Knjiga( String ISBN, String naslovKnjige, String zanr, String imePisca, String kratakOpis,
		int godsinaIzdavanja, int brojStranica,String izdavac) {
	super();
	
	this.ISBN = ISBN;
	this.naslovKnjige = naslovKnjige;
	this.zanr = zanr;
	this.imePisca = imePisca;
	this.kratakOpis = kratakOpis;
	this.godsinaIzdavanja = godsinaIzdavanja;
	this.brojStranica = brojStranica;
	this.izdavac=izdavac;
}
public Knjiga() {
	// TODO Auto-generated constructor stub
	super();
}

public String getISBN() {
	return ISBN;
}
public void setISBN(String iSBN) {
	ISBN = iSBN;
}
public String getNaslovKnjige() {
	return naslovKnjige;
}
public void setNaslovKnjige(String naslovKnjige) {
	this.naslovKnjige = naslovKnjige;
}
public String getZanr() {
	return zanr;
}
public void setZanr(String zanr) {
	this.zanr = zanr;
}
public String getImePisca() {
	return imePisca;
}
public void setImePisca(String imePisca) {
	this.imePisca = imePisca;
}
public String getKratakOpis() {
	return kratakOpis;
}
public void setKratakOpis(String kratakOpis) {
	this.kratakOpis = kratakOpis;
}
public int getGodsinaIzdavanja() {
	return godsinaIzdavanja;
}
public void setGodsinaIzdavanja(int godsinaIzdavanja) {
	this.godsinaIzdavanja = godsinaIzdavanja;
}
public int getBrojStranica() {
	return brojStranica;
}
public void setBrojStranica(int brojStranica) {
	this.brojStranica = brojStranica;
}

public String getIzdavac() {
	return izdavac;
}
public void setIzdavac(String izdavac) {
	this.izdavac = izdavac;
}
@Override
public String toString() {
	return "Knjiga [ ISBN=" + ISBN + ", naslovKnjige=" + naslovKnjige + ", zanr=" + zanr + ", imePisca="
			+ imePisca + ", kratakOpis=" + kratakOpis + ", godsinaIzdavanja=" + godsinaIzdavanja + ", brojStranica="
			+ brojStranica + ", izdavac=" + izdavac + "]";
}
}





