package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
	private Connection conn;

	public Conexion() {
		String dbURL = "jdbc:mysql://localhost:3306/dbalmacen";
		String username = "root";
		String password = "123141";

		try {

			this.conn = DriverManager.getConnection(dbURL, username, password);
			/*
			 * if (this.conn != null) { System.out.println("Conexión exitosa."); } else {
			 * System.out.println("Error al conectar."); }
			 */
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}

	}

	public Connection getConn() {
		return conn;
	}

}
