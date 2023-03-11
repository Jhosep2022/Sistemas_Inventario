package operaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import clases.Conexion;
import clases.TipoUsuario;
import clases.Usuario;

public class OpUsuario {
	public static JComboBox<TipoUsuario> ListarTipoUsuario() {
		JComboBox<TipoUsuario> jc = new JComboBox<TipoUsuario>();
		jc.addItem(new TipoUsuario(0, ""));
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM TIPOUSUARIO";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int idtipo = result.getInt("IDTIPO");
				String tipo = result.getString("TIPO").toUpperCase();
				jc.addItem(new TipoUsuario(idtipo, tipo));
			}
			return jc;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return jc;
		}

	}

	public static boolean BuscarCiUsuario(int ci) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM USUARIO WHERE CI = " + ci;
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

	public static boolean BuscarCiUsuarioActualizar(int ci, int ci_) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM USUARIO WHERE CI = " + ci + " AND CI != " + ci_;
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

	public static boolean InsertarUsuario(Usuario u) {
		Connection conn = (new Conexion()).getConn();
		String sql = "INSERT INTO USUARIO(IDTIPO, CI, NOMBRE, TELEFONO, CELULAR, CLAVE, DIRECCION, ESTADO) VALUES "
				+ "(?,?,?,?,?,?,?, ?)";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, u.getIdtipo());
			statement.setInt(2, u.getCi());
			statement.setString(3, u.getNombre());
			statement.setString(4, u.getTelefono());
			statement.setString(5, u.getCelular());
			statement.setString(6, u.getClave());
			statement.setString(7, u.getDireccion());
			statement.setInt(8, u.getEstado());
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return false;
		}
	}

	public static String[][] ListarUsuario(String txt) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM USUARIO AS U, TIPOUSUARIO AS T WHERE U.IDTIPO = T.IDTIPO" + " AND (U.CI LIKE '%"
				+ txt + "%' OR U.IDUSUARIO LIKE '%" + txt + "%' OR U.NOMBRE LIKE '%" + txt + "%' OR T.TIPO LIKE '%"
				+ txt + "%' OR U.FECHAREGISTRO LIKE '%" + txt + "%')";
		String sql_ = "SELECT COUNT(*) AS NRO FROM USUARIO AS U, TIPOUSUARIO AS T WHERE U.IDTIPO = T.IDTIPO"
				+ " AND (U.CI LIKE '%" + txt + "%' OR U.IDUSUARIO LIKE '%" + txt + "%' OR U.NOMBRE LIKE '%" + txt
				+ "%' OR T.TIPO LIKE '%" + txt + "%' OR U.FECHAREGISTRO LIKE '%" + txt + "%')";
		Statement statement;

		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql_);
			int nro = 0;
			while (result.next()) {
				nro = result.getInt("NRO");
			}
			if (nro < 10) {
				nro = 10;
			}
			String datos[][] = new String[nro][11];

			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			int i = 0;
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
				datos[i][0] = (idusuario + "");
				datos[i][1] = ci_ + "";
				datos[i][2] = ("  " + nombre + "").toUpperCase();
				datos[i][3] = ("  " + tipo + "").toUpperCase();
				datos[i][4] = fecharegistro + "";
				datos[i][5] = idtipo + "";
				datos[i][6] = telefono + "";
				datos[i][7] = celular + "";
				datos[i][8] = (clave_ + "").toUpperCase();
				datos[i][9] = (direccion + "").toUpperCase();
				datos[i][10] = estado + "";
				i++;
			}
			return datos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return null;
		}

	}

	public static Usuario Buscarusuario(int idusuario_) {
		Usuario u = new Usuario();
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM USUARIO AS U, TIPOUSUARIO AS TU WHERE U.IDTIPO = TU.IDTIPO AND U.IDUSUARIO = "
				+ idusuario_;
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
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
			return u;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return u;
		}

	}

	public static boolean ActualizarUsuario(Usuario u) {
		Connection conn = (new Conexion()).getConn();
		String sql = "UPDATE USUARIO SET IDTIPO = ?, CI = ?, NOMBRE = ?, TELEFONO = ?, CELULAR = ?, CLAVE = ?, DIRECCION = ?, ESTADO = ?"
				+ " WHERE IDUSUARIO = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, u.getIdtipo());
			statement.setInt(2, u.getCi());
			statement.setString(3, u.getNombre());
			statement.setString(4, u.getTelefono());
			statement.setString(5, u.getCelular());
			statement.setString(6, u.getClave());
			statement.setString(7, u.getDireccion());
			statement.setInt(8, u.getEstado());
			statement.setInt(9, u.getIdusuario());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return false;
		}
	}

	public static boolean EliminarUsuario(int idusuario) {
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM COMPRA AS C, VENTA AS V WHERE C.IDUSUARIO = " + idusuario + " OR V.IDUSUARIO = "
				+ idusuario;
		String sql_ = "DELETE FROM USUARIO WHERE IDUSUARIO = ?";
		Statement statement;

		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			int count = 0;
			while (result.next()) {
				count++;
			}
			if (count > 0) {
				return false;
			} else {
				PreparedStatement statement_ = conn.prepareStatement(sql_);
				statement_.setInt(1, idusuario);

				int rowsDeleted = statement_.executeUpdate();
				if (rowsDeleted > 0) {
					return true;
				} else {
					return false;
				}
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return false;
		}
	}

}
