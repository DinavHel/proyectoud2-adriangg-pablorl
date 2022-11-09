package com.example.projectud1_agg_prl;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	String user;
	String pass;
	String dbName;
	
	public DAO(String dbName) {
		this.user = "root";
		this.pass = "root";
		this.dbName = dbName;
	}

	/**
	 *
	 * @param user
	 * @param pass
	 * @param dbName
	 */
	public DAO(String user, String pass, String dbName) {
		this.user = user;
		this.pass = pass;
		this.dbName = dbName;
	}

	/**
	 *
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	/**
	 * establece una conexion con una base de datos mysql
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection abrirConexionMySqlDB() throws ClassNotFoundException, SQLException {
		//Class.forName("org.mysql.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://localhost:3306/" + dbName;
        
        Connection con = DriverManager.getConnection(jdbcUrl, user, pass);
		return con;
	}

	/**
	 * inserta datos en la base de datos, utiliza el String table para selecionar al tabla donde insertar datos
	 * columns para las columnas del insert y values para los valores que quieres insertar
	 * @param table
	 * @param columns
	 * @param values
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int insercionDatos(String table, String[] columns, String[] values) throws ClassNotFoundException, SQLException {
		Connection con = this.abrirConexionMySqlDB();
		Statement stmt = con.createStatement();
		String sql="INSERT INTO " + table + " (";
		for(int i = 0; i < columns.length - 1; i++) {
			sql += columns[i] + ",";
		}
		sql += columns[columns.length - 1] + ")";
		sql += " VALUES (\"";
		for(int i = 0; i < values.length - 1; i++) {
			sql += values[i] + "\",\"";
		}
		sql += values[values.length - 1] + "\");";
        
        int num = stmt.executeUpdate(sql);
        con.close();
		return num;
	}

	/**
	 * metodo para una lectura basicade una tabla de la base de datos
	 * String table para seleccionar la tabla que leer
	 * Array columns para seleccionar las columnas a leer
	 * @param table
	 * @param columns
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public String lecturaDatos(String table, String[] columns) throws ClassNotFoundException, SQLException {
		String result = "";
		Connection con = this.abrirConexionMySqlDB();
		Statement stmt = con.createStatement();
		String query = "SELECT ";
		for(int i = 0; i < columns.length - 1; i++) {
			query += columns[i] + ", ";
		}
		query += columns[columns.length - 1] + " FROM " + table;
		System.out.println(query);
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			for(int i = 0; i < columns.length - 1; i++) {
				result += rs.getString(columns[i]) + ", ";
			}
			result += rs.getString(columns[columns.length - 1]) + "\n";
		}
		
		con.close();
		return result;
	}



}
