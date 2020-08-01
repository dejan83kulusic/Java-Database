package com.knjigaServer.mysql.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


import com.knjigaServer.mysql.dto.Knjiga;





public class KnjigaDAO {
	
	// select
	public static Vector<Knjiga> vratiSveKnjige() {
		Vector<Knjiga> retVal = new Vector<Knjiga>();
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM knjiga_dejan ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();


			} catch (SQLException e) {
				e.printStackTrace();
				DBUtilities.getInstance().showSQLException(e);
			} finally {
				ConnectionPool.getInstance().checkIn(conn);
				DBUtilities.getInstance().close(ps, rs);
			}
			return retVal;
		}



			
		public static boolean dodajKnjigu(Knjiga knjiga) {
			boolean retVal = false;
			Connection conn = null;
			java.sql.PreparedStatement ps = null;

		    String query = "INSERT INTO knjiga_dejan (ISBN, ime_pisca,godina_izdavanja,broj_stranica,kratak_sadrzaj,zanr,naslov_knjige,izdavac) VALUES (?,?,?, ?, ?, ?,?,?)";
			
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, knjiga.getISBN());
				ps.setString(2, knjiga.getImePisca());
				ps.setString(3, knjiga.getNaslovKnjige());
				ps.setInt(4, knjiga.getBrojStranica());
				ps.setInt(5, knjiga.getGodsinaIzdavanja());
				ps.setString(6,knjiga.getKratakOpis());
				ps.setString(7, knjiga.getZanr());
				ps.setString(8, knjiga.getIzdavac());
			
				retVal = ps.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
				DBUtilities.getInstance().showSQLException(e);
			} finally {
				ConnectionPool.getInstance().checkIn(conn);
				DBUtilities.getInstance().close(ps);
			}
			return retVal;
		}
		
		public static boolean azurirajKnjigu(int knjigaID, String naziv) {
			boolean retVal = false;
			Connection conn = null;
			java.sql.PreparedStatement ps = null;

			String query = "UPDATE knjiga_dejan SET naslov_knjige=? WHERE id=?";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setString(1, naziv);
				ps.setInt(2, knjigaID);

				retVal = ps.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
				DBUtilities.getInstance().showSQLException(e);
			} finally {
				ConnectionPool.getInstance().checkIn(conn);
				DBUtilities.getInstance().close(ps);
			}
			return retVal;
		}
		
		public static boolean obrisiKnjigu(int knjigaID) {
			boolean retVal = false;
			Connection conn = null;
			java.sql.PreparedStatement ps = null;

			String query = "DELETE FROM knjiga_dejan WHERE id=?";
			try {
				conn = ConnectionPool.getInstance().checkOut();
				ps = conn.prepareStatement(query);
				ps.setInt(1, knjigaID);

				retVal = ps.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
				DBUtilities.getInstance().showSQLException(e);
			} finally {
				ConnectionPool.getInstance().checkIn(conn);
				DBUtilities.getInstance().close(ps);
			}
			return retVal;
		}



		
	}