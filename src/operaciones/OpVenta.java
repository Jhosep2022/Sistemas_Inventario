package operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import clases.Cliente;
import clases.Conexion;
import clases.DetalleVenta;
import clases.Producto;
import clases.Usuario;
import clases.Venta;

public class OpVenta {
	public static String[][] ListarVentas(String txt) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT COUNT(*) AS NRO FROM VENTA AS V, CLIENTE AS C, USUARIO AS U WHERE V.IDCLIENTE = C.IDCLIENTE AND V.IDUSUARIO = U.IDUSUARIO AND (V.IDVENTA LIKE '%"
				+ txt + "%' OR C.NOMBRE LIKE '%" + txt + "%' OR U.NOMBRE LIKE '%" + txt
				+ "%' OR V.FECHAREGISTRO LIKE '%" + txt + "%' OR V.TOTAL LIKE '%" + txt + "%') ORDER BY V.IDVENTA ASC";
		String sql_ = "SELECT *, C.NOMBRE AS NOMBRECLIENTE, U.NOMBRE AS NOMBREUSUARIO, V.FECHAREGISTRO AS FECHA FROM VENTA AS V, CLIENTE AS C, USUARIO AS U WHERE V.IDCLIENTE = C.IDCLIENTE AND V.IDUSUARIO = U.IDUSUARIO AND (V.IDVENTA LIKE '%"
				+ txt + "%' OR C.NOMBRE LIKE '%" + txt + "%' OR U.NOMBRE LIKE '%" + txt
				+ "%' OR V.FECHAREGISTRO LIKE '%" + txt + "%' OR V.TOTAL LIKE '%" + txt + "%') ORDER BY V.IDVENTA ASC";

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
				int idcompra = result.getInt("IDVENTA");
				String cliente = result.getString("NOMBRECLIENTE").toUpperCase();
				String usuario = result.getString("NOMBREUSUARIO").toUpperCase();
				String fecha = result.getString("FECHA");
				float total = result.getFloat("TOTAL");
				datos[i][0] = idcompra + "";
				datos[i][1] = ("  " + cliente).toUpperCase();
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

	public static Venta BuscarVenta(int idventa) {
		Venta v = new Venta();
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM VENTA WHERE  IDVENTA = " + idventa;
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int idusuario_ = 0, idcliente_ = 0, idventa_ = 0;
			while (result.next()) {
				idventa_ = result.getInt("IDVENTA");
				idcliente_ = result.getInt("IDCLIENTE");
				idusuario_ = result.getInt("IDUSUARIO");
				String fecharegistro = result.getString("FECHAREGISTRO");
				String observacion = result.getString("OBSERVACION");
				float total = result.getFloat("TOTAL");
				v.setIdventa(idventa_);
				v.setFecharegistro(fecharegistro);
				v.setObservacion(observacion.toUpperCase());
				v.setTotal(total);
			}
			sql = "SELECT * FROM USUARIO AS U, TIPOUSUARIO AS TU WHERE U.IDTIPO = TU.IDTIPO AND U.IDUSUARIO = "
					+ idusuario_;
			result = statement.executeQuery(sql);
			Usuario u = new Usuario();
			while (result.next()) {
				int idtipo = result.getInt("IDTIPO");
				String tipo = result.getString("TIPO");
				int idusuario = result.getInt("IDUSUARIO");
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
				u.setIdusuario(idusuario);
				u.setCi(ci_);
				u.setNombre(nombre.toUpperCase());
				u.setTelefono(telefono);
				u.setFecharegistro(fecharegistro);
				u.setCelular(celular);
				u.setClave(clave_.toUpperCase());
				u.setDireccion(direccion.toUpperCase());
				u.setEstado(estado);
			}
			v.setUsuario(u);
			sql = "SELECT * FROM CLIENTE WHERE IDCLIENTE = " + idcliente_;
			result = statement.executeQuery(sql);
			Cliente c = new Cliente();
			while (result.next()) {
				int idcliente = result.getInt("IDCLIENTE");
				int ci = result.getInt("CI");
				String nombre = result.getString("NOMBRE");
				String telefono = result.getString("TELEFONO");
				String celular = result.getString("CELULAR");
				String fecharegistro = result.getString("FECHAREGISTRO");
				String direccion = result.getString("DIRECCION");
				String nit = result.getString("NIT");
				int estado = result.getInt("ESTADO");
				c.setIdcliente(idcliente);
				c.setCi(ci);
				c.setNombre(nombre);
				c.setTelefono(telefono);
				c.setCelular(celular);
				c.setFecharegistro(fecharegistro);
				c.setDireccion(direccion);
				c.setNit(nit);
				c.setEstado(estado);
			}
			v.setCliente(c);

			sql = "SELECT *,P.DESCRIPCION AS DP, C.DESCRIPCION AS DC, P.PRECIOVENTA AS PRECIOP, DV.PRECIOVENTA AS PRECIOV FROM DETALLEVENTA AS DV, PRODUCTO AS P, CATEGORIA AS C WHERE DV.IDPRODUCTO = P.IDPRODUCTO AND P.IDCATEGORIA = C.IDCATEGORIA AND DV.IDVENTA = "
					+ idventa;

			result = statement.executeQuery(sql);
			ArrayList<DetalleVenta> det = new ArrayList<DetalleVenta>();
			while (result.next()) {
				Producto pr = new Producto();
				DetalleVenta dv = new DetalleVenta();
				int idcategoria = result.getInt("IDCATEGORIA");
				int idproducto_ = result.getInt("IDPRODUCTO");
				int estado = result.getInt("ESTADO");
				int stock = result.getInt("STOCK");
				String descripcioncategoria = result.getString("DC");
				String descripcionproducto = result.getString("DP");
				String categoria = result.getString("CATEGORIA");
				String producto = result.getString("PRODUCTO");
				float precioventav = result.getFloat("PRECIOV");
				float precioventap = result.getFloat("PRECIOP");
				pr.setIdcategoria(idcategoria);
				pr.setCategoria(categoria);
				pr.setDescripcion(descripcioncategoria);
				pr.setIdproducto(idproducto_);
				pr.setStock(stock);
				pr.setEstado(estado);
				pr.setPrecioventa(precioventap);
				pr.setProducto(producto);
				pr.setDescripcionproducto(descripcionproducto);
				dv.setProducto(pr);
				int cantidad = result.getInt("CANTIDAD");
				dv.setCantidad(cantidad);
				dv.setPrecioventa(precioventav);
				det.add(dv);
			}
			v.setListaventa(det);
			;
			return v;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return v;
		}

	}

