package operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import clases.Compra;
import clases.Conexion;
import clases.DetalleCompra;
import clases.Producto;
import clases.Proveedor;
import clases.Usuario;

//ascasc
public class OpCompra {
	public static String[][] ListarCompras(String txt) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT COUNT(*) AS NRO FROM COMPRA AS C, USUARIO AS U, PROVEEDOR AS P WHERE C.IDPROVEEDOR = P.IDPROVEEDOR AND C.IDUSUARIO = U.IDUSUARIO AND (C.IDCOMPRA LIKE '%"
				+ txt + "%' OR P.PROVEEDOR LIKE '%" + txt + "%' OR U.NOMBRE LIKE '%" + txt
				+ "%' OR C.FECHAREGISTRO LIKE '%" + txt + "%' OR C.TOTAL LIKE '%" + txt + "%') ORDER BY C.IDCOMPRA";
		String sql_ = "SELECT *, C.FECHAREGISTRO AS FECHA FROM COMPRA AS C, USUARIO AS U, PROVEEDOR AS P WHERE C.IDPROVEEDOR = P.IDPROVEEDOR AND C.IDUSUARIO = U.IDUSUARIO AND (C.IDCOMPRA LIKE '%"
				+ txt + "%' OR P.PROVEEDOR LIKE '%" + txt + "%' OR U.NOMBRE LIKE '%" + txt
				+ "%' OR C.FECHAREGISTRO LIKE '%" + txt + "%' OR C.TOTAL LIKE '%" + txt + "%') ORDER BY C.IDCOMPRA";
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
				int idcompra = result.getInt("IDCOMPRA");
				String proveedor = result.getString("PROVEEDOR").toUpperCase();
				String usuario = result.getString("NOMBRE").toUpperCase();
				String fecha = result.getString("FECHA");
				float total = result.getFloat("TOTAL");
				datos[i][0] = idcompra + "";
				datos[i][1] = ("  " + proveedor).toUpperCase();
				datos[i][2] = ("  " + usuario).toUpperCase();
				datos[i][3] = fecha + "";
				datos[i][4] = "  Bs. " + String.format("%.2f", total);
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static Compra BuscarCompra(int idcompra) {
		Compra c = new Compra();
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM COMPRA WHERE IDCOMPRA = " + idcompra;
		Statement statement;
		try {

			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int idusuario = 0, idproveedor = 0, idcompra_ = 0;
			while (result.next()) {
				idcompra_ = result.getInt("IDCOMPRA");
				idproveedor = result.getInt("IDPROVEEDOR");
				idusuario = result.getInt("IDUSUARIO");
				String fecharegistro = result.getString("FECHAREGISTRO");
				String observacion = result.getString("OBSERVACION");
				float total = result.getFloat("TOTAL");
				c.setIdcompra(idcompra_);
				c.setFecharegistro(fecharegistro);
				c.setObservacion(observacion.toUpperCase());
				c.setTotal(total);
			}

			sql = "SELECT * FROM USUARIO AS U, TIPOUSUARIO AS TU WHERE U.IDTIPO = TU.IDTIPO AND U.IDUSUARIO = "
					+ idusuario;
			result = statement.executeQuery(sql);
			Usuario u = new Usuario();
			while (result.next()) {
				int idtipo = result.getInt("IDTIPO");
				String tipo = result.getString("TIPO");
				int idusuario_ = result.getInt("IDUSUARIO");
				int ci_ = result.getInt("CI");
				int estado = result.getInt("ESTADO");
				String nombre = result.getString("NOMBRE");
				String telefono = result.getString("TELEFONO");
				String fecharegistro = result.getString("FECHAREGISTRO");
				String celular = result.getString("CELULAR");
				String clave_ = result.getString("CLAVE");
				String direccion = result.getString("DIRECCION");
				u.setIdtipo(idtipo);
				u.setTipo(tipo.toUpperCase());
				u.setIdusuario(idusuario_);
				u.setCi(ci_);
				u.setNombre(nombre.toUpperCase());
				u.setTelefono(telefono);
				u.setFecharegistro(fecharegistro);
				u.setCelular(celular);
				u.setClave(clave_.toUpperCase());
				u.setDireccion(direccion.toUpperCase());
				u.setEstado(estado);
			}
			c.setUsuario(u);
			sql = "SELECT * FROM PROVEEDOR WHERE IDPROVEEDOR = " + idproveedor;
			result = statement.executeQuery(sql);
			Proveedor p = new Proveedor();
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
			c.setProveedor(p);

			sql = "SELECT *, P.DESCRIPCION AS DPR, C.DESCRIPCION AS DCR FROM DETALLECOMPRA AS DC, PRODUCTO P, CATEGORIA AS C WHERE DC.IDPRODUCTO = P.IDPRODUCTO AND C.IDCATEGORIA = P.IDCATEGORIA AND DC.IDCOMPRA = "
					+ idcompra;
			result = statement.executeQuery(sql);
			ArrayList<DetalleCompra> det = new ArrayList<DetalleCompra>();
			while (result.next()) {
				Producto pr = new Producto();
				DetalleCompra dcp = new DetalleCompra();
				int idcategoria = result.getInt("IDCATEGORIA");
				int idproducto_ = result.getInt("IDPRODUCTO");
				int estado = result.getInt("ESTADO");
				int stock = result.getInt("STOCK");
				String descripcioncategoria = result.getString("DCR");
				String descripcionproducto = result.getString("DPR");
				String categoria = result.getString("CATEGORIA");
				String producto = result.getString("PRODUCTO");
				float precioventa = result.getFloat("PRECIOVENTA");
				pr.setIdcategoria(idcategoria);
				pr.setCategoria(categoria);
				pr.setDescripcion(descripcioncategoria);
				pr.setIdproducto(idproducto_);
				pr.setStock(stock);
				pr.setEstado(estado);
				pr.setPrecioventa(precioventa);
				pr.setProducto(producto);
				pr.setDescripcionproducto(descripcionproducto);
				dcp.setProducto(pr);
				int cantidad = result.getInt("CANTIDAD");
				float preciocompra = result.getFloat("PRECIOCOMPRA");
				dcp.setCantidad(cantidad);
				dcp.setPreciocompra(preciocompra);
				det.add(dcp);
			}
			c.setListacompra(det);
			return c;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return c;
		}

	}

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
			String datos[][] = new String[nro][3];
			statement = conn.createStatement();
			result = statement.executeQuery(sql_);
			int i = 0;
			while (result.next()) {
				int idproveedor = result.getInt("IDPROVEEDOR");
				String proveedor = result.getString("PROVEEDOR");
				String nit = result.getString("NIT");
				datos[i][0] = idproveedor + "";
				datos[i][1] = ("  " + proveedor).toUpperCase();
				datos[i][2] = nit.toUpperCase();
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}

	}

