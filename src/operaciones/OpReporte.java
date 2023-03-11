package operaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import clases.Conexion;
import clases.Producto;

public class OpReporte {
	public static String[][] ListarMenosStock() {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT COUNT(*) AS NRO FROM (SELECT P.IDPRODUCTO FROM CATEGORIA AS C, PRODUCTO AS P WHERE C.IDCATEGORIA = P.IDCATEGORIA ORDER BY P.STOCK ASC LIMIT 20) AS T";
		String sql_ = "SELECT P.PRODUCTO AS PRODUCTO, P.STOCK AS CANTIDAD FROM CATEGORIA AS C, PRODUCTO AS P WHERE C.IDCATEGORIA = P.IDCATEGORIA ORDER BY P.STOCK ASC LIMIT 20";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int nro = 0;
			while (result.next()) {
				nro = result.getInt("NRO");
			}
			if (nro < 20) {
				nro = 20;
			} else if (nro == 0) {
				return null;
			}
			System.out.println(nro);
			String datos[][] = new String[nro][2];
			statement = conn.createStatement();
			result = statement.executeQuery(sql_);
			int i = 0;
			while (result.next()) {
				int cantidad = result.getInt("CANTIDAD");
				String producto = result.getString("PRODUCTO").toUpperCase();
				datos[i][0] = (i + 1) + ". " + producto;
				datos[i][1] = cantidad + "";
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static String[][] ListarCompras(String fechai, String fechaf) {
		Connection conn = (new Conexion()).getConn();
		Statement statement;
		try {
			String sql = "", sql_ = "";
			if (fechai.equals("")) {
				sql = "SELECT CO.IDCOMPRA, PRO.PROVEEDOR,CA.CATEGORIA, CO.FECHAREGISTRO, P.PRODUCTO, DC.CANTIDAD, DC.PRECIOCOMPRA, (DC.CANTIDAD*DC.PRECIOCOMPRA) AS SUBTOTAL  FROM COMPRA AS CO, DETALLECOMPRA AS DC, PROVEEDOR AS PRO, PRODUCTO AS P, CATEGORIA AS CA WHERE CO.IDCOMPRA = DC.IDCOMPRA AND CO.IDPROVEEDOR = PRO.IDPROVEEDOR AND DC.IDPRODUCTO = P.IDPRODUCTO AND CA.IDCATEGORIA = P.IDCATEGORIA ORDER BY CO.FECHAREGISTRO ASC";
				sql_ = "SELECT COUNT(*) AS NRO FROM COMPRA AS CO, DETALLECOMPRA AS DC, PROVEEDOR AS PRO, PRODUCTO AS P, CATEGORIA AS CA WHERE CO.IDCOMPRA = DC.IDCOMPRA AND CO.IDPROVEEDOR = PRO.IDPROVEEDOR AND DC.IDPRODUCTO = P.IDPRODUCTO AND CA.IDCATEGORIA = P.IDCATEGORIA ORDER BY CO.FECHAREGISTRO ASC";
			} else {
				if (fechaf.equals("")) {
					sql = "SELECT CO.IDCOMPRA,CA.CATEGORIA, PRO.PROVEEDOR, CO.FECHAREGISTRO, P.PRODUCTO, DC.CANTIDAD, DC.PRECIOCOMPRA, (DC.CANTIDAD*DC.PRECIOCOMPRA) AS SUBTOTAL FROM COMPRA AS CO, DETALLECOMPRA AS DC, PROVEEDOR AS PRO, PRODUCTO AS P, CATEGORIA AS CA WHERE CO.IDCOMPRA = DC.IDCOMPRA AND CO.IDPROVEEDOR = PRO.IDPROVEEDOR AND DC.IDPRODUCTO = P.IDPRODUCTO AND CA.IDCATEGORIA = P.IDCATEGORIA AND CO.FECHAREGISTRO BETWEEN CAST('"
							+ fechai
							+ " 00:00:00' AS DATETIME) AND CAST(NOW() AS DATETIME) ORDER BY CO.FECHAREGISTRO ASC";
					sql_ = "SELECT COUNT(*) AS NRO FROM COMPRA AS CO, DETALLECOMPRA AS DC, PROVEEDOR AS PRO, PRODUCTO AS P, CATEGORIA AS CA WHERE CO.IDCOMPRA = DC.IDCOMPRA AND CO.IDPROVEEDOR = PRO.IDPROVEEDOR AND DC.IDPRODUCTO = P.IDPRODUCTO AND CA.IDCATEGORIA = P.IDCATEGORIA AND CO.FECHAREGISTRO BETWEEN CAST('"
							+ fechai
							+ " 00:00:00' AS DATETIME) AND CAST(NOW() AS DATETIME) ORDER BY CO.FECHAREGISTRO ASC";
				} else {
					sql = "SELECT CO.IDCOMPRA, CA.CATEGORIA,PRO.PROVEEDOR, CO.FECHAREGISTRO, P.PRODUCTO, DC.CANTIDAD, DC.PRECIOCOMPRA, (DC.CANTIDAD*DC.PRECIOCOMPRA) AS SUBTOTAL FROM COMPRA AS CO, DETALLECOMPRA AS DC, PROVEEDOR AS PRO, PRODUCTO AS P, CATEGORIA AS CA WHERE CO.IDCOMPRA = DC.IDCOMPRA AND CO.IDPROVEEDOR = PRO.IDPROVEEDOR AND DC.IDPRODUCTO = P.IDPRODUCTO AND CA.IDCATEGORIA = P.IDCATEGORIA AND CO.FECHAREGISTRO BETWEEN CAST('"
							+ fechai + " 00:00:00' AS DATETIME) AND CAST('" + fechaf
							+ " 23:59:59' AS DATETIME) ORDER BY CO.FECHAREGISTRO ASC";
					sql_ = "SELECT COUNT(*) AS NRO FROM COMPRA AS CO, DETALLECOMPRA AS DC, PROVEEDOR AS PRO, PRODUCTO AS P, CATEGORIA AS CA WHERE CO.IDCOMPRA = DC.IDCOMPRA AND CO.IDPROVEEDOR = PRO.IDPROVEEDOR AND DC.IDPRODUCTO = P.IDPRODUCTO AND CA.IDCATEGORIA = P.IDCATEGORIA AND CO.FECHAREGISTRO BETWEEN CAST('"
							+ fechai + " 00:00:00' AS DATETIME) AND CAST('" + fechaf
							+ " 23:59:59' AS DATETIME) ORDER BY CO.FECHAREGISTRO ASC";
				}

			}
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql_);
			int nro = 0;
			while (result.next()) {
				nro = result.getInt("NRO");
			}
			if (nro == 0) {
				return new String[15][8];
			}
			String datos[][] = new String[nro][8];
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			int i = 0;
			String idcompra = "";
			char h = '-';
			while (result.next()) {
				if (idcompra.equals(result.getInt("IDCOMPRA") + "") && i != 0) {
					StringBuffer a = new StringBuffer((result.getInt("IDCOMPRA") + "").length());
					for (int j = 0; j < (result.getInt("IDCOMPRA") + "").length(); j++) {
						a.append(h);
					}
					datos[i][0] = a.toString();

					StringBuffer b = new StringBuffer(("**" + result.getString("PROVEEDOR")).length());
					for (int j = 0; j < ("**" + result.getString("PROVEEDOR")).length(); j++) {
						b.append(h);
					}
					datos[i][1] = "  " + b.toString();

					StringBuffer c = new StringBuffer(result.getString("FECHAREGISTRO").length());
					for (int j = 0; j < result.getString("FECHAREGISTRO").length(); j++) {
						c.append(h);
					}
					datos[i][2] = c.toString();
				} else {
					if (h == '*') {
						h = '-';
					} else {
						h = '*';
					}
					idcompra = result.getInt("IDCOMPRA") + "";
					datos[i][0] = result.getInt("IDCOMPRA") + "";
					datos[i][1] = "  " + result.getString("PROVEEDOR");
					datos[i][2] = result.getString("FECHAREGISTRO");
				}

				datos[i][3] = "  " + result.getString("PRODUCTO");
				datos[i][4] = "  " + result.getString("CATEGORIA");
				datos[i][5] = result.getInt("CANTIDAD") + "";
				datos[i][6] = String.format("%.2f  ", result.getFloat("PRECIOCOMPRA"));
				datos[i][7] = String.format("%.2f  ", result.getFloat("SUBTOTAL"));
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static String[][] ListarVentas(String fechai, String fechaf) {
		Connection conn = (new Conexion()).getConn();
		Statement statement;
		try {
			String sql = "", sql_ = "";
			if (fechai.equals("")) {
				sql = "SELECT V.IDVENTA, CL.NOMBRE, CA.CATEGORIA, V.FECHAREGISTRO, PRO.PRODUCTO, DV.CANTIDAD, PRO.PRECIOVENTA, (PRO.PRECIOVENTA*DV.CANTIDAD) AS SUBTOTAL FROM VENTA AS V, DETALLEVENTA AS DV, CLIENTE AS CL, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND V.IDCLIENTE = CL.IDCLIENTE AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA ORDER BY V.FECHAREGISTRO ASC";
				sql_ = "SELECT COUNT(*) AS NRO FROM VENTA AS V, DETALLEVENTA AS DV, CLIENTE AS CL, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND V.IDCLIENTE = CL.IDCLIENTE AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA ORDER BY V.FECHAREGISTRO ASC";
			} else {
				if (fechaf.equals("")) {
					sql = "SELECT V.IDVENTA, CL.NOMBRE, CA.CATEGORIA, V.FECHAREGISTRO, PRO.PRODUCTO, DV.CANTIDAD, PRO.PRECIOVENTA, (PRO.PRECIOVENTA*DV.CANTIDAD) AS SUBTOTAL FROM VENTA AS V, DETALLEVENTA AS DV, CLIENTE AS CL, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND V.IDCLIENTE = CL.IDCLIENTE AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai
							+ " 00:00:00' AS DATETIME) AND CAST(NOW() AS DATETIME) ORDER BY V.FECHAREGISTRO ASC";
					sql_ = "SELECT COUNT(*) AS NRO FROM VENTA AS V, DETALLEVENTA AS DV, CLIENTE AS CL, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND V.IDCLIENTE = CL.IDCLIENTE AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai
							+ " 00:00:00' AS DATETIME) AND CAST(NOW() AS DATETIME) ORDER BY V.FECHAREGISTRO ASC";
				} else {
					sql = "SELECT V.IDVENTA, CL.NOMBRE, CA.CATEGORIA, V.FECHAREGISTRO, PRO.PRODUCTO, DV.CANTIDAD, PRO.PRECIOVENTA, (PRO.PRECIOVENTA*DV.CANTIDAD) AS SUBTOTAL FROM VENTA AS V, DETALLEVENTA AS DV, CLIENTE AS CL, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND V.IDCLIENTE = CL.IDCLIENTE AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai + " 00:00:00' AS DATETIME) AND CAST('" + fechaf
							+ " 23:59:59' AS DATETIME) ORDER BY V.FECHAREGISTRO ASC";
					sql_ = "SELECT COUNT(*) AS NRO FROM VENTA AS V, DETALLEVENTA AS DV, CLIENTE AS CL, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND V.IDCLIENTE = CL.IDCLIENTE AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai + " 00:00:00' AS DATETIME) AND CAST('" + fechaf
							+ " 23:59:59' AS DATETIME) ORDER BY V.FECHAREGISTRO ASC";
				}

			}
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql_);
			int nro = 0;
			while (result.next()) {
				nro = result.getInt("NRO");
			}
			if (nro == 0) {
				return new String[15][8];
			}
			String datos[][] = new String[nro][8];
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			int i = 0;
			String idventa = "";
			char h = '-';
			while (result.next()) {
				if (idventa.equals(result.getInt("IDVENTA") + "") && i != 0) {
					StringBuffer a = new StringBuffer((result.getInt("IDVENTA") + "").length());
					for (int j = 0; j < (result.getInt("IDVENTA") + "").length(); j++) {
						a.append(h);
					}
					datos[i][0] = a.toString();

					StringBuffer b = new StringBuffer(("**" + result.getString("NOMBRE")).length());
					for (int j = 0; j < ("**" + result.getString("NOMBRE")).length(); j++) {
						b.append(h);
					}
					datos[i][1] = "  " + b.toString();

					StringBuffer c = new StringBuffer(result.getString("FECHAREGISTRO").length());
					for (int j = 0; j < result.getString("FECHAREGISTRO").length(); j++) {
						c.append(h);
					}
					datos[i][2] = c.toString();
				} else {
					if (h == '*') {
						h = '-';
					} else {
						h = '*';
					}
					idventa = result.getInt("IDVENTA") + "";
					datos[i][0] = result.getInt("IDVENTA") + "";
					datos[i][1] = "  " + result.getString("NOMBRE");
					datos[i][2] = result.getString("FECHAREGISTRO");
				}

				datos[i][3] = "  " + result.getString("PRODUCTO");
				datos[i][4] = "  " + result.getString("CATEGORIA");
				datos[i][5] = result.getInt("CANTIDAD") + "";
				datos[i][6] = String.format("%.2f  ", result.getFloat("PRECIOVENTA"));
				datos[i][7] = String.format("%.2f  ", result.getFloat("SUBTOTAL"));
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static String[][] ListarProductosMas(String fechai, String fechaf) {
		Connection conn = (new Conexion()).getConn();
		Statement statement;
		try {
			String sql = "", sql_ = "";
			if (fechai.equals("")) {
				sql = "SELECT PRO.PRODUCTO, CA.CATEGORIA, SUM(DV.CANTIDAD) AS CANTIDAD FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD DESC";
				sql_ = "SELECT COUNT(*) AS NRO FROM (SELECT PRO.PRODUCTO FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD DESC) AS TAB";
			} else {
				if (fechaf.equals("")) {
					sql = "SELECT PRO.PRODUCTO, CA.CATEGORIA, SUM(DV.CANTIDAD) AS CANTIDAD FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai
							+ " 00:00:00' AS DATETIME) AND CAST(NOW() AS DATETIME) GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD DESC";
					sql_ = "SELECT COUNT(*) AS NRO FROM (SELECT PRO.PRODUCTO FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai
							+ " 00:00:00' AS DATETIME) AND CAST(NOW() AS DATETIME) GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD DESC) AS TAB";
				} else {
					sql = "SELECT PRO.PRODUCTO, CA.CATEGORIA, SUM(DV.CANTIDAD) AS CANTIDAD FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai + " 00:00:00' AS DATETIME) AND CAST('" + fechaf
							+ " 23:59:59' AS DATETIME) GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD DESC";
					sql_ = "SELECT COUNT(*) AS NRO FROM (SELECT PRO.PRODUCTO FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai + " 00:00:00' AS DATETIME) AND CAST('" + fechaf
							+ " 23:59:59' AS DATETIME) GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD DESC) AS TAB";
				}

			}
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql_);
			int nro = 0;
			while (result.next()) {
				nro = result.getInt("NRO");
			}
			if (nro == 0) {
				return new String[15][4];
			}
			String datos[][] = new String[nro][4];
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			int i = 0;
			while (result.next()) {
				datos[i][0] = (i + 1) + "";
				datos[i][1] = "  " + result.getString("PRODUCTO");
				datos[i][2] = "  " + result.getString("CATEGORIA");
				datos[i][3] = result.getInt("CANTIDAD") + "";
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static String[][] ListarProductosMenos(String fechai, String fechaf) {
		Connection conn = (new Conexion()).getConn();
		Statement statement;
		try {
			String sql = "", sql_ = "";
			if (fechai.equals("")) {
				sql = "SELECT PRO.PRODUCTO, CA.CATEGORIA, SUM(DV.CANTIDAD) AS CANTIDAD FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD ASC";
				sql_ = "SELECT COUNT(*) AS NRO FROM (SELECT PRO.PRODUCTO FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD ASC) AS TAB";
			} else {
				if (fechaf.equals("")) {
					sql = "SELECT PRO.PRODUCTO, CA.CATEGORIA, SUM(DV.CANTIDAD) AS CANTIDAD FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai
							+ " 00:00:00' AS DATETIME) AND CAST(NOW() AS DATETIME) GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD ASC";
					sql_ = "SELECT COUNT(*) AS NRO FROM (SELECT PRO.PRODUCTO FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai
							+ " 00:00:00' AS DATETIME) AND CAST(NOW() AS DATETIME) GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD ASC) AS TAB";
				} else {
					sql = "SELECT PRO.PRODUCTO, CA.CATEGORIA, SUM(DV.CANTIDAD) AS CANTIDAD FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai + " 00:00:00' AS DATETIME) AND CAST('" + fechaf
							+ " 23:59:59' AS DATETIME) GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD ASC";
					sql_ = "SELECT COUNT(*) AS NRO FROM (SELECT PRO.PRODUCTO FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA AND V.FECHAREGISTRO BETWEEN CAST('"
							+ fechai + " 00:00:00' AS DATETIME) AND CAST('" + fechaf
							+ " 23:59:59' AS DATETIME) GROUP BY DV.IDPRODUCTO ORDER BY CANTIDAD ASC) AS TAB";
				}

			}
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql_);
			int nro = 0;
			while (result.next()) {
				nro = result.getInt("NRO");
			}
			if (nro == 0) {
				return new String[15][4];
			}
			String datos[][] = new String[nro][4];
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			int i = 0;
			while (result.next()) {
				datos[i][0] = (i + 1) + "";
				datos[i][1] = "  " + result.getString("PRODUCTO");
				datos[i][2] = "  " + result.getString("CATEGORIA");
				datos[i][3] = result.getInt("CANTIDAD") + "";
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static String[][] ListarVentasTotales() {
		Connection conn = (new Conexion()).getConn();
		Statement statement;
		try {
			String sql = "SELECT DV.IDPRODUCTO, SUM(DV.CANTIDAD) AS CANTIDAD FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA GROUP BY DV.IDPRODUCTO";
			String sql_ = "SELECT COUNT(*) AS NRO FROM (SELECT DV.IDPRODUCTO, SUM(DV.CANTIDAD) AS CANTIDAD FROM VENTA AS V, DETALLEVENTA AS DV, PRODUCTO AS PRO, CATEGORIA AS CA WHERE V.IDVENTA = DV.IDVENTA AND DV.IDPRODUCTO = PRO.IDPRODUCTO AND CA.IDCATEGORIA = PRO.IDCATEGORIA GROUP BY DV.IDPRODUCTO) AS TAB";
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql_);
			int nro = 0;
			while (result.next()) {
				nro = result.getInt("NRO");
			}
			if (nro == 0) {
				return new String[15][2];
			}
			String datos[][] = new String[nro][2];
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			int i = 0;
			while (result.next()) {
				datos[i][0] = result.getInt("IDPRODUCTO") + "";
				datos[i][1] = result.getInt("CANTIDAD") + "";

				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static String[][] ListarVentasProductosTotales() {
		Connection conn = (new Conexion()).getConn();
		Statement statement;
		try {
			String sql = "SELECT CO.IDCOMPRA, CO.FECHAREGISTRO, PRO.IDPRODUCTO, PRO.PRODUCTO, CA.CATEGORIA, DC.CANTIDAD, DC.PRECIOCOMPRA FROM COMPRA AS CO, DETALLECOMPRA AS DC, PRODUCTO AS PRO, CATEGORIA AS CA WHERE CO.IDCOMPRA = DC.IDCOMPRA AND DC.IDPRODUCTO = PRO.IDPRODUCTO AND PRO.IDCATEGORIA = CA.IDCATEGORIA ORDER BY CO.FECHAREGISTRO ASC";
			String sql_ = "SELECT COUNT(*) AS NRO FROM (SELECT CO.IDCOMPRA, CO.FECHAREGISTRO, PRO.IDPRODUCTO, PRO.PRODUCTO, CA.CATEGORIA, DC.CANTIDAD, DC.PRECIOCOMPRA FROM COMPRA AS CO, DETALLECOMPRA AS DC, PRODUCTO AS PRO, CATEGORIA AS CA WHERE CO.IDCOMPRA = DC.IDCOMPRA AND DC.IDPRODUCTO = PRO.IDPRODUCTO AND PRO.IDCATEGORIA = CA.IDCATEGORIA ORDER BY CO.FECHAREGISTRO ASC) AS TAB";
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql_);
			int nro = 0;
			while (result.next()) {
				nro = result.getInt("NRO");
			}
			if (nro == 0) {
				return new String[15][7];
			}
			String datos[][] = new String[nro][7];
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			int i = 0;
			while (result.next()) {
				datos[i][0] = result.getInt("IDCOMPRA") + "";
				datos[i][1] = result.getString("FECHAREGISTRO");
				datos[i][2] = result.getInt("IDPRODUCTO") + "";
				datos[i][3] = "  " + result.getString("PRODUCTO");
				datos[i][4] = "  " + result.getString("CATEGORIA");
				datos[i][5] = result.getInt("CANTIDAD") + "";
				datos[i][6] = result.getFloat("PRECIOCOMPRA") + "";
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static ArrayList<Producto> ListarProducto() {
		Connection conn = (new Conexion()).getConn();
		ArrayList<Producto> datos = new ArrayList<Producto>();
		String sql = "SELECT * FROM PRODUCTO AS P, CATEGORIA AS C WHERE P.IDCATEGORIA = C.IDCATEGORIA";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			datos.add(new Producto());
			while (result.next()) {
				int idproducto = result.getInt("IDPRODUCTO");
				String producto = result.getString("PRODUCTO").toUpperCase();
				String categoria = result.getString("CATEGORIA").toUpperCase();
				Producto prod = new Producto();
				prod.setIdproducto(idproducto);
				prod.setProducto(producto);
				prod.setCategoria(categoria);
				datos.add(prod);
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}

	}

}