	public static JComboBox<Cliente> ListarCliente() {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM CLIENTE";
		JComboBox<Cliente> jc = new JComboBox<Cliente>();
		Statement statement;
		try {
			jc.addItem(new Cliente());
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				Cliente c = new Cliente();
				c.setIdcliente(result.getInt("IDCLIENTE"));
				c.setNombre(result.getString("NOMBRE"));
				jc.addItem(c);
			}
			return jc;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}
	}

	public static int RegistrarVenta(Venta v) {
		Connection conn = (new Conexion()).getConn();
		try {
			PreparedStatement statement;
			String sql = "";
			int idventa = 0;
			System.out.println(v.getIdventa());
			if (v.getIdventa() != 0) {
				idventa = v.getIdventa();
				String sql_ = "DELETE FROM DETALLEVENTA WHERE IDVENTA = ?";
				statement = conn.prepareStatement(sql_);
				statement.setInt(1, v.getIdventa());
				if (statement.executeUpdate() == 0) {
					System.out.println("1");
					return 0;
				}
				sql_ = "UPDATE VENTA SET IDCLIENTE = ?, IDUSUARIO = ?, OBSERVACION = ?, TOTAL = ? WHERE IDVENTA = ?";
				statement = conn.prepareStatement(sql_);
				statement.setInt(1, v.getCliente().getIdcliente());
				statement.setInt(2, v.getUsuario().getIdusuario());
				statement.setString(3, v.getObservacion());
				statement.setFloat(4, 0f);
				statement.setInt(5, idventa);
				if (statement.executeUpdate() == 0) {
					System.out.println("2");
					return 0;
				}
			} else {
				System.out.println("FALSO");
				sql = "INSERT INTO VENTA(IDCLIENTE, IDUSUARIO, OBSERVACION, TOTAL) VALUES (?, ?, ?, ?)";
				statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, v.getCliente().getIdcliente());
				statement.setInt(2, v.getUsuario().getIdusuario());
				statement.setString(3, v.getObservacion());
				statement.setFloat(4, 0f);
				if (statement.executeUpdate() == 0) {
					System.out.println("3");
					return 0;
				}
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					idventa = generatedKeys.getInt(1);
				}
			}
			System.out.println(idventa + "- " + v.getListaventa().size());
			for (int i = 0; i < v.getListaventa().size(); i++) {
				sql = "INSERT INTO DETALLEVENTA(IDVENTA, IDPRODUCTO, CANTIDAD, PRECIOVENTA) VALUES (?, ?, ?, ?)";
				statement = conn.prepareStatement(sql);
				statement.setInt(1, idventa);
				statement.setInt(2, v.getListaventa().get(i).getProducto().getIdproducto());
				statement.setInt(3, v.getListaventa().get(i).getCantidad());
				statement.setFloat(4, v.getListaventa().get(i).getPrecioventa());
				if (statement.executeUpdate() == 0) {
					System.out.println("4");
					return 0;
				}
			}
			return idventa;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return 0;
	}

	public static boolean EliminarVenta(int idventa) {
		Connection conn = (new Conexion()).getConn();

		try {
			PreparedStatement statement;
			String sql_ = "DELETE FROM DETALLEVENTA WHERE IDVENTA = ?";
			statement = conn.prepareStatement(sql_);
			statement.setInt(1, idventa);
			if (statement.executeUpdate() == 0) {
				return false;
			}
			sql_ = "DELETE FROM VENTA WHERE IDVENTA = ?";
			statement = conn.prepareStatement(sql_);
			statement.setInt(1, idventa);
			if (statement.executeUpdate() == 0) {
				return false;
			}
			return true;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
		}
		return false;
	}

	public static String[][] ListarProducto(String txt) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT COUNT(*) AS NRO FROM PRODUCTO AS P, CATEGORIA AS C WHERE P.STOCK > 0 AND P.IDCATEGORIA = C.IDCATEGORIA AND (C.CATEGORIA LIKE '%"
				+ txt + "%' OR P.PRODUCTO LIKE '%" + txt + "%' OR P.STOCK LIKE '%" + txt + "%' OR P.PRECIOVENTA LIKE '%"
				+ txt + "%')";
		String sql_ = "SELECT * FROM PRODUCTO AS P, CATEGORIA AS C WHERE P.STOCK > 0 AND P.IDCATEGORIA = C.IDCATEGORIA AND (C.CATEGORIA LIKE '%"
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
}
