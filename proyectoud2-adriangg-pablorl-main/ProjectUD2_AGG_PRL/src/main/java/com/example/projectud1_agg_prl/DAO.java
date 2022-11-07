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
        String jdbcUrl = "jdbc:mariadb://localhost:3306/teis1";
        
        Connection con = DriverManager.getConnection(jdbcUrl, user, pass);
        
        Statement stmt = con.createStatement(); 
        ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos"); 
        while (rs.next()){ 
            System.out.println(rs.getString("id") + " " + 
                    rs.getString(2)+ " " + rs.getString("nombre")); 
        }
		return con;
	}

}
