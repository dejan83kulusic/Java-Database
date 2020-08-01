package com.knjigaServer.mysql.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Vector;

public class ConnectionPool {
	private String jdbcURL;
	private String username;
	private String password;
	private int preconnectCount;
	private int maxIdleConnections;
	private int maxConnections;

	private int connectCount;
	private Vector<Connection> usedConnections;
	private Vector<Connection> freeConnections;

	private static ConnectionPool instance;

	public static ConnectionPool getInstance() {
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	private ConnectionPool() {
		readConfiguration();
		try {
			freeConnections = new Vector<Connection>();
			usedConnections = new Vector<Connection>();

			for (int i = 0; i < preconnectCount; i++) {
				Connection conn = DriverManager.getConnection(jdbcURL, username, password);
				freeConnections.addElement(conn);
			}
			connectCount = preconnectCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readConfiguration() {
		ResourceBundle bundle = PropertyResourceBundle.getBundle("com.knjigaServer.mysql.dao.ConnectionPool");
		jdbcURL = bundle.getString("jdbcURL");
		username = bundle.getString("username");
		password = bundle.getString("password");
		preconnectCount = 0;
		maxIdleConnections = 10;
		maxConnections = 10;
		try {
			preconnectCount = Integer.parseInt(bundle.getString("preconnectCount"));
			maxIdleConnections = Integer.parseInt(bundle.getString("maxIdleConnections"));
			maxConnections = Integer.parseInt(bundle.getString("maxConnections"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized Connection checkOut() throws SQLException {
		Connection conn = null;
		if (freeConnections.size() > 0) {
			conn = freeConnections.elementAt(0);
			freeConnections.removeElementAt(0);
			usedConnections.addElement(conn);
		} else {
			if (connectCount < maxConnections) {
				conn = DriverManager.getConnection(jdbcURL, username, password);
				usedConnections.addElement(conn);
				connectCount++;
			} else {
				try {
					wait();
					conn = freeConnections.elementAt(0);
					freeConnections.removeElementAt(0);
					usedConnections.addElement(conn);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}

	public synchronized void checkIn(Connection conn) {
		if (conn == null)
			return;
		if (usedConnections.removeElement(conn)) {
			freeConnections.addElement(conn);
			while (freeConnections.size() > maxIdleConnections) {
				int lastOne = freeConnections.size() - 1;
				Connection c = freeConnections.elementAt(lastOne);
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				freeConnections.removeElementAt(lastOne);
			}
			notify();
		}
	}
}
