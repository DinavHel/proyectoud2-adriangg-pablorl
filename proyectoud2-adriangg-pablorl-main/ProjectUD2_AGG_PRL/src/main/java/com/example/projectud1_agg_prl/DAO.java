package com.example.projectud1_agg_prl;

import java.sql.*;

public class DAO {
	
	String user;
	String pass;
	String jdbcUrl = "jdbc:mariadb://localhost:3306/teis1";
	String dbName;
	
	public DAO(String dbName) {
		this.user = "root";
		this.pass = "root";
		this.dbName = dbName;
	}
	
	public DAO(String user, String pass, String dbName) {
		this.user = user;
		this.pass = pass;
		this.dbName = dbName;
	}
	
	public Connection abrirConexionMariaDB() throws ClassNotFoundException, SQLException {
		Class.forName("org.mariadb.jdbc.Driver");
        String jdbcUrl = "jdbc:mariadb://localhost:3306/" + dbName;
        
        Connection con = DriverManager.getConnection(jdbcUrl, user, pass);
		return con;
	}
	
	public int insercionDatos(String table, String[] columns, String[] values) throws ClassNotFoundException, SQLException {
		Connection con = this.abrirConexionMariaDB();
		Statement stmt = con.createStatement();
		String sql="INSERT INTO " + table + " (";
		for(int i = 0; i < columns.length - 1; i++) {
			sql += columns[i] + ",";
		}
		sql += columns[columns.length - 1] + ")";
		sql += " VALUES (";
		for(int i = 0; i < values.length - 1; i++) {
			sql += values[i] + ",";
		}
		sql += values[values.length - 1] + ");";
        
        int num = stmt.executeUpdate(sql);
        con.close();
		return num;
	}
}
