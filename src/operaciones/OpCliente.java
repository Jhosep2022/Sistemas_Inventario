package operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import clases.Cliente;
import clases.Conexion;

public class OpCliente {

	public static String[][] ListarCliente(String txt) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT COUNT(*) AS NRO FROM CLIENTE WHERE IDCLIENTE LIKE '%" + txt + "%' OR CI LIKE '%" + txt
				+ "%' OR NOMBRE LIKE '%" + txt + "%' OR TELEFONO LIKE '%" + txt + "%' " + "OR CELULAR LIKE '%" + txt
				+ "%' OR FECHAREGISTRO LIKE '%" + txt + "%' OR NIT LIKE '%" + txt + "%'";
		String sql_ = "SELECT * FROM CLIENTE WHERE IDCLIENTE LIKE '%" + txt + "%' OR CI LIKE '%" + txt
				+ "%' OR NOMBRE LIKE '%" + txt + "%' OR TELEFONO LIKE '%" + txt + "%' " + "OR CELULAR LIKE '%" + txt
				+ "%' OR FECHAREGISTRO LIKE '%" + txt + "%' OR NIT LIKE '%" + txt + "%'";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int nro = 0;
			while (result.next()) {
				nro = result.getInt("NRO");
			}
			if (nro < 10) {
				nro = 10;
			}
			String datos[][] = new String[nro][7];
			statement = conn.createStatement();
			result = statement.executeQuery(sql_);
			int i = 0;
			while (result.next()) {
				int idcliente = result.getInt("IDCLIENTE");
				int ci = result.getInt("CI");
				String nombre = result.getString("NOMBRE");
				String telefono = result.getString("TELEFONO");
				String celular = result.getString("CELULAR");
				String nit = result.getString("NIT");
				String fecharegistro = result.getString("FECHAREGISTRO");
				datos[i][0] = idcliente + "";
				datos[i][1] = ci + "";
				datos[i][2] = (" " + nombre + "").toUpperCase();
				datos[i][3] = (telefono + "").toUpperCase();
				datos[i][4] = (celular + "").toUpperCase();
				datos[i][5] = (nit + "").toUpperCase();
				datos[i][6] = (fecharegistro + "").toUpperCase();
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}

	}

	public static Cliente BuscarCliente(int idcliente) {
		Cliente c = new Cliente();
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM CLIENTE WHERE IDCLIENTE = " + idcliente;
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int idcliente_ = result.getInt("IDCLIENTE");
				int ci = result.getInt("CI");
				int estado = result.getInt("ESTADO");
				String nombre = result.getString("NOMBRE");
				String telefono = result.getString("TELEFONO");
				String celular = result.getString("CELULAR");
				String fecharegistro = result.getString("FECHAREGISTRO");
				String direccion = result.getString("DIRECCION");
				String nit = result.getString("NIT");
				c.setIdcliente(idcliente_);
				c.setCi(ci);
				c.setEstado(estado);
				c.setNombre(nombre.toUpperCase());
				c.setTelefono(telefono.toUpperCase());
				c.setCelular(celular.toUpperCase());
				c.setFecharegistro(fecharegistro.toUpperCase());
				c.setDireccion(direccion.toUpperCase());
				c.setNit(nit.toUpperCase());
			}
			return c;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return c;
		}

	}

	public static boolean BuscarClienteCi(int ci, int ci_) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM CLIENTE WHERE CI = " + ci + " AND CI != " + ci_;
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				return true;
			}
			return false;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return false;
		}
	}

	public static boolean RegistraCliente(Cliente c) {
		Connection conn = (new Conexion()).getConn();
		String sql = "INSERT INTO CLIENTE (CI, NOMBRE, TELEFONO, CELULAR, DIRECCION, NIT, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, c.getCi());
			statement.setString(2, c.getNombre());
			statement.setString(3, c.getTelefono());
			statement.setString(4, c.getCelular());
			statement.setString(5, c.getDireccion());
			statement.setString(6, c.getNit());
			statement.setInt(7, c.getEstado());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}

	public static boolean ActualizarCliente(Cliente c) {
		Connection conn = (new Conexion()).getConn();
		String sql = "UPDATE CLIENTE SET CI = ?, NOMBRE = ?, TELEFONO = ?, CELULAR = ?, NIT = ?, DIRECCION = ?, ESTADO = ? WHERE IDCLIENTE = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, c.getCi());
			statement.setString(2, c.getNombre());
			statement.setString(3, c.getTelefono());
			statement.setString(4, c.getCelular());
			statement.setString(5, c.getNit());
			statement.setString(6, c.getDireccion());
			statement.setInt(7, c.getEstado());
			statement.setInt(8, c.getIdcliente());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}

	public static boolean EliminarCliente(int idcliente) {
		Connection conn = (new Conexion()).getConn();
		String sql = "DELETE FROM CLIENTE WHERE IDCLIENTE = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idcliente);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}
}
