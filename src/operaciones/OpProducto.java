package operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import clases.Categoria;
import clases.Conexion;
import clases.Producto;

public class OpProducto {

	public static String[][] ListarProducto(String txt) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT COUNT(*) AS NRO FROM PRODUCTO AS P, CATEGORIA AS C WHERE P.IDCATEGORIA = C.IDCATEGORIA AND (C.CATEGORIA LIKE '%"
				+ txt + "%' OR P.PRODUCTO LIKE '%" + txt + "%' OR P.STOCK LIKE '%" + txt + "%' OR P.PRECIOVENTA LIKE '%"
				+ txt + "%')";
		String sql_ = "SELECT * FROM PRODUCTO AS P, CATEGORIA AS C WHERE P.IDCATEGORIA = C.IDCATEGORIA AND (C.CATEGORIA LIKE '%"
				+ txt + "%' OR P.PRODUCTO LIKE '%" + txt + "%' OR P.STOCK LIKE '%" + txt + "%' OR P.PRECIOVENTA LIKE '%"
				+ txt + "%')";
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
			String datos[][] = new String[nro][5];
			statement = conn.createStatement();
			result = statement.executeQuery(sql_);
			int i = 0;
			while (result.next()) {
				int idproducto = result.getInt("IDPRODUCTO");
				String producto = result.getString("PRODUCTO").toUpperCase();
				String categoria = result.getString("CATEGORIA").toUpperCase();
				int stock = result.getInt("STOCK");
				float precio = result.getFloat("PRECIOVENTA");

				datos[i][0] = idproducto + "";
				datos[i][1] = ("  " + producto).toUpperCase();
				datos[i][2] = ("  " + categoria).toUpperCase();
				datos[i][3] = stock + "";
				datos[i][4] = "  Bs. " + String.format("%.2f", precio) + "  ";
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}

	}

	public static ArrayList<Categoria> ListarCategoria() {
		Connection conn = (new Conexion()).getConn();
		ArrayList<Categoria> cat = new ArrayList<Categoria>();
		String sql = "SELECT * FROM CATEGORIA";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			cat.add(new Categoria());
			while (result.next()) {
				int idcategoria = result.getInt("IDCATEGORIA");
				String categoria = result.getString("CATEGORIA");
				String descripcion = result.getString("DESCRIPCION");
				cat.add(new Categoria(idcategoria, categoria, descripcion));
			}
			return cat;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return cat;
		}

	}

	public static Producto BuscarProducto(int idproducto) {
		Producto p = new Producto();
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT *, P.DESCRIPCION AS DP, C.DESCRIPCION AS DC FROM PRODUCTO AS P, CATEGORIA AS C WHERE P.IDCATEGORIA = C.IDCATEGORIA AND P.IDPRODUCTO = "
				+ idproducto;
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int idcategoria = result.getInt("IDCATEGORIA");
				int idproducto_ = result.getInt("IDPRODUCTO");
				int estado = result.getInt("ESTADO");
				int stock = result.getInt("STOCK");
				String descripcioncategoria = result.getString("DC");
				String descripcionproducto = result.getString("DP");
				String categoria = result.getString("CATEGORIA");
				String producto = result.getString("PRODUCTO");
				float precioventa = result.getFloat("PRECIOVENTA");
				p.setIdcategoria(idcategoria);
				p.setCategoria(categoria);
				p.setDescripcion(descripcioncategoria);
				p.setIdproducto(idproducto_);
				p.setStock(stock);
				p.setEstado(estado);
				p.setPrecioventa(precioventa);
				p.setProducto(producto);
				p.setDescripcionproducto(descripcionproducto);
			}
			return p;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return p;
		}

	}

	public static boolean ActualizarProducto(Producto p) {
		Connection conn = (new Conexion()).getConn();
		String sql = "UPDATE PRODUCTO SET IDCATEGORIA = ?, PRODUCTO = ?, DESCRIPCION = ?, STOCK = ?, PRECIOVENTA = ?, ESTADO = ? WHERE IDPRODUCTO = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, p.getIdcategoria());
			statement.setString(2, p.getProducto());
			statement.setString(3, p.getDescripcionproducto());
			statement.setInt(4, p.getStock());
			statement.setFloat(5, p.getPrecioventa());
			statement.setInt(6, p.getEstado());
			statement.setInt(7, p.getIdproducto());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}

	public static boolean RegistrarProducto(Producto p) {
		Connection conn = (new Conexion()).getConn();
		String sql = "INSERT INTO PRODUCTO(IDCATEGORIA, PRODUCTO, DESCRIPCION, STOCK, PRECIOVENTA, ESTADO) VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, p.getIdcategoria());
			statement.setString(2, p.getProducto());
			statement.setString(3, p.getDescripcionproducto());
			statement.setInt(4, p.getStock());
			statement.setFloat(5, p.getPrecioventa());
			statement.setInt(6, p.getEstado());

			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}

	public static boolean EliminarProducto(int idproducto) {
		Connection conn = (new Conexion()).getConn();
		String sql = "DELETE FROM PRODUCTO WHERE IDPRODUCTO = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idproducto);

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