	public static JComboBox<Proveedor> listarProveedor() {
		JComboBox<Proveedor> jc = new JComboBox<Proveedor>();
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM PROVEEDOR";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			jc.addItem(new Proveedor());
			while (result.next()) {
				int idproveedor = result.getInt("IDPROVEEDOR");
				String proveedor = result.getString("PROVEEDOR");
				String telefono = result.getString("TELEFONO");
				String celular = result.getString("CELULAR");
				String nit = result.getString("NIT");
				String direccion = result.getString("DIRECCION");
				String descripcion = result.getString("DESCRIPCION");
				Proveedor pr = new Proveedor(idproveedor, proveedor, direccion, telefono, celular, descripcion, nit);
				jc.addItem(pr);
			}
			return jc;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return jc;
		}

	}

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
			String datos[][] = new String[nro][4];
			statement = conn.createStatement();
			result = statement.executeQuery(sql_);
			int i = 0;
			while (result.next()) {
				int idproducto = result.getInt("IDPRODUCTO");
				String producto = result.getString("PRODUCTO").toUpperCase();
				int stock = result.getInt("STOCK");
				float precio = result.getFloat("PRECIOVENTA");

				datos[i][0] = idproducto + "";
				datos[i][1] = ("  " + producto).toUpperCase();
				datos[i][2] = stock + "";
				datos[i][3] = "   Bs. " + String.format("%.2f", precio);
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}

	}

	public static int RegistrarCompra(Compra c) {
		Connection conn = (new Conexion()).getConn();

		try {
			PreparedStatement statement;
			int idcompra = 0;
			if (c.getIdcompra() != 0) {
				idcompra = c.getIdcompra();
				String sql_ = "DELETE FROM DETALLECOMPRA WHERE IDCOMPRA = ?";
				statement = conn.prepareStatement(sql_);
				statement.setInt(1, c.getIdcompra());
				if (statement.executeUpdate() == 0) {
					return 0;
				}

				sql_ = "UPDATE COMPRA SET IDPROVEEDOR = ?, IDUSUARIO = ?, OBSERVACION = ?, TOTAL = ? WHERE IDCOMPRA = ?";
				statement = conn.prepareStatement(sql_);
				statement.setInt(1, c.getProveedor().getIdproveedor());
				statement.setInt(2, c.getUsuario().getIdusuario());
				statement.setString(3, c.getObservacion());
				statement.setFloat(4, 0f);
				statement.setInt(5, idcompra);
				if (statement.executeUpdate() == 0) {
					return 0;
				}
			} else {
				String sql = "INSERT INTO COMPRA(IDPROVEEDOR, IDUSUARIO, OBSERVACION, TOTAL) VALUES (?, ?, ?, ?)";
				statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, c.getProveedor().getIdproveedor());
				statement.setInt(2, c.getUsuario().getIdusuario());
				statement.setString(3, c.getObservacion());
				statement.setFloat(4, 0f);
				if (statement.executeUpdate() == 0) {
					return 0;
				}
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					idcompra = generatedKeys.getInt(1);
				}
			}
			for (int i = 0; i < c.getListacompra().size(); i++) {
				String sql = "INSERT INTO DETALLECOMPRA(IDCOMPRA, IDPRODUCTO, CANTIDAD, PRECIOCOMPRA) VALUES (?, ?, ?, ?)";
				statement = conn.prepareStatement(sql);
				statement.setInt(1, idcompra);
				statement.setInt(2, c.getListacompra().get(i).getProducto().getIdproducto());
				statement.setInt(3, c.getListacompra().get(i).getCantidad());
				statement.setFloat(4, c.getListacompra().get(i).getPreciocompra());
				if (statement.executeUpdate() == 0) {
					return 0;
				}
			}
			return idcompra;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return 0;
	}

	public static boolean EliminarCompra(int idcompra) {
		Connection conn = (new Conexion()).getConn();

		try {
			PreparedStatement statement;
			String sql_ = "DELETE FROM DETALLECOMPRA WHERE IDCOMPRA = ?";
			statement = conn.prepareStatement(sql_);
			statement.setInt(1, idcompra);
			if (statement.executeUpdate() == 0) {
				return false;
			}
			sql_ = "DELETE FROM COMPRA WHERE IDCOMPRA = ?";
			statement = conn.prepareStatement(sql_);
			statement.setInt(1, idcompra);
			if (statement.executeUpdate() == 0) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}
}
