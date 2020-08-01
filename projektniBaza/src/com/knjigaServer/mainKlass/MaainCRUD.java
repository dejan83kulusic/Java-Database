package com.knjigaServer.mainKlass;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import com.knjigaServer.mysql.dao.KnjigaDAO;
import com.knjigaServer.mysql.dto.Knjiga;




public class MaainCRUD {

public static void main(String[] args) {
	
		// Prikaz svih filmova
		Vector<Knjiga> sviFilmovi = KnjigaDAO.vratiSveKnjige();
		System.out.println("Spisak filmova: ");
		for (Knjiga m : sviFilmovi) {
			System.out.println(m);
		}
		
		// Dodavanje novog filma
		Scanner sc = new Scanner(System.in);
		Knjiga noveKnjige = new Knjiga();
		System.out.println("*** DODAVANJE NOVOG FILMA ***");
		
		System.out.println("Unesite naziv: ");
		noveKnjige.setNaslovKnjige(sc.nextLine());
		System.out.println("Unesite zanr: ");
		noveKnjige.setZanr(sc.nextLine());
		System.out.println("Unesite godinu izdavanja: ");
		noveKnjige.setGodsinaIzdavanja(Integer.valueOf(sc.nextLine()));
		System.out.println("Unesite ime pisca: ");
		noveKnjige.setImePisca(sc.nextLine());
		System.out.println("Unesite ISBN: ");
		noveKnjige.setISBN(sc.nextLine());
		System.out.println("Unesite ime izdavaca: ");
		noveKnjige.setIzdavac(sc.nextLine());
		System.out.println("Unesite kratak sadrzaj");
		noveKnjige.setKratakOpis(sc.nextLine());
		System.out.println("Unesite broj stranica: ");
		noveKnjige.setBrojStranica(Integer.valueOf(sc.nextLine()));
		boolean uspjesno = KnjigaDAO.dodajKnjigu(noveKnjige);
		if (uspjesno) {
			System.out.println("Uspjesno ste dodali film.");
		} else {
			System.out.println("Dogodila se greska pri dodavanju filma.");
		}
		
		System.out.println("**********AZURIRAJ FILM***********");
		System.out.println("Unesite ID knjige: ");
		int idKnjige=sc.nextInt();
		System.out.println("Unesite novi naziv filma:");
		String nazivKnjige=sc.nextLine();
		uspjesno=KnjigaDAO.azurirajKnjigu(idKnjige, nazivKnjige);
		if(uspjesno)
			System.out.println("Uspjesno ste azurirali film.");
		else
			System.out.println("Azuriranje nije uspjelo.");
		
		System.out.println("************BRISANJE FILMA***********");
		System.out.println("Unesite ID filma:");
		 idKnjige=sc.nextInt();
		uspjesno=KnjigaDAO.obrisiKnjigu(idKnjige);
		if(uspjesno)
			System.out.println("Uspjesno ste obrisali film.");
		else
			System.out.println("Brisanje nije uspjelo.");
		sc.close();
	}

}
