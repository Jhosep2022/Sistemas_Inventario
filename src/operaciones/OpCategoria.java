package operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import clases.Categoria;
import clases.Conexion;

public class OpCategoria {
	public static String[][] ListarCategoria(String txt) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT COUNT(*) AS NRO FROM CATEGORIA WHERE IDCATEGORIA LIKE '%" + txt + "%' OR CATEGORIA LIKE '%"
				+ txt + "%' OR DESCRIPCION LIKE '%" + txt + "%'";
		String sql_ = "SELECT * FROM CATEGORIA WHERE IDCATEGORIA LIKE '%" + txt + "%' OR CATEGORIA LIKE '%" + txt
				+ "%' OR DESCRIPCION LIKE '%" + txt + "%'";
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
			String datos[][] = new String[nro][2];
			statement = conn.createStatement();
			result = statement.executeQuery(sql_);
			int i = 0;
			while (result.next()) {
				int idcategoria = result.getInt("IDCATEGORIA");
				String categoria = result.getString("CATEGORIA");
				// String descripcion = result.getString("DESCRIPCION");
				datos[i][0] = idcategoria + "";
				datos[i][1] = ("  " + categoria).toUpperCase();
				// datos[i][2] = (" " + descripcion).toUpperCase();
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static Categoria BuscarCategoria(int idcategoria) {
		Categoria c = new Categoria();
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM CATEGORIA WHERE IDCATEGORIA = " + idcategoria;
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int idcategoria_ = result.getInt("IDCATEGORIA");
				String categoria = result.getString("CATEGORIA");
				String descripcion = result.getString("DESCRIPCION");
				c.setIdcategoria(idcategoria_);
				c.setCategoria(categoria.toUpperCase());
				c.setDescripcion(descripcion.toUpperCase());
			}
			return c;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return c;
		}

	}

	public static boolean RegistrarCategoria(Categoria c) {
		Connection conn = (new Conexion()).getConn();
		String sql = "INSERT INTO CATEGORIA(CATEGORIA, DESCRIPCION) VALUES(?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, c.getCategoria());
			statement.setString(2, c.getDescripcion());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}

	public static boolean ActualizarCategoria(Categoria c) {
		Connection conn = (new Conexion()).getConn();
		String sql = "UPDATE CATEGORIA SET CATEGORIA = ?, DESCRIPCION = ? WHERE IDCATEGORIA = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, c.getCategoria());
			statement.setString(2, c.getDescripcion());
			statement.setInt(3, c.getIdcategoria());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}

	public static boolean EliminarCategoria(int idcategoria) {
		Connection conn = (new Conexion()).getConn();
		String sql = "DELETE FROM CATEGORIA WHERE IDCATEGORIA = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idcategoria);

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
