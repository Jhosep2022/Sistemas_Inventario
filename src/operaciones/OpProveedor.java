package operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import clases.Conexion;
import clases.Proveedor;

public class OpProveedor {
	public static String[][] ListarProveedor(String txt) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT COUNT(*) AS NRO FROM PROVEEDOR WHERE IDPROVEEDOR LIKE '%" + txt + "%' OR PROVEEDOR LIKE '%"
				+ txt + "%' OR TELEFONO LIKE '%" + txt + "%' " + "OR CELULAR LIKE '%" + txt + "%' OR NIT LIKE '%" + txt
				+ "%' OR DIRECCION LIKE '%" + txt + "%'";
		String sql_ = "SELECT * FROM PROVEEDOR WHERE IDPROVEEDOR LIKE '%" + txt + "%' OR PROVEEDOR LIKE '%" + txt
				+ "%' OR TELEFONO LIKE '%" + txt + "%' " + "OR CELULAR LIKE '%" + txt + "%' OR NIT LIKE '%" + txt
				+ "%' OR DIRECCION LIKE '%" + txt + "%'";
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
			String datos[][] = new String[nro][6];
			statement = conn.createStatement();
			result = statement.executeQuery(sql_);
			int i = 0;
			while (result.next()) {
				int idproveedor = result.getInt("IDPROVEEDOR");
				String proveedor = result.getString("PROVEEDOR");
				String telefono = result.getString("TELEFONO");
				String celular = result.getString("CELULAR");
				String nit = result.getString("NIT");
				String direccion = result.getString("DIRECCION");
				datos[i][0] = idproveedor + "";
				datos[i][1] = ("  " + proveedor).toUpperCase();
				datos[i][2] = ("  " + direccion).toUpperCase();
				datos[i][3] = telefono.toUpperCase();
				datos[i][4] = celular.toUpperCase();
				datos[i][5] = nit.toUpperCase();

				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}

	}

	public static Proveedor BuscarProveedor(int idproveedor) {
		Proveedor p = new Proveedor();
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM PROVEEDOR WHERE IDPROVEEDOR = " + idproveedor;
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int idproveedor_ = result.getInt("IDPROVEEDOR");
				String proveedor = result.getString("PROVEEDOR");
				String direccion = result.getString("DIRECCION");
				String telefono = result.getString("TELEFONO");
				String celular = result.getString("CELULAR");
				String descripcion = result.getString("DESCRIPCION");
				String nit = result.getString("NIT");
				p.setIdproveedor(idproveedor_);
				p.setProveedor(proveedor);
				p.setDireccion(direccion);
				p.setTelefono(telefono);
				p.setCelular(celular);
				p.setDescripcion(descripcion);
				p.setNit(nit);
			}
			return p;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return p;
		}

	}

	public static boolean RegistrarProveedor(Proveedor p) {
		Connection conn = (new Conexion()).getConn();
		String sql = "INSERT INTO PROVEEDOR(PROVEEDOR, DIRECCION, TELEFONO, CELULAR, DESCRIPCION, NIT) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, p.getProveedor());
			statement.setString(2, p.getDireccion());
			statement.setString(3, p.getTelefono());
			statement.setString(4, p.getCelular());
			statement.setString(5, p.getDescripcion());
			statement.setString(6, p.getNit());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}

	public static boolean ActualizarProveedor(Proveedor p) {
		Connection conn = (new Conexion()).getConn();
		String sql = "UPDATE PROVEEDOR SET PROVEEDOR = ?, DIRECCION = ?, TELEFONO = ?, CELULAR = ?, NIT = ?, DESCRIPCION = ? WHERE IDPROVEEDOR = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, p.getProveedor());
			statement.setString(2, p.getDireccion());
			statement.setString(3, p.getTelefono());
			statement.setString(4, p.getCelular());
			statement.setString(5, p.getNit());
			statement.setString(6, p.getDescripcion());
			statement.setInt(7, p.getIdproveedor());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}

	public static boolean EliminarProveedor(int idproveedor) {
		Connection conn = (new Conexion()).getConn();
		String sql = "DELETE FROM PROVEEDOR WHERE IDPROVEEDOR = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idproveedor);

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
